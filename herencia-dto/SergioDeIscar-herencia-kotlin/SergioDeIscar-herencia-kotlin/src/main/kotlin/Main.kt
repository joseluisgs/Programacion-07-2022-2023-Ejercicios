import controllers.PersonaController
import factories.PersonaFactory
import models.Persona
import repositories.persona.PersonaRepositoryMap
import service.storage.persona.PersonaFileCsv
import service.storage.persona.PersonaFileJson
import service.storage.persona.PersonaFileXml
import service.storage.persona.PersonaStorageService

@ExperimentalStdlibApi
fun main() {
    val personas = PersonaFactory.getRdnPersonas()

    generateFile(personas, PersonaFileJson)
    generateFile(personas, PersonaFileXml)
    generateFile(personas, PersonaFileCsv)

    println("Tienen el mismo contenido:" + equalContent(listOf(PersonaFileJson, PersonaFileXml, PersonaFileCsv)))
}

private fun generateFile(personas: List<Persona>, storageService: PersonaStorageService) {
    val controller = PersonaController(
        PersonaRepositoryMap(
            storageService
        )
    )

    controller.saveAll(personas)
}

private fun equalContent(listOf: List<PersonaStorageService>): Boolean {
    val controllers = listOf.map {
        PersonaController(
            PersonaRepositoryMap(
                it
            )
        )
    }
    return controllers.map { it.getAll() }.distinct().size == 1
}
