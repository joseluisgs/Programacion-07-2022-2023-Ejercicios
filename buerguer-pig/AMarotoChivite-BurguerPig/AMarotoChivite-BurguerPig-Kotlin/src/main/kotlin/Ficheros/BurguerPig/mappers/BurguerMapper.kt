package mappers

import Ficheros.BurguerPig.mappers.base.IDtoMapper
import Ficheros.BurguerPig.mappers.base.ListMapper
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.dto.BurguerDTO
import Ficheros.BurguerPig.models.dto.BurguerListDto
import java.util.*

class BurguerMapper : IDtoMapper<BurguerDTO, Burguer> {

    override fun toDto(model: Burguer): BurguerDTO {
        return BurguerDTO(
            model.name,
            IngredientListMapper().toDto(model.ingredients).list,
            model.getUUID().toString(),
            model.getPrice().toString()
        )
    }

    override fun toModel(dto: BurguerDTO): Burguer {
        return Burguer(
            dto.name,
            IngredientListMapper().toModelList(dto.ingredients),
            UUID.fromString(dto.uuid),
            dto.price.toDouble()
        )
    }
}

class BurguerListMapper : ListMapper<BurguerDTO, Burguer>(BurguerMapper()) {

    fun toDtoList(modelList: List<Burguer>): BurguerListDto {
        val dtoList = modelList.map { BurguerMapper().toDto(it) }
        return BurguerListDto(dtoList)
    }

    fun toModelList(dtoList: BurguerListDto): List<Burguer> {
        return dtoList.burguers.map { BurguerMapper().toModel(it) }
    }
}


