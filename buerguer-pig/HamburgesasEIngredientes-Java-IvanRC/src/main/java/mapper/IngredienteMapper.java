package mapper;

import dto.ingrediente.IngredienteDto;
import dto.ingrediente.ListaIngredienteDto;
import models.Ingrediente;

import java.util.List;

public class IngredienteMapper {

    public static IngredienteDto toDto(Ingrediente ingrediente){
        return new IngredienteDto(
                String.valueOf(ingrediente.getId()),
                ingrediente.getNombre(),
                String.valueOf(ingrediente.getPrecio())
        );
    }

    public static Ingrediente toIngrediente(IngredienteDto ingredienteDto){
        return new Ingrediente(
                Integer.parseInt(ingredienteDto.getId()),
                ingredienteDto.getNombre(),
                Double.parseDouble(ingredienteDto.getPrecio())
        );
    }

    public static ListaIngredienteDto toDto(List<Ingrediente> ingredientes){
        return new ListaIngredienteDto(ingredientes.stream().map(ingrediente -> toDto(ingrediente)).toList());
    }

    public static List<Ingrediente> toIngredientes(ListaIngredienteDto ingredientes){
        return ingredientes.ingredientesDto.stream().map(i -> toIngrediente(i)).toList();
    }

}
