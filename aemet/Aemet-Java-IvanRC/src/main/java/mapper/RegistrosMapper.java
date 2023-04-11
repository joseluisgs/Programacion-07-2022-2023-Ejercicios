package mapper;

import dto.*;
import models.Registro;
import utils.Utils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class RegistrosMapper {

    public static dto.RegistroDto toDto(Registro registro){
        dto.RegistroDto registroDto;
        if(registro.getTemperaturaMedia() != null){
            registroDto = new RegistroDto(
                    registro.getDia().toString(),
                    registro.getTemperaturaMedia().toString()
            );
        }else{
            if(registro.getTemperaturaMax() != null){
                registroDto = new RegistroDto(
                        registro.getDia().toString(),
                        registro.getTemperaturaMax().toString(),
                        registro.getLugar(),
                        registro.getMomento().toString()
                );
            }else{
                if(registro.getTemperaturaMin() != null){
                    registroDto = new RegistroDto(
                            registro.getDia().toString(),
                            registro.getTemperaturaMin().toString(),
                            registro.getLugar(),
                            registro.getMomento().toString(),
                            null
                    );
                }else{
                    registroDto = new RegistroDto(
                            registro.getDia().toString(),
                            registro.getHuboPrecipitacion(),
                            registro.getPrecipitacion().toString()
                    );
                }
            }
        }
        return registroDto;
    }

    public static Registro toRegistro(RegistroDto registroDto){
        Registro registro;
        if(registroDto.getTemperaturaMedia() != null){
            registro = new Registro(
                    Utils.toLocalDate(registroDto.getDia()),
                    Double.parseDouble(registroDto.getTemperaturaMedia())
            );
        }else{
            if(registroDto.getTemperaturaMax() != null){
                registro = new Registro(
                        Utils.toLocalDate(registroDto.getDia()),
                        Double.parseDouble(registroDto.getTemperaturaMax()),
                        registroDto.getLugar(),
                        Utils.toLocalTime(registroDto.getMomento())
                );
            }else{
                if(registroDto.getTemperaturaMin() != null){
                    registro = new Registro(
                            Utils.toLocalDate(registroDto.getDia()),
                            Double.parseDouble(registroDto.getTemperaturaMin()),
                            registroDto.getLugar(),
                            Utils.toLocalTime(registroDto.getMomento()),
                            null
                    );
                }else{
                    registro = new Registro(
                            Utils.toLocalDate(registroDto.getDia()),
                            registroDto.getHuboPrecipitacion(),
                            Double.parseDouble(registroDto.getPrecipitacion())
                    );
                }
            }
        }
        return registro;
    }

    public static InformesDto toDto(List<Map<LocalDate, Registro>> mapa){
        List<List<String>> claves = mapa.stream().map(it -> it.keySet().stream().map(key -> key.toString()).toList()).toList();
        List<List<RegistroDto>> valores = mapa.stream().map(it -> it.values().stream().map(value -> toDto(value)).toList()).toList();
        Map<String, RegistroDto> mapas = new HashMap<>();
        List<MapaRegistroDto> lista = new ArrayList<>();
        Map<String, RegistroDto> mapa1 = new HashMap<>();
        Map<String, RegistroDto> mapa2 = new HashMap<>();
        Map<String, RegistroDto> mapa3 = new HashMap<>();
        Map<String, RegistroDto> mapa4 = new HashMap<>();
        for(int i = 0; i<claves.size(); i++){
            for(int j = 0; j<claves.get(i).size(); j++){
                if(i == 0) {
                    mapa1.put(claves.get(i).get(j), valores.get(i).get(j));
                }else{
                    if(i == 1){
                        mapa2.put(claves.get(i).get(j), valores.get(i).get(j));
                    }else{
                        if(i == 2){
                            mapa3.put(claves.get(i).get(j), valores.get(i).get(j));
                        }else{
                            mapa4.put(claves.get(i).get(j), valores.get(i).get(j));
                        }
                    }
                }
            }
        }
        lista.add(new MapaRegistroDto(mapa1));
        lista.add(new MapaRegistroDto(mapa2));
        lista.add(new MapaRegistroDto(mapa3));
        lista.add(new MapaRegistroDto(mapa4));
        return new InformesDto(
            lista
        );
    }

    public static List<Map<LocalDate, Registro>> toRegistros(InformesDto informe){
        List<List<LocalDate>> claves = informe.getInformes().stream().map(it -> it.getMapaRegistros())
                .map(it -> it.keySet().stream().map(key -> Utils.toLocalDate(key)).toList()).toList();
        List<List<Registro>> valores = informe.getInformes().stream().map(it -> it.getMapaRegistros())
                .map(it -> it.values().stream().map(value -> toRegistro(value)).toList()).toList();
        Map<LocalDate, Registro> mapas = new java.util.HashMap<>(Collections.emptyMap());
        List<Map<LocalDate, Registro>> lista = new ArrayList<>();
        Map<LocalDate, Registro> mapa1 = new HashMap<>();
        Map<LocalDate, Registro> mapa2 = new HashMap<>();
        Map<LocalDate, Registro> mapa3 = new HashMap<>();
        Map<LocalDate, Registro> mapa4 = new HashMap<>();
        for(int i = 0; i<claves.size(); i++){
            for(int j = 0; j<claves.get(i).size(); j++){
                if(i == 0) {
                    mapa1.put(claves.get(i).get(j), valores.get(i).get(j));
                }else{
                    if(i == 1){
                        mapa2.put(claves.get(i).get(j), valores.get(i).get(j));
                    }else{
                        if(i == 2){
                            mapa3.put(claves.get(i).get(j), valores.get(i).get(j));
                        }else{
                            mapa4.put(claves.get(i).get(j), valores.get(i).get(j));
                        }
                    }
                }
            }
        }
        lista.add(mapa1);
        lista.add(mapa2);
        lista.add(mapa3);
        lista.add(mapa4);
        return lista;
    }

}
