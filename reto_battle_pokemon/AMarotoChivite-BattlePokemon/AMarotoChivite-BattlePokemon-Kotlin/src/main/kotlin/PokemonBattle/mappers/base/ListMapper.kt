package PokemonBattle.mappers.base

class ListDto<Dto>(val list: List<Dto>)

open class ListMapper<Dto, Model>(private val objectMapper: IDtoMapper<Dto, Model>) :
    IDtoMapper<ListDto<Dto>, List<Model>> {

    override fun toModel(dto: ListDto<Dto>): List<Model> {
        return dto.list.map { objectMapper.toModel(it) }
    }
}