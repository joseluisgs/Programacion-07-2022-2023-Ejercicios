package PokemonBattle.mappers.base

interface IDtoMapper<Dto, Model> {
    fun toModel(dto: Dto): Model
}