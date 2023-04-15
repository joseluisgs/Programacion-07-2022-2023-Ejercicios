package mapper;

import dto.ListaMedicionesDto;
import dto.MapaMedicionesDto;
import dto.MedicionDto;
import models.Medicion;
import utils.Utils;

import java.time.LocalDate;
import java.util.*;

public class MedicionesMapper {
    public static MedicionDto toDto(Medicion medicion){
        return new MedicionDto(
                medicion.getPoblacion(),
                medicion.getProvincia(),
                medicion.getTemperaturaMax().toString(),
                medicion.getHoraTempMax().toString(),
                medicion.getTemperaturaMin().toString(),
                medicion.getHoraTempMin().toString(),
                medicion.getPrecipitacion().toString(),
                medicion.getDia().toString()
        );
    }

    public static Medicion toMedicion(MedicionDto medicionDto){
        return new Medicion(
                medicionDto.getPoblacion(),
                medicionDto.getProvincia(),
                Double.parseDouble(medicionDto.getTemperaturaMax()),
                Utils.toLocalTime(medicionDto.getHoraTempMax()),
                Double.parseDouble(medicionDto.getTemperaturaMin()),
                Utils.toLocalTime(medicionDto.getHoraTempMin()),
                Double.parseDouble(medicionDto.getPrecipitacion()),
                Utils.toLocalDate(medicionDto.getDia())
        );
    }

    public static MapaMedicionesDto toMedicionesDto(Map<LocalDate, List<Medicion>> mapa){
        List<String> claves = mapa.keySet().stream().map(it -> it.toString()).toList();
        List<ListaMedicionesDto> valores = mapa.values().stream().map(it -> new ListaMedicionesDto(
                it.stream().map(medicion -> toDto(medicion)).toList()
        )).toList();
        Map<String, ListaMedicionesDto> mapas = new HashMap<>(Collections.emptyMap());
        for(int i = 0; i<claves.size(); i++){
            mapas.put(claves.get(i), valores.get(i));
        }
        return new MapaMedicionesDto(
                mapas
        );
    }

    public static Map<LocalDate, List<Medicion>> toMediciones(MapaMedicionesDto mapaDto){
        List<LocalDate> claves = mapaDto.getMapaMediciones().keySet().stream().map(it -> Utils.toLocalDate(it)).toList();
        List<List<Medicion>> valores = mapaDto.getMapaMediciones().values().stream().map(it ->
                it.getMediciones().stream().map(medicionDto -> toMedicion(medicionDto)).toList()
        ).toList();
        Map<LocalDate, List<Medicion>> mapas = new HashMap<>(Collections.emptyMap());
        for(int i = 0; i<claves.size(); i++){
            mapas.put(claves.get(i), valores.get(i));
        }
        return mapas;
    }
}
