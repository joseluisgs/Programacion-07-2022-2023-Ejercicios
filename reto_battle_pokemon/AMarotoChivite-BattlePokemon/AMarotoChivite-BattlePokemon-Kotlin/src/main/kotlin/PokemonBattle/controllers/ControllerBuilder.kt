package PokemonBattle.controllers

import PokemonBattle.models.Team
import PokemonBattle.models.factories.TeamFactory
import mu.KotlinLogging

class ControllerBuilder {
    private val logger = KotlinLogging.logger {}

    fun createTeam(): Team {
        logger.debug { "ControllerBuilder: Creando Equipo Pokemon" }
        return TeamFactory.create()
    }
}