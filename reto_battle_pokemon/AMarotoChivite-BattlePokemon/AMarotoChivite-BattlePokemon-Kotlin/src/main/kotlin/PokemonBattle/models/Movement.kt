package PokemonBattle.models

import PokemonBattle.enums.Types

data class Movement(
    val power: Int,
    var pointsPower: Int,
    val type: Types,
) {
    override fun toString(): String {
        return "Movement(power='$power', pointsPower='$pointsPower', type='${type.emoji}')"
    }
}
