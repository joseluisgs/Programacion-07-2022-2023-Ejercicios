package PokemonBattle.models.factories

import PokemonBattle.enums.Types
import PokemonBattle.models.dto.MovementDto

class MovementFactory {
    companion object {
        fun create(): MovementDto {
            val type = Types.values().random().toString()
            val power = (45..70).random().toString()
            val pointsPower = (2..4).random().toString()
            return MovementDto(power, pointsPower, type)
        }
    }
}