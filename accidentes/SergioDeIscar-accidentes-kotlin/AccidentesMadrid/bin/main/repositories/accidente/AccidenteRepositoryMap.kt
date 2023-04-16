package repositories.accidente

import enums.TipoClima
import enums.TipoSexo
import models.Accidente
import mu.KotlinLogging
import repositories.IExternalStore
import services.storage.accidente.AccidenteStorageService

private val logger = KotlinLogging.logger {  }

class AccidenteRepositoryMap(
    private val storageService: AccidenteStorageService
): AccidenteRepository{
    private val accidentes = mutableMapOf<String, Accidente>()

    override fun getAccidentesAlcoholOrDrogas(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAlcoholOrDrogas" }
        upgrade()
        return accidentes.values.filter { it.alcochol || it.drogas }
    }

    override fun getNumeroAccidentesAlcoholAndDrogas(): Int {
        logger.debug { "AccidenteRepositoryMap ->\tgetNumeroAccidentesAlcoholAndDrogas" }
        upgrade()
        return accidentes.values.count { it.alcochol && it.drogas }
    }

    override fun getAccidentesAgrupadosPorSexo(): Map<TipoSexo, List<Accidente>> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAgrupadosPorSexo" }
        upgrade()
        return accidentes.values.groupBy { it.sexo }
    }

    override fun getAccidentesAgrupadosPorMes(): Map<Int, List<Accidente>> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAgrupadosPorMes" }
        upgrade()
        return accidentes.values.groupBy { it.fechaYHora.monthValue }
    }

    override fun getAccidentesAgrupadosPorVehiculo(): Map<String, List<Accidente>> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAgrupadosPorVehiculo" }
        upgrade()
        return accidentes.values.groupBy { it.tipoVehiculo }
    }

    override fun getAccidentesEnLaCalleLeganes(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesEnLaCalleLeganes" }
        upgrade()
        return accidentes.values.filter { it.localizacion.lowercase().contains("leganes") || it.localizacion.lowercase().contains("leganés") }
    }

    override fun getNumeroAccidentesPorDistrito(): Map<String, Int> {
        logger.debug { "AccidenteRepositoryMap ->\tgetNumeroAccidentesPorDistrito" }
        upgrade()
        return accidentes.values.groupBy { it.distrito }.mapValues { it.value.size }
    }

    override fun getAccidentesPorDistritoDescendente(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesPorDistritoDescendente" }
        upgrade()
        return accidentes.values.sortedByDescending { it.distrito }
    }

    override fun getAccidentesFindeNoche(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesFindeNoche" }
        upgrade()
        return accidentes.values.filter {
            it.fechaYHora.hour !in 6..20 &&
            it.fechaYHora.dayOfWeek.value !in 1..5
        }
    }

    override fun getAccidentesFindeNocheAlcohol(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesFindeNocheAlcohol" }
        upgrade()
        return getAccidentesFindeNoche().filter { it.alcochol }
    }

    override fun getAccidentesConMasDeUnFallecido(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesConMasDeUnFallecido" }
        upgrade()
        return accidentes.values.filter { it.codLesividad == 4 } // 4 = Fallecido
    }

    override fun isDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes(): Boolean {
        logger.debug { "AccidenteRepositoryMap ->\tisDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes" }
        upgrade()
        val distritoConMasAccidentes = getNumeroAccidentesPorDistrito().maxByOrNull { it.value }?.key
        val distritoConMasAccidentesFinde = getAccidentesFindeNoche()
            .groupBy { it.distrito }
            .maxByOrNull { it.value.size }?.key
        return distritoConMasAccidentes == distritoConMasAccidentesFinde
    }

    override fun getAccidentesAlcoholOrDrogasAndFallecidos(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAlcoholOrDrogasAndFallecidos" }
        upgrade()
        return getAccidentesAlcoholOrDrogas().filter { it.codLesividad == 4 } // 4 = Fallecido
    }

    override fun getNumeroAccidentesAtropelloPersona(): Int {
        logger.debug { "AccidenteRepositoryMap ->\tgetNumeroAccidentesAtropelloPersona" }
        upgrade()
        return accidentes.values.count { it.tipoAccidente.lowercase().contains("atropello a persona") }
    }

    override fun getAccidentesAgrupadosPorClima(): Map<TipoClima, List<Accidente>> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAgrupadosPorClima" }
        upgrade()
        return accidentes.values.groupBy { it.clima }
    }

    override fun getAccidentesAtropelloAnimal(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAccidentesAtropelloAnimal" }
        upgrade()
        return accidentes.values.filter { it.tipoAccidente.lowercase().contains("animal") }
    }

    override fun getAll(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tgetAll" }
        upgrade()
        return accidentes.values.toList()
    }

    override fun getById(id: String): Accidente? {
        logger.debug { "AccidenteRepositoryMap ->\tgetById" }
        upgrade()
        return accidentes[id]
    }

    override fun save(element: Accidente, storage: Boolean): Accidente {
        logger.debug { "AccidenteRepositoryMap ->\tsave" }
        //upgrade()
        accidentes[element.numExpediente] = element
        if (storage) downgrade()
        return element
    }

    override fun saveAll(elements: List<Accidente>, storage: Boolean) {
        logger.debug { "AccidenteRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, storage) }
    }

    override fun deleteById(id: String): Accidente? {
        logger.debug { "AccidenteRepositoryMap ->\tdeleteById" }
        //upgrade()
        val result = accidentes.remove(id)
        downgrade()
        return result
    }

    override fun update(element: Accidente): Accidente? {
        logger.debug { "AccidenteRepositoryMap ->\tupdate" }
        return updateById(element.numExpediente, element)
    }

    override fun updateById(id: String, element: Accidente): Accidente? {
        logger.debug { "AccidenteRepositoryMap ->\tupdateById" }
        if (getById(id) == null) return null
        accidentes[id] = element
        downgrade()
        return element
    }

    override fun upgrade(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tupgrade" }
        accidentes.clear()
        val load = storageService.loadAll()
        saveAll(load, false)
        return load
    }

    override fun downgrade(): List<Accidente> {
        logger.debug { "AccidenteRepositoryMap ->\tdowngrade" }
        return storageService.saveAll(accidentes.values.toList())
    }
}