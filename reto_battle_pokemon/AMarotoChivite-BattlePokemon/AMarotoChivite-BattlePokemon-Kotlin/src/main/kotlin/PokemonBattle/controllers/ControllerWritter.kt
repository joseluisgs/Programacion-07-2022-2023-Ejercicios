package PokemonBattle.controllers

import PokemonBattle.repositories.RepositoryTeamWritter
import mu.KotlinLogging

class ControllerWritter(private val repository: RepositoryTeamWritter) {
    private val logger = KotlinLogging.logger {}

    fun saveFile() {
        logger.debug { "Controller: Escribiendo en fichero" }
        repository.saveFile()
    }

}