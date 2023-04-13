package mapper.distrito;

import dto.distrito.DistritoDto;
import model.Distrito;
import utils.Utils;

public class DistritoMapper {

    public static DistritoDto toDto(Distrito distrito){
        return new DistritoDto(
                Utils.getValorNumericoToString(distrito.getId()),
                distrito.getNombre()
    );
    }

    public static Distrito toDistrito(DistritoDto distritoDto){
        return new Distrito(
                Utils.valorNumericoToInt(distritoDto.getId()),
                distritoDto.getNombre()
    );
    }

}
