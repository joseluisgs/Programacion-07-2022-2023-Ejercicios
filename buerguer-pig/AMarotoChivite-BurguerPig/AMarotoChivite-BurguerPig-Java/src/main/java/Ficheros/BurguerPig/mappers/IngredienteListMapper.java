package Ficheros.BurguerPig.mappers;

import Ficheros.BurguerPig.mappers.base.IDtoMapper;
import Ficheros.BurguerPig.mappers.base.ListMapper;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.models.dto.IngredienteDTO;
import Ficheros.BurguerPig.models.dto.IngredienteListDto;

import java.util.List;
import java.util.stream.Collectors;

public class IngredienteListMapper extends ListMapper<IngredienteDTO, Ingredient> {

    public IngredienteListMapper(IDtoMapper<IngredienteDTO, Ingredient> objectMapper) {
        super(objectMapper);
    }

    public IngredienteListDto toDtoList(List<Ingredient> modelList) {
        List<IngredienteDTO> dtoList = modelList.stream()
                .map(ingredient -> new IngredienteMapper().toDto(ingredient))
                .collect(Collectors.toList());
        return new IngredienteListDto(dtoList);
    }

    public List<Ingredient> toModelList(List<IngredienteDTO> dtoList) {
        return dtoList.stream()
                .map(dto -> new IngredienteMapper().toModel(dto))
                .collect(Collectors.toList());
    }
}
