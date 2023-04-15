import controllers.DuplaController
import controllers.InformeController
import factories.InformeFactory
import models.Dupla
import models.Informe
import repositories.dupla.DuplaRepositoryMap
import repositories.informe.InformeRepository
import repositories.informe.InformeRepositoryMap
import services.storage.dupla.DuplaFileCsv
import services.storage.dupla.DuplaFileJson
import services.storage.dupla.DuplaFileXml
import services.storage.dupla.DuplaStorageService
import services.storage.informe.*

@ExperimentalStdlibApi
fun main() {
    val duplaControllers = listOf(
        DuplaController(
            DuplaRepositoryMap(
                DuplaFileCsv
            )
        ),
        DuplaController(
            DuplaRepositoryMap(
                DuplaFileJson
            )
        ),
        DuplaController(
            DuplaRepositoryMap(
                DuplaFileXml
            )
        )
    )

    println("Tienen el mismo contenido las duplas: " + generateFiles(
        duplaControllers[0].getAll().toList(),
        duplaControllers
    ))

    consultas(duplaControllers[1])

    val informeControllers = listOf(
        InformeController(
            InformeRepositoryMap(
                InformeFileJson
            )
        ),
        InformeController(
            InformeRepositoryMap(
                InformeFileXml
            )
        ),
        InformeController(
            InformeRepositoryMap(
                InformeFileCsv
            )
        ),
        InformeController(
            InformeRepositoryMap(
                InformeFileBinario
            )
        )
    )

    println("Tienen el mismo contenido los informes: " +
            createInfromes(duplaControllers[2], informeControllers
            ))
}

private fun generateFiles(duplas: List<Dupla>, controllers: List<DuplaController>): Boolean{
    controllers.forEach{it.saveAll(duplas)}
    return controllers.map { it.getAll() }.distinct().size == 1
}

@ExperimentalStdlibApi
private fun createInfromes(controllerDupla: DuplaController, controllers: List<InformeController>): Boolean {
    val informes = InformeFactory.createInformesMadrid(controllerDupla)
    controllers.forEach { it.saveAll(informes) }
    return controllers.map { it.getAll() }.distinct().size == 1
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