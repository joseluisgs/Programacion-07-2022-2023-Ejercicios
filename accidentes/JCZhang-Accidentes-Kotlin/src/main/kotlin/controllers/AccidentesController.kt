package controllers

import mu.KotlinLogging
import repositories.AccidentesRepository

class AccidentesController(val repo: AccidentesRepository){

    private val logger = KotlinLogging.logger {  }

    fun load(){
        logger.debug { "Controller: load()" }
        repo.load()
    }

    fun save(){
        logger.debug { "Controller: save()" }
        repo.save()
    }

}