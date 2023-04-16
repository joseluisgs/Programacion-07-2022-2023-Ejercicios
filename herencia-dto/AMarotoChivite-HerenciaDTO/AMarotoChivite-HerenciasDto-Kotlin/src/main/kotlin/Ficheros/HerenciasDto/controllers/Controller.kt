package Ficheros.HerenciaDto.controllers

import Ficheros.HerenciasDto.models.dto.PersonDTO
import Ficheros.HerenciasDto.repositories.IRepository
import Ficheros.HerenciasDto.repositories.Repository
import mu.KotlinLogging

class Controller(private val repository: Repository) : IRepository<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    override fun saveAllModelsInFileOverride() {
        logger.debug { "Controller: Escribiendo y (sobreescribiendo)" }
        repository.saveAllModelsInFileOverride()
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Controller: Leyendo" }
        return repository.readAllModelsInFile()
    }
}