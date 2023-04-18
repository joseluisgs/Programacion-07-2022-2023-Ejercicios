import controllers.persona.PersonaController
import factories.PersonaFactory
import models.Persona
import repositories.persona.PersonaRepositoryDataBase
import repositories.persona.PersonaRepositoryMap
import service.storage.persona.*

@ExperimentalStdlibApi
fun main() {
    val personas = PersonaFactory.getRdnPersonas()

    val controllers = listOf(
        PersonaController(
            PersonaRepositoryDataBase
        ),
        PersonaController(
            PersonaRepositoryMap(
                PersonaFileBinario
            )
        ),
        PersonaController(
            PersonaRepositoryMap(
                PersonaFileCsv
            )
        ),
        PersonaController(
            PersonaRepositoryMap(
                PersonaFileJson
            )
        ),
        PersonaController(
            PersonaRepositoryMap(
                PersonaFileSerializable
            )
        ),
        PersonaController(
            PersonaRepositoryMap(
                PersonaFileXml
            )
        )
    )

    println("Tienen el mismo contenido:" + generateFile(personas, controllers))
}

private fun generateFile(personas: List<Persona>, controllers: List<PersonaController>): Boolean {
    controllers.forEach { it.saveAll(personas) }
    // Con distinct ya que no estan en el mismo orden
    return controllers.all { it.findAll().distinct() == personas.distinct() }
}
