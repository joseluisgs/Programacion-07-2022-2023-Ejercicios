package PokemonBattle.controllers

import PokemonBattle.models.Team
import PokemonBattle.repositories.RepositoryTeamWritterReader
import mu.KotlinLogging

class ControllerWritterReader(private val repository: RepositoryTeamWritterReader) {
    private val logger = KotlinLogging.logger {}

    fun saveFile() {
        logger.debug { "Controller: Escribiendo en fichero" }
        repository.saveFile()
    }

    fun readFile(): List<Team> {
        logger.debug { "Controller: Leyendo de fichero" }
        val listTeams = repository.readFile()

        if (listTeams.size != 2) {
            logger.debug { "Controller: Cantidad de equipos errónea" }
            IllegalArgumentException("Cantidad de equipos errónea")
        }

        for (i in listTeams.indices) {
            if (listTeams[i].listPokemon.size != 6) {
                logger.debug { "Controller: Cantidad de pokemon errónea" }
                IllegalArgumentException("Cantidad de pokemon errónea")
            }
        }

        return listTeams
    }

    fun saveItem(item: Team) {
        logger.debug { "Controller: Añadiendo item" }
        repository.saveItem(item)
    }

}