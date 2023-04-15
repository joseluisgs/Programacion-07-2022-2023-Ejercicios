package controllers

import enums.TipoClima
import enums.TipoSexo
import models.Accidente
import mu.KotlinLogging
import repositories.accidente.AccidenteExtension
import repositories.accidente.AccidenteRepository

private val logger = KotlinLogging.logger {  }

class AccidenteController(
    private val repo: AccidenteRepository
): AccidenteExtension {
    override fun getAccidentesAlcoholOrDrogas(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesAlcoholOrDrogas" }
        return repo.getAccidentesAlcoholOrDrogas()
    }

    override fun getNumeroAccidentesAlcoholAndDrogas(): Int {
        logger.debug { "AccidenteController ->\tgetNumeroAccidentesAlcoholAndDrogas" }
        return repo.getNumeroAccidentesAlcoholAndDrogas()
    }

    override fun getAccidentesAgrupadosPorSexo(): Map<TipoSexo, List<Accidente>> {
        logger.debug { "AccidenteController ->\tgetAccidentesAgrupadosPorSexo" }
        return repo.getAccidentesAgrupadosPorSexo()
    }

    override fun getAccidentesAgrupadosPorMes(): Map<Int, List<Accidente>> {
        logger.debug { "AccidenteController ->\tgetAccidentesAgrupadosPorMes" }
        return repo.getAccidentesAgrupadosPorMes()
    }

    override fun getAccidentesAgrupadosPorVehiculo(): Map<String, List<Accidente>> {
        logger.debug { "AccidenteController ->\tgetAccidentesAgrupadosPorVehiculo" }
        return repo.getAccidentesAgrupadosPorVehiculo()
    }

    override fun getAccidentesEnLaCalleLeganes(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesEnLaCalleLeganes" }
        return repo.getAccidentesEnLaCalleLeganes()
    }

    override fun getNumeroAccidentesPorDistrito(): Map<String, Int> {
        logger.debug { "AccidenteController ->\tgetNumeroAccidentesPorDistrito" }
        return repo.getNumeroAccidentesPorDistrito()
    }

    override fun getAccidentesPorDistritoDescendente(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesPorDistritoDescendente" }
        return repo.getAccidentesPorDistritoDescendente()
    }

    override fun getAccidentesFindeNoche(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesFindeNoche" }
        return repo.getAccidentesFindeNoche()
    }

    override fun getAccidentesFindeNocheAlcohol(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesFindeNocheAlcohol" }
        return repo.getAccidentesFindeNocheAlcohol()
    }

    override fun getAccidentesConMasDeUnFallecido(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesConMasDeUnFallecido" }
        return repo.getAccidentesConMasDeUnFallecido()
    }

    override fun isDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes(): Boolean {
        logger.debug { "AccidenteController ->\tisDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes" }
        return repo.isDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes()
    }

    override fun getAccidentesAlcoholOrDrogasAndFallecidos(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesAlcoholOrDrogasAndFallecidos" }
        return repo.getAccidentesAlcoholOrDrogasAndFallecidos()
    }

    override fun getNumeroAccidentesAtropelloPersona(): Int {
        logger.debug { "AccidenteController ->\tgetNumeroAccidentesAtropelloPersona" }
        return repo.getNumeroAccidentesAtropelloPersona()
    }

    override fun getAccidentesAgrupadosPorClima(): Map<TipoClima, List<Accidente>> {
        logger.debug { "AccidenteController ->\tgetAccidentesAgrupadosPorClima" }
        return repo.getAccidentesAgrupadosPorClima()
    }

    override fun getAccidentesAtropelloAnimal(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAccidentesAtropelloAnimal" }
        return repo.getAccidentesAtropelloAnimal()
    }

    override fun getAll(): List<Accidente> {
        logger.debug { "AccidenteController ->\tgetAll" }
        return repo.getAll()
    }

    override fun getById(id: String): Accidente? {
        logger.debug { "AccidenteController ->\tgetById" }
        return repo.getById(id)
    }

    override fun save(element: Accidente, storage: Boolean): Accidente {
        logger.debug { "AccidenteController ->\tsave" }
        return repo.save(element, storage)
    }

    override fun saveAll(elements: List<Accidente>, storage: Boolean) {
        logger.debug { "AccidenteController ->\tsaveAll" }
        repo.saveAll(elements, storage)
    }

    override fun deleteById(id: String): Accidente? {
        logger.debug { "AccidenteController ->\tdeleteById" }
        return repo.deleteById(id)
    }

    override fun update(element: Accidente): Accidente? {
        logger.debug { "AccidenteController ->\tupdate" }
        return repo.update(element)
    }

    override fun updateById(id: String, element: Accidente): Accidente? {
        logger.debug { "AccidenteController ->\tupdateById" }
        return repo.updateById(id, element)
    }
}