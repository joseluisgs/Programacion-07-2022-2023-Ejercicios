package mapper;

import dto.hamburgesa.HamburgesaDto;
import dto.hamburgesa.ListaHamburgesaDto;
import dto.ingrediente.IngredienteDto;
import models.Hamburgesa;
import models.Ingrediente;

import java.util.List;
import java.util.UUID;

public class HamburgesaMapper {
    public static HamburgesaDto toDto(Hamburgesa hamburgesa){
        return new HamburgesaDto(
                String.valueOf(hamburgesa.getId()),
                hamburgesa.getNombre(),
                hamburgesa.getIngredientes().stream().map(i -> IngredienteMapper.toDto(i)).toList()
        );
    }

    public static Hamburgesa toHamburgesa(HamburgesaDto hamburgesaDto){
        return new Hamburgesa(
                UUID.fromString(hamburgesaDto.getId()),
                hamburgesaDto.getNombre(),
                hamburgesaDto.getIngredientes().stream().map(i -> IngredienteMapper.toIngrediente(i)).toList()
        );
    }

    public static ListaHamburgesaDto toDto(List<Hamburgesa> hamburgesas){
        return new ListaHamburgesaDto(hamburgesas.stream().map(hamburgesa -> toDto(hamburgesa)).toList());
    }

    public static List<Hamburgesa> toHamburgesas(ListaHamburgesaDto hamburgesas){
        return hamburgesas.hamburgesasDto.stream().map(i -> toHamburgesa(i)).toList();
    }
}
