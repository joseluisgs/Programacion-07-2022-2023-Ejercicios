package mappers

import Ficheros.BurguerPig.mappers.base.IDtoMapper
import Ficheros.BurguerPig.mappers.base.ListMapper
import Ficheros.BurguerPig.models.Ingredient
import Ficheros.BurguerPig.models.dto.IngredientDTO
import Ficheros.BurguerPig.models.dto.IngredientListDto

class IngredientMapper : IDtoMapper<IngredientDTO, Ingredient> {

    override fun toDto(model: Ingredient): IngredientDTO {
        return IngredientDTO(
            model.name,
            model.price.toString(),
            model.getID().toString()
        )
    }

    override fun toModel(dto: IngredientDTO): Ingredient {
        return Ingredient(
            dto.name,
            dto.price.toDouble(),
            dto.id.toInt()
        )
    }
}

class IngredientListMapper : ListMapper<IngredientDTO, Ingredient>(IngredientMapper()) {

    fun toDtoList(modelList: List<Ingredient>): IngredientListDto {
        val dtoList = modelList.map { IngredientMapper().toDto(it) }
        return IngredientListDto(dtoList)
    }

    fun toModelList(dtoList: List<IngredientDTO>): List<Ingredient> {
        return dtoList.map { IngredientMapper().toModel(it) }
    }
}