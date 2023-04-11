package mapper.lesividad;


import dto.lesividad.LesividadDto;
import model.Lesividad;
import utils.Utils;

public class LesividadMapper {
    public static LesividadDto toDto(Lesividad distrito){
        return new LesividadDto(
                Utils.getValorNumericoToString(distrito.getId()),
                distrito.getLesividad()
        );
    }

    public static Lesividad toLesividad(LesividadDto distritoDto){
        return new Lesividad(
                Utils.valorNumericoToInt(distritoDto.getId()),
                distritoDto.getLesividad()
        );
    }
}
