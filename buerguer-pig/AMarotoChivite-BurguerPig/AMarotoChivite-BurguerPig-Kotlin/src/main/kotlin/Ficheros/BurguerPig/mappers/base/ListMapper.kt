package Ficheros.BurguerPig.mappers.base

class ListDto<Dto>(val list: List<Dto>)

open class ListMapper<Dto, Model>(private val objectMapper: IDtoMapper<Dto, Model>) :
    IDtoMapper<ListDto<Dto>, List<Model>> {

    override fun toDto(model: List<Model>): ListDto<Dto> {
        val dtoList = model.map { objectMapper.toDto(it) }
        return ListDto(dtoList)
    }

    override fun toModel(dto: ListDto<Dto>): List<Model> {
        return dto.list.map { objectMapper.toModel(it) }
    }
}