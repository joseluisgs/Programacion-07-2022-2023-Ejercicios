import repositories.AccidentesRepository
import java.time.LocalTime
import java.time.temporal.ChronoField

val accidentes = AccidentesRepository.accidentes

fun main() {
//	numAccidentesAlcoholODrogas()
//	numAccidentesAlcoholYDrogas()
//	numAccidentesPorSexo()
//	numAccidentesPorMes()
//	accidentesPorTipoDeVehiculo()
//	accidentesEnLeganes()
//	numAccidentesPorDistrito()
//	estadisticasPorDistrito()
//	accidentesPorDistritoDescending()
//	accidentesDuranteFinDeSemanaNoche()
//	distritoConMasAccidentesIgualADistritoConMasAccidentesDuranteFinde()
//	accidentesDuranteFinDeSemanaNocheYPositivoAlcohol()
//	accidentesConMasDeUnFallecido()
//	alcoholODrogasYVictimasMortales()
//	accidentesConAtropellosAPersonas()
//	accidentesPorEstadoMeteorologico()
//	distritosConAtropelloAnimal()
}

fun numAccidentesAlcoholODrogas () {
	println("Hay ${accidentes.count { it.positividadAlcohol.contains("S") || it.positividadDroga.contains("1")}} accidentes en los que se han visto involucrados alcohol o drogas")
}

fun numAccidentesAlcoholYDrogas () {
	println("Hay ${accidentes.count { it.positividadAlcohol.contains("S") && it.positividadDroga.contains("1")}} accidentes en los que se han visto involucrados alcohol y drogas")
}

fun numAccidentesPorSexo () {
	val listaOrdenadaPorSexo = accidentes.groupBy {it.sexo}
	for ((sexo, accidentes) in listaOrdenadaPorSexo) {
		println("$sexo: ${accidentes.size}")
	}
}

fun numAccidentesPorMes () {
	val listaOrdenadaPorMes = accidentes.groupBy {it.fecha.month}
	for ((mes, accidente) in listaOrdenadaPorMes) {
		println("$mes: ${accidente.size}")
	}
}

fun accidentesPorTipoDeVehiculo () {
	val listaOrdenadaPorTipoDeVehiculo = accidentes.groupBy {it.tipoVehiculo}
	for ((tipoVehiculo, accidentes) in listaOrdenadaPorTipoDeVehiculo) {
		println("$tipoVehiculo: ${accidentes.size}")
	}
}

fun accidentesEnLeganes() {
	val listaOrdenadaPorDistrito = accidentes.groupBy {it.distrito}
	val distritoSeleccionado = "LEGANES"
	if (listaOrdenadaPorDistrito.keys.contains(distritoSeleccionado)) {
		for ((distrito, accidentes) in listaOrdenadaPorDistrito) {
			if (distrito == distritoSeleccionado)
				println("Ha habido ${accidentes.size} accidentes en $distrito ")
		}
	}
	else
		println("No hay ningún accidente registrado en $distritoSeleccionado")
}

fun numAccidentesPorDistrito() {
	val listaOrdenadaPorDistrito = accidentes.groupBy {it.distrito}
	for ((distrito, accidentes) in listaOrdenadaPorDistrito) {
		println("$distrito: ${accidentes.size}")
	}
}

fun estadisticasPorDistrito() {
	val listaOrdenadaPorDistrito = accidentes.groupBy {it.distrito}
		for ((distrito, accidentes) in listaOrdenadaPorDistrito) {
			println("$distrito: ")
			println("\t Tipo de Vehículo: ")
			val listaOrdenadaTipoDeVehiculo = accidentes.groupBy {it.tipoVehiculo}
			for ((tipoVehiculo, _accidentes) in listaOrdenadaTipoDeVehiculo){
				println("\t\t $tipoVehiculo: ${_accidentes.size}")
			}
			println("\t Tipo de Accidente: ")
			val listaOrdenadaPorTipoDeAccidente = accidentes.groupBy {it.tipoAccidente}
			for ((tipoAccidente, _accidentes) in listaOrdenadaPorTipoDeAccidente){
				println("\t\t $tipoAccidente: ${_accidentes.size}")
			}
			println("\t Estado Meteorológico: ")
			val listaOrdenadaPorMeteorologia = accidentes.groupBy {it.estadoMeteorologico}
			for ((estadoMeteorologico, _accidentes) in listaOrdenadaPorMeteorologia){
				println("\t\t $estadoMeteorologico: ${_accidentes.size}")
			}
			println("\t Tipo de Persona: ")
			val listaOrdenadaPorTipoDePersona = accidentes.groupBy {it.tipoPersona}
			for ((tipoPersona, _accidentes) in listaOrdenadaPorTipoDePersona){
				println("\t\t $tipoPersona: ${_accidentes.size}")
			}
			println("\t Rango de Edad: ")
			val listaOrdenadaPorRangoDeEdad = accidentes.groupBy {it.rangoEdad}
			for ((rangoEdad, _accidentes) in listaOrdenadaPorRangoDeEdad){
				println("\t\t $rangoEdad: ${_accidentes.size}")
			}
			println("\t Sexo: ")
			val listaOrdenadaPorSexo = accidentes.groupBy {it.sexo}
			for ((sexo, _accidentes) in listaOrdenadaPorSexo){
				println("\t\t $sexo: ${_accidentes.size}")
			}
			println("\t Lesividad: ")
			val listaOrdenadaPorLesividad = accidentes.groupBy {it.lesividad}
			for ((lesividad, _accidentes) in listaOrdenadaPorLesividad){
				println("\t\t $lesividad: ${_accidentes.size}")
			}
			println("\t Positividad en Alcohol: ")
			val listaOrdenadaPorPositividadEnAlcohol = accidentes.groupBy {it.positividadAlcohol}
			for ((positividad, _accidentes) in listaOrdenadaPorPositividadEnAlcohol){
				println("\t\t $positividad: ${_accidentes.size}")
			}
			println("\t Positividad en Drogas: ")
			val listaOrdenadaPorPositividadEnDrogas = accidentes.groupBy {it.positividadDroga}
			for ((positividad, _accidentes) in listaOrdenadaPorPositividadEnDrogas){
				println("\t\t ${positividad.dropLast(1)}: ${_accidentes.size}")
			}
	}
}

fun accidentesPorDistritoDescending () {
	val cantidadAccidentesPorDistrito = accidentes.groupBy { it.distrito }.mapValues { entry -> entry.value.size }.toList().sortedByDescending { it.second }
	cantidadAccidentesPorDistrito.forEach { (distrito, cantidad) -> println("$distrito: $cantidad")
	}
}

fun accidentesDuranteFinDeSemanaNoche () {
	val accidentesFinDeSemanaNoche = accidentes.filter {(it.fecha.get(ChronoField.DAY_OF_WEEK) == 6 || it.fecha.get(ChronoField.DAY_OF_WEEK) == 7) && (it.hora.isAfter(LocalTime.of(21, 0)) || it.hora.isBefore(LocalTime.of(7, 0))) }
	for (accidente in accidentesFinDeSemanaNoche) {
		println("Número de Expediente: ${accidente.numExpediente.substring(1)} -- Fecha del accidente: ${accidente.fecha} -- Hora del accidente: ${accidente.hora}")
	}
}

fun accidentesDuranteFinDeSemanaNocheYPositivoAlcohol () {
	val accidentesFinDeSemanaNochePositivoAlcohol = accidentes.filter { (it.fecha.get(ChronoField.DAY_OF_WEEK) == 6 || it.fecha.get(ChronoField.DAY_OF_WEEK) == 7) && (it.hora.isAfter(LocalTime.of(21, 0)) || it.hora.isBefore(LocalTime.of(7, 0))) && it.positividadAlcohol == "S" }
	for (accidente in accidentesFinDeSemanaNochePositivoAlcohol) {
		println("Número de Expediente: ${accidente.numExpediente.substring(1)} -- Fecha del accidente: ${accidente.fecha} -- Hora del accidente: ${accidente.hora} -- Positivo en Alcohol: ${accidente.positividadAlcohol}")
	}
}

fun accidentesConMasDeUnFallecido (){
	val accidentesConFallecidos = accidentes.filter { accidente -> accidente.codLesividad == "4" }
	val accidentesAgrupadosPorExpediente = accidentesConFallecidos.groupBy { it.numExpediente }
	for ((numExpediente, group) in accidentesAgrupadosPorExpediente)
		println("Número de Expediente: ${numExpediente.substring(1)} -- Número de Fallecidos: ${group.size}")
}

fun distritoConMasAccidentesIgualADistritoConMasAccidentesDuranteFinde() {
	val cantidadDeAccidentesPorDistrito = accidentes.groupBy { it.distrito }.mapValues { entry -> entry.value.size }.toList().sortedByDescending { it.second }
	println ("El distrito con más accidentes: ${cantidadDeAccidentesPorDistrito.first()}")

	val accidentesFinDeSemana = accidentes.filter {it.fecha.get(ChronoField.DAY_OF_WEEK) == 6 || it.fecha.get(ChronoField.DAY_OF_WEEK) == 7}
	val cantidadDeAccidentesFinDeSemanaPorDistrito = accidentesFinDeSemana.groupBy { it.distrito }.mapValues { entry -> entry.value.size }.toList().sortedByDescending { it.second }
	println ("El distrito con más accidentes los fin de semana: ${cantidadDeAccidentesFinDeSemanaPorDistrito.first()}")
}

fun alcoholODrogasYVictimasMortales () {
	val accidentesConAlcoholODrogasYFallecidos = accidentes.filter { accidente -> (accidente.positividadAlcohol == "S" || accidente.positividadDroga == "1") && accidente.lesividad == "4" }
	accidentesConAlcoholODrogasYFallecidos.forEach { accidente -> println("Número de Expediente: ${accidente.numExpediente} -- Positivo en alcohol: ${accidente.positividadAlcohol} -- Positivo en drogas: ${accidente.positividadDroga} -- Fallecido: ${accidente.lesividad}") }
}

fun accidentesConAtropellosAPersonas () {
	val accidentesConAtropellos = accidentes.count { accidente -> accidente.tipoAccidente.contains("persona") }
	println("Número de accidentes con atropellos a personas: $accidentesConAtropellos")
}

fun accidentesPorEstadoMeteorologico () {
	val listaOrdenadaPorEstadoMeteorologico = accidentes.groupBy {it.estadoMeteorologico}
	for ((estadoMeteorologico, accidentes) in listaOrdenadaPorEstadoMeteorologico) {
		println("$estadoMeteorologico: ${accidentes.size}")
	}
}

fun distritosConAtropelloAnimal () {
	val listaConAtropelloAAnimal = accidentes.filter { it.tipoAccidente.contains("animal")}
	val listaAgrupada = listaConAtropelloAAnimal.groupBy { it.distrito }
	for ((distrito, accidente) in listaAgrupada)
		println("$distrito: ${accidente.size}")
}


