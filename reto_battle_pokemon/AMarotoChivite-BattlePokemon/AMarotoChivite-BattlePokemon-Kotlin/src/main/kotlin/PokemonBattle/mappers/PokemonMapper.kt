package PokemonBattle.mappers

import PokemonBattle.mappers.base.IDtoMapper
import PokemonBattle.mappers.base.ListMapper
import PokemonBattle.models.Pokemon
import PokemonBattle.models.Team
import PokemonBattle.models.dto.PokemonDto
import mappers.MovementListMapper

class PokemonMapper : IDtoMapper<PokemonDto, Pokemon> {
    override fun toModel(dto: PokemonDto): Pokemon {
        return Pokemon(
            dto.getID().toInt(),
            dto.ratioDefense.toDouble(),
            dto.life.toInt(),
            dto.listType, // Ya me entran como List<Types>
            MovementListMapper().toModelList(dto.listMovement)
        )
    }
}

class PokemonListMapper : ListMapper<PokemonDto, Pokemon>(PokemonMapper()) {
    fun toModelList(dtoList: Team): List<Pokemon> {
        return dtoList.listPokemon.map { PokemonMapper().toModel(it) }
    }
}
