package repositories.accidente

import enums.TipoClima
import enums.TipoSexo
import models.Accidente
import repositories.CrudRepository

interface AccidenteExtension: CrudRepository<Accidente, String> {
    fun getAccidentesAlcoholOrDrogas(): List<Accidente>
    fun getNumeroAccidentesAlcoholAndDrogas(): Int
    fun getAccidentesAgrupadosPorSexo(): Map<TipoSexo,List<Accidente>>
    fun getAccidentesAgrupadosPorMes(): Map<Int,List<Accidente>>
    fun getAccidentesAgrupadosPorVehiculo(): Map<String,List<Accidente>>
    fun getAccidentesEnLaCalleLeganes(): List<Accidente>
    fun getNumeroAccidentesPorDistrito(): Map<String,Int>
    fun getAccidentesPorDistritoDescendente(): List<Accidente>
    fun getAccidentesFindeNoche(): List<Accidente>
    fun getAccidentesFindeNocheAlcohol(): List<Accidente>
    fun getAccidentesConMasDeUnFallecido(): List<Accidente>
    fun isDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes(): Boolean
    fun getAccidentesAlcoholOrDrogasAndFallecidos(): List<Accidente>
    fun getNumeroAccidentesAtropelloPersona(): Int
    fun getAccidentesAgrupadosPorClima(): Map<TipoClima,List<Accidente>>
    fun getAccidentesAtropelloAnimal(): List<Accidente>
}