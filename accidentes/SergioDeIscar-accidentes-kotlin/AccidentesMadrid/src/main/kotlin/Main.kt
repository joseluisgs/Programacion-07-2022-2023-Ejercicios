import controllers.AccidenteController
import repositories.accidente.AccidenteRepositoryMap
import services.storage.accidente.AccidenteFileCsv
import services.storage.accidente.AccidenteFileJson
import services.storage.accidente.AccidenteFileXml

@ExperimentalStdlibApi
fun main() {
    val controllerCsv = AccidenteController(
        AccidenteRepositoryMap(
            AccidenteFileCsv
        )
    )
    val controllerJson = AccidenteController(
        AccidenteRepositoryMap(
            AccidenteFileJson
        )
    )
    val controllerXml = AccidenteController(
        AccidenteRepositoryMap(
            AccidenteFileXml
        )
    )

    val csv = controllerCsv.getAll()
    val csvMitad = csv.subList(0, 500) // Tarda mucho en escribirse en el fichero

    controllerJson.saveAll(csvMitad)
    controllerXml.saveAll(csvMitad)

    println("Tienen el mismo contenido: ${controllerJson.getAll().containsAll(controllerXml.getAll())}")
    step()
    consultas(controllerCsv)
}

private fun consultas(controller: AccidenteController) {
    println("Accidentes con alcohol o drogas:")
    controller.getAccidentesAlcoholOrDrogas().forEach { println(it) }
    step()
    println("Numero de accidentes con alcohol y drogas:")
    println(controller.getNumeroAccidentesAlcoholAndDrogas())
    step()
    println("Accidentes agrupados por sexo:")
    controller.getAccidentesAgrupadosPorSexo().mapValues { it.value.size }.forEach { println(it) }
    step()
    println("Accidentes agrupados por mes:")
    controller.getAccidentesAgrupadosPorMes().mapValues { it.value.size }.forEach { println(it) }
    step()
    println("Accidentes agrupados por vehiculo:")
    controller.getAccidentesAgrupadosPorVehiculo().mapValues { it.value.size }.forEach { println(it) }
    step()
    println("Accidentes en la calle Leganés:")
    controller.getAccidentesEnLaCalleLeganes().forEach { println(it) }
    step()
    println("Numero de accidentes por distrito:")
    controller.getNumeroAccidentesPorDistrito().forEach { println(it) }
    step()
    println("Accidentes por distrito descendente:")
    controller.getAccidentesPorDistritoDescendente().forEach { println(it) }
    step()
    println("Accidentes fin de semana noche:")
    controller.getAccidentesFindeNoche().forEach { println(it) }
    step()
    println("Accidentes fin de semana noche alcohol:")
    controller.getAccidentesFindeNocheAlcohol().forEach { println(it) }
    step()
    println("Accidentes con mas de un fallecido:")
    controller.getAccidentesConMasDeUnFallecido().forEach { println(it) }
    step()
    println("Distrito con mas accidentes igual distrito con mas accidentes fin de semana:")
    println(controller.isDistritoConMasAccidentesIgualDistritoConMasAccidentesFindes())
    step()
    println("Accidentes con alcohol o drogas y fallecidos:")
    controller.getAccidentesAlcoholOrDrogasAndFallecidos().forEach { println(it) }
    step()
    println("Numero de accidentes atropello persona:")
    println(controller.getNumeroAccidentesAtropelloPersona())
    step()
    println("Accidentes agrupados por clima:")
    controller.getAccidentesAgrupadosPorClima().forEach { println(it) }
    step()
    println("Accidentes atropello animal:")
    controller.getAccidentesAtropelloAnimal().forEach { println(it) }
    step()
}

private fun step(time: Long = 5_00L) {
    println("====================================")
    repeat(5){ println() }
    Thread.sleep(time)
}