package Ficheros.BurguerPig.mappers;

import Ficheros.BurguerPig.mappers.base.IDtoMapper;
import Ficheros.BurguerPig.mappers.base.ListMapper;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.models.dto.BurguerDTO;
import Ficheros.BurguerPig.models.dto.IngredienteDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BurguerMapper implements IDtoMapper<BurguerDTO, Burguer> {

    @Override
    public BurguerDTO toDto(Burguer model) {
        return new BurguerDTO(
                model.getName(),
                //model.getIngredients().stream().map(it -> new IngredienteDTO(it.getName(), String.valueOf(it.getPrice()), String.valueOf(it.getID()))).collect(Collectors.toList()),
                new IngredienteListMapper(new IngredienteMapper()).toDto(model.getIngredients()).list,
                model.getUuid().toString(),
                String.valueOf(model.getPrice())
        );
    }

    @Override
    public Burguer toModel(BurguerDTO dto) {
        return new Burguer(
                dto.getName(),
                //dto.getIngredients().stream().map((it -> new Ingredient(it.getName(),Double.parseDouble(it.getPrice()), Integer.parseInt(it.getId())))).collect(Collectors.toList()),
                new IngredienteListMapper(new IngredienteMapper()).toModelList(dto.getIngredients()),
                UUID.fromString(dto.getUuid()),
                Double.parseDouble(dto.getPrice())
        );
    }
}


