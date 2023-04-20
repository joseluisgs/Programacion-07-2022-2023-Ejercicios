package controllers.persona

import com.github.michaelbull.result.*
import errors.PersonaError
import models.Persona
import mu.KotlinLogging
import repositories.persona.PersonaRepository
import service.storage.persona.PersonaStorageService
import validators.validate

private val logger = KotlinLogging.logger {}

class PersonaController(
    private val repo: PersonaRepository,
    private val storage: PersonaStorageService
): IPersonaController {
    override fun getPorcentajePorTipo(): Map<String, Double> {
        logger.debug { "PersonaController ->\tgetPorcentajePorTipo" }
        return repo.getPorcentajePorTipo()
    }

    override fun findAll(): Iterable<Persona> {
        logger.debug { "PersonaController ->\tgetAll" }
        return repo.findAll()
    }

    override fun findById(id: Long): Result<Persona, PersonaError> {
        logger.debug { "PersonaController ->\tgetById" }
        return repo.findById(id)?.let { Ok(it) } ?: Err(PersonaError.PersonaNoEncontradaError())
    }

    override fun save(element: Persona, storage: Boolean): Result<Persona, PersonaError> {
        logger.debug { "PersonaController ->\tsave" }
        return element.validate().onSuccess {
            repo.save(element)
            if (storage) exportData()
        }
    }

    override fun saveAll(elements: Iterable<Persona>, storage: Boolean) {
        logger.debug { "PersonaController ->\tsaveAll" }
        elements.forEach { it.validate().onFailure {
            logger.error { "PersonaController -> ${it.message}" }
            return
        }}
        repo.saveAll(elements)
        if (storage) exportData()
    }

    override fun deleteById(id: Long): Result<Boolean, PersonaError> {
        logger.debug { "PersonaController ->\tdeleteById" }
        return if (repo.deleteById(id)){
            Ok(true)
        } else{
            Err(PersonaError.PersonaNoEncontradaError())
        }
    }

    override fun delete(element: Persona): Result<Boolean, PersonaError> {
        logger.debug { "PersonaController ->\tdelete" }
        return if (repo.delete(element)){
            Ok(true)
        } else{
            Err(PersonaError.PersonaNoEncontradaError())
        }
    }

    override fun deleteAll() {
        logger.debug { "PersonaController ->\tdeleteAll" }
        repo.deleteAll()
    }

    override fun existsById(id: Long): Result<Boolean, PersonaError> {
        logger.debug { "PersonaController ->\texistsById" }
        return if (repo.existsById(id)){
            Ok(true)
        } else{
            Err(PersonaError.PersonaNoEncontradaError())
        }
    }

    override fun exportData() {
        logger.debug { "PersonaController ->\texportData" }
        storage.saveAll(repo.findAll().toList())
    }

    override fun importData() {
        logger.debug { "PersonaController ->\timportData" }
        repo.saveAll(storage.loadAll())
    }
}