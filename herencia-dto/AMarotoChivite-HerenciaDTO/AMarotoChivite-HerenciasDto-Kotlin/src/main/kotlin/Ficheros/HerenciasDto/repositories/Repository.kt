package Ficheros.HerenciasDto.repositories

import Ficheros.HerenciasDto.models.dto.PersonDTO
import Ficheros.HerenciasDto.models.factories.PersonaFactory
import Ficheros.HerenciasDto.storage.IStorageGeneral
import mu.KotlinLogging


class Repository(private val storage: IStorageGeneral<PersonDTO>) : IRepository<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    private val repository = PersonaFactory().createPersonas(20)

    override fun saveAllModelsInFileOverride() {
        logger.debug { "Repository: Escribiendo y (sobreescribiendo)" }
        storage.saveInFile(repository.toList())
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Repository: Leyendo" }
        return storage.readAllModelsInFile()
    }
}