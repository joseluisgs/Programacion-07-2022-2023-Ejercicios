package Ficheros.BurguerPig.mappers;

import Ficheros.BurguerPig.mappers.base.IDtoMapper;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.models.dto.IngredienteDTO;

public class IngredienteMapper implements IDtoMapper<IngredienteDTO, Ingredient> {

    @Override
    public IngredienteDTO toDto(Ingredient model) {
        return new IngredienteDTO(
                model.getName(),
                Double.toString(model.getPrice()),
                Integer.toString(model.getID())
        );
    }

    @Override
    public Ingredient toModel(IngredienteDTO dto) {
        return new Ingredient(
                dto.getName(),
                Double.parseDouble(dto.getPrice()),
                Integer.parseInt(dto.getId())
        );
    }
}

