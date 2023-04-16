package PokemonBattle.models.factories

import PokemonBattle.enums.Types
import PokemonBattle.models.Team
import PokemonBattle.models.dto.MovementDto
import PokemonBattle.models.dto.PokemonDto
import kotlin.math.round
import kotlin.random.Random

class TeamFactory {
    companion object {
        fun create(): Team {

            val listPokemon = mutableListOf<PokemonDto>()

            repeat(6) {
                val ratioDefense = Random.nextDouble(0.2, 0.7)
                val ratioDefenseRounded = (round(ratioDefense * 10) / 10).toString()
                val maxLife = (100..150).random().toString()

                // Filtro tipos
                val listTypes = mutableListOf<Types>()

                val repositoryTypes = Types.values()
                val numTypes = (1..10).random()
                if (numTypes in 1..7) {
                    listTypes.add(repositoryTypes.random())
                } else {
                    val type1 = repositoryTypes.random()
                    var type2 = repositoryTypes.random()

                    // Ciclo while que se repite hasta que type2 sea diferente de type1
                    while (type2 == type1) {
                        type2 = repositoryTypes.random()
                    }

                    listTypes.add(type1)
                    listTypes.add(type2)
                }

                // Filtro movimientos
                val listMovement = mutableListOf<MovementDto>()
                repeat(4) {
                    listMovement.add(MovementFactory.create())
                }
                listPokemon.add(PokemonDto(ratioDefenseRounded, maxLife, listTypes, listMovement))
            }

            return Team(listPokemon)
        }
    }
}