package mapper.accidente;

import dto.accidente.AccidenteDto;
import dto.accidente.ListaAccidentesDto;
import mapper.distrito.DistritoMapper;
import mapper.lesividad.LesividadMapper;
import model.Accidente;
import utils.Utils;

import java.util.List;

public class AccidenteMapper {

    public static AccidenteDto toDto(Accidente accidente){
        return new AccidenteDto(
                accidente.getNumeroExpediente(),
                accidente.getFecha().toString(),
                accidente.getHora().toString(),
                accidente.getLocalizacion(),
                Utils.getValorNumericoToString(accidente.getNumeroCalle()),
                DistritoMapper.toDto(accidente.getDistrito()),
                accidente.getTipoAccidente(),
                accidente.getEstadoMeteorologico(),
                accidente.getTipoDeVehiculo(),
                accidente.getTipoDePersona(),
                accidente.getRangoEdad(),
                accidente.getSexo(),
                LesividadMapper.toDto(accidente.getLesividad()),
                Utils.getValorNumericoToString(accidente.getLongitud()),
                Utils.getValorNumericoToString(accidente.getAltitud()),
                Utils.getPositivoToString(accidente.getEsPositivaEnAlchol(), "S", "N"),
                Utils.getPositivoToString(accidente.getEsPositivaEnDrogas(), "1", "NULL")
    );
    }

    public static Accidente toAccidente(AccidenteDto accidenteDto){
        return new Accidente(
                accidenteDto.getNumeroExpediente(),
                Utils.toLocalDateDDMMYYYY(accidenteDto.getFecha()),
                Utils.toLocalTime(accidenteDto.getHora()),
                accidenteDto.getLocalizacion(),
                Utils.valorNumericoToInt(accidenteDto.getNumeroCalle()),
                DistritoMapper.toDistrito(accidenteDto.getDistrito()),
                accidenteDto.getTipoAccidente(),
                accidenteDto.getEstadoMeteorologico(),
                accidenteDto.getTipoDeVehiculo(),
                accidenteDto.getTipoDePersona(),
                accidenteDto.getRangoEdad(),
                accidenteDto.getSexo(),
                LesividadMapper.toLesividad(accidenteDto.getLesividad()),
                Utils.valorNumericoToDouble(accidenteDto.getLongitud()),
                Utils.valorNumericoToDouble(accidenteDto.getAltitud()),
                accidenteDto.getEsPositivaEnAlchol().equals("S"),
                accidenteDto.getEsPositivaEnDrogas().equals("1")
        );
    }

    public static List<Accidente> toAccidentes(ListaAccidentesDto lista){
        return lista.getAccidentesDto().stream().map(a -> toAccidente(a)).toList();
    }

    public static ListaAccidentesDto toAccidentesDto(List<Accidente> lista){
        return new ListaAccidentesDto(
                lista.stream().map(AccidenteMapper::toDto).toList()
        );
    }
}
