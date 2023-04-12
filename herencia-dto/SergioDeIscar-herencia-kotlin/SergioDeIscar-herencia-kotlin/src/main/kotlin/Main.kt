import controllers.PersonaController
import factories.PersonaFactory
import models.Persona
import repositories.persona.PersonaRepositoryMap
import service.storage.persona.*

@ExperimentalStdlibApi
fun main() {
    val personas = PersonaFactory.getRdnPersonas()

    /*generateFile(personas, PersonaFileJson)
    generateFile(personas, PersonaFileXml)
    generateFile(personas, PersonaFileCsv)
    generateFile(personas, PersonaFileBinario)
    generateFile(personas, PersonaFileSerializable)*/

    generateFile(personas, PersonaDB)

    /*println("Tienen el mismo contenido:" + equalContent(personas, listOf(
        PersonaFileJson,
        PersonaFileXml,
        PersonaFileCsv,
        PersonaFileBinario,
        PersonaFileSerializable,
        PersonaDB
    )))*/
}

private fun generateFile(personas: List<Persona>, storageService: PersonaStorageService) {
    val controller = PersonaController(
        PersonaRepositoryMap(
            storageService
        )
    )

    controller.saveAll(personas)
    controller.getAll().forEach { println(it) }
}

private fun equalContent(personas: List<Persona>, listOf: List<PersonaStorageService>): Boolean {
    val controllers = listOf.map {
        PersonaController(
            PersonaRepositoryMap(
                it
            )
        )
    }
    controllers.forEach { it.saveAll(personas) } // Cargar datos
    controllers.forEach {
        println(it.getPorcentajePorTipo().mapValues { "${it.value * 100} %" })
    }
    return controllers.map { it.getAll() }.distinct().size == 1
}
