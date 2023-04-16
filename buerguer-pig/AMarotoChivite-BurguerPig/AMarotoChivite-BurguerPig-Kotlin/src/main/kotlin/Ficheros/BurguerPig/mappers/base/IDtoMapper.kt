package Ficheros.BurguerPig.mappers.base

interface IDtoMapper<Dto, Model> {
    fun toDto(model: Model): Dto
    fun toModel(dto: Dto): Model
}