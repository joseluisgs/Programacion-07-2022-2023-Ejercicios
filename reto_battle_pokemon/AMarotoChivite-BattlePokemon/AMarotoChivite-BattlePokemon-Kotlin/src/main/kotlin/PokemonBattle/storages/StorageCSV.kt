package PokemonBattle.storages

import PokemonBattle.config.ConfigApp
import PokemonBattle.enums.Types
import PokemonBattle.models.Team
import PokemonBattle.models.dto.MovementDto
import PokemonBattle.models.dto.PokemonDto
import mu.KotlinLogging
import java.io.File

class StorageCSV : IStorageWritter<Team> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}teams.csv"
    val file = File(localFile)

    override fun saveFile(repository: List<Team>) {
        logger.debug { "Storage: Escribiendo en fichero CSV" }

        file.writeText("id_team,id_pokemon;ratio_defense;max_life;types;movement_power/movement_point_power/movement_type" + "\n")

        repository.forEach {
            file.appendText("${it.getID()},${printPokemon(it.listPokemon)}\n")
        }
    }

    private fun printPokemon(pokemons: List<PokemonDto>): String {
        return pokemons.joinToString("|") {
            "${it.getID()};${it.ratioDefense};${it.life};${printTypes(it.listType)};${printMovements(it.listMovement)}"
        }
    }

    private fun printTypes(types: List<Types>): String {
        return types.joinToString("+") {
            "$it"
        }
    }

    private fun printMovements(listMovement: List<MovementDto>): String {
        return listMovement.joinToString("+") {
            "${it.power}/${it.pointsPower}/${it.type}"
        }
    }
}