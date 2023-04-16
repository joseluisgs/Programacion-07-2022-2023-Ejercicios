package PokemonBattle.models

import PokemonBattle.models.dto.PokemonDto

data class Team(
    val listPokemon: List<PokemonDto>
) {
    private var idTeam: String = autoCount().toString()

    fun getID(): String {
        return idTeam
    }

    companion object {
        private var counter = 0
        private fun autoCount(): Int {
            return counter++
        }
    }
}