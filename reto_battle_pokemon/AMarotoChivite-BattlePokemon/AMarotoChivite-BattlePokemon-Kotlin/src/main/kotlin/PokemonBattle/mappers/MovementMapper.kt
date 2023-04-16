package mappers

import PokemonBattle.enums.Types
import PokemonBattle.mappers.base.IDtoMapper
import PokemonBattle.mappers.base.ListMapper
import PokemonBattle.models.Movement
import PokemonBattle.models.dto.MovementDto

class MovementMapper : IDtoMapper<MovementDto, Movement> {

    override fun toModel(dto: MovementDto): Movement {
        return Movement(
            dto.power.toInt(),
            dto.pointsPower.toInt(),
            Types.valueOf(dto.type)
        )
    }
}

class MovementListMapper : ListMapper<MovementDto, Movement>(MovementMapper()) {
    fun toModelList(dtoList: List<MovementDto>): List<Movement> {
        return dtoList.map { MovementMapper().toModel(it) }
    }
}