package PokemonBattle.models.dto

import PokemonBattle.enums.Types

data class PokemonDto(
    val ratioDefense: String,
    val life: String,
    val listType: List<Types>,
    val listMovement: List<MovementDto>
) {
    private var idPokemon: String = autoCount().toString()

    fun getID(): String {
        return idPokemon
    }

    companion object {
        private var counter = 0
        private fun autoCount(): Int {
            return counter++
        }
    }

    /*    override fun toString(): String {
            return "Pokemon(ratioDefense='$ratioDefense', maxLife='$maxLife', listType=${printTypes()} ${printMovement()})"
        }

        private fun printTypes(): String {
            return listType.joinToString(separator = "+") { type -> type.emoji }
        }

        private fun printMovement(): String {
            return "\nListMovement: \n${listMovement.joinToString("\n")})"
        }*/
}