import controllers.DuplaController
import controllers.InformeController
import factories.InformeFactory
import repositories.dupla.DuplaRepositoryMap
import repositories.informe.InformeRepository
import repositories.informe.InformeRepositoryMap
import services.storage.dupla.DuplaFileCsv
import services.storage.dupla.DuplaFileJson
import services.storage.dupla.DuplaFileXml
import services.storage.informe.InformeFileJson
import services.storage.informe.InformeFileXml

@ExperimentalStdlibApi
fun main() {
    val controllerCsv = DuplaController(
        DuplaRepositoryMap(
            DuplaFileCsv
        )
    )
    val controllerJson = DuplaController(
        DuplaRepositoryMap(
            DuplaFileJson
        )
    )
    val controllerXml = DuplaController(
        DuplaRepositoryMap(
            DuplaFileXml
        )
    )
    val duplasCsv = controllerCsv.getAll()
    controllerJson.saveAll(duplasCsv)
    DuplaFileXml.saveAll(duplasCsv)

    println("Csv, Json y Xml mismo contenido: ${
        duplasCsv.containsAll(controllerJson.getAll()) &&
        duplasCsv.containsAll(controllerXml.getAll())
    }")

    consultas(controllerJson)

    createInfromes(controllerXml)
}

@ExperimentalStdlibApi
private fun createInfromes(controllerDupla: DuplaController) {
    val controllerInformeJson = InformeController(
        InformeRepositoryMap(
            InformeFileJson
        )
    )
    val controllerInformeXml = InformeController(
        InformeRepositoryMap(
            InformeFileXml
        )
    )
    val informes = InformeFactory.createInformesMadrid(controllerDupla)

    controllerInformeJson.saveAll(informes)
    controllerInformeXml.saveAll(informes)
}

private fun consultas(controller: DuplaController) {
    println("Consultas:")
    println("Temperatura máxima por día y lugar:")
    controller.maxTemPorLugar().forEach { println(it) }
    step()
    println("Temperatura mínima por día y lugar")
    controller.minTemPorLugar().forEach { println(it) }
    step()
    println("Temperatura máxima por provincia:")
    controller.maxTemPorProvincia().forEach { println(it) }
    step()
    println("Temperatura mínima por provincia:")
    controller.minTemPorProvincia().forEach { println(it) }
    step()
    println("Media de temperatura por provincia:")
    controller.mediaPorProvincia().forEach { println(it) }
    step()
    println("Media de precipitación por día y provincia:")
    controller.mediaPrecipitacionPorDiaYProvincia().forEach { println(it) }
    step()
    println("Media de temperatura en Madrid:")
    println(controller.mediaTemMadrid())
    step()
    println("Media de temperatura máxima:")
    println(controller.mediaTemMax())
    step()
    println("Media de temperatura mínima:")
    println(controller.mediaTemMin())
    step()
    println("Lugares donde la máxima ha sido antes de las 15:00 por día:")
    controller.maxTemAntes().forEach { println(it) }
    step()
    println("Lugares donde la mínima ha sido después de las 17:30 por día:")
    controller.minTemDespues().forEach { println(it) }
    step()
}

private fun step(time: Long = 5_000L){
    println("========================================")
    repeat(5){ println() }
    Thread.sleep(time)
}