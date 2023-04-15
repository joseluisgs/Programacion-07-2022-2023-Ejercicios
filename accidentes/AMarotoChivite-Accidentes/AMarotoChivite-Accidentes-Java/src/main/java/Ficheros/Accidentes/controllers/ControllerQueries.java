package Ficheros.Accidentes.controllers;

import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.utils.Triple;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static Ficheros.Accidentes.utils.ParseLocalDate.parseFecha;
import static java.util.stream.Collectors.groupingBy;

public class ControllerQueries {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ControllerQueries.class);
    private final List<AccidenteDto> listOfStorageReadFile;
    private final Map<String, List<AccidenteDto>> mapByDistrito;

    public ControllerQueries(List<AccidenteDto> listOfStorageReadFile) {
        this.listOfStorageReadFile = listOfStorageReadFile;

        this.mapByDistrito = listOfStorageReadFile.stream()
                .collect(groupingBy(it -> it.getDistrito()));
                /* OTRA FORMA (reflexiva): .collect(Collectors.groupingBy(AccidenteDto::getDistrito)); */
        logger.debug("ControllerQueries: CARGANDO CONSULTAS");
    }

    public List<AccidenteDto> getAccidentesOnlyAlcoholOrDrugs() {
        return listOfStorageReadFile.stream()
                .filter(accidente -> !"NULL".equals(accidente.getPositivaDroga()) || !"N".equals(accidente.getPositivaAlcohol()))
                .collect(Collectors.toList());
    }

    public int numAccidentesOnlyAlcoholAndDrugs() {
        return (int) listOfStorageReadFile.stream()
                .filter(a -> !"NULL".equals(a.getPositivaDroga()) && !"N".equals(a.getPositivaAlcohol()))
                .count();
    }

    public Map<String, List<AccidenteDto>> accidentesBySexo() {
        return listOfStorageReadFile.stream()
                .collect(Collectors.groupingBy(AccidenteDto::getSexo));
    }

    public Map<String, List<AccidenteDto>> accidentesByMonths() {
        return listOfStorageReadFile.stream()
                .collect(Collectors.groupingBy(dto -> dto.getFecha().substring(3, 5)));
    }

    public Map<String, List<AccidenteDto>> accidentesByVehiculos() {
        return listOfStorageReadFile.stream()
                .collect(Collectors.groupingBy(AccidenteDto::getTipoVehiculo));
    }

    // EN LEGANÉS
    public List<AccidenteDto> accidentesByLocalizacion(String localizacion) {
        return listOfStorageReadFile.stream()
                .filter(dto -> dto.getLocalizacion().contains(localizacion.toUpperCase(Locale.getDefault())))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> numAccidentesByDistrito() {
        return mapByDistrito.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }

    public List<AccidenteDto> estadisticasByDistrito() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, List<AccidenteDto>> accidentesByDistritoOrderDesc(int numeroAccidentersParaVisualizar) {
        throw new UnsupportedOperationException("Not implemented yet");
/*
        return mapByDistrito.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .sorted(Comparator.comparing(AccidenteDto::getFecha).reversed())
                        .limit(numeroAccidentersParaVisualizar)
                        .collect(Collectors.toList())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));*/
    }

    public List<AccidenteDto> accidentesEnFinDeSemanaAndNoche() {
        return listOfStorageReadFile.stream()
                .filter(accidente -> {
                    LocalDate fecha = parseFecha(accidente.getFecha());
                    boolean esFinDeSemana = fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY;
                    DateTimeFormatter flexibleFormatter = DateTimeFormatter.ofPattern("[H:]mm[:ss][ a]");
                    LocalTime hora = LocalTime.parse(accidente.getHora(), flexibleFormatter);
                    return esFinDeSemana && (hora.isAfter(LocalTime.of(19, 59)) || hora.isBefore(LocalTime.of(6, 0)));
                })
                .collect(Collectors.toList());
    }

    public List<AccidenteDto> accidentesEnFinDeSemanaAndNocheAndPositiveAlcohol() {
        List<AccidenteDto> listFinDeSemanaNoche = accidentesEnFinDeSemanaAndNoche();

        return listFinDeSemanaNoche.stream()
                .filter(accidente -> !accidente.getPositivaDroga().equals("NULL") && !accidente.getPositivaAlcohol().equals("N"))
                .collect(Collectors.toList());
    }

    public List<AccidenteDto> accidentesWhereDead() {
        return listOfStorageReadFile.stream()
                .filter(accidente -> accidente.getLesividad().contains("Fallecido"))
                .collect(Collectors.toList());
    }

    public Triple<Boolean, String, String> isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend() {
        Map<String, Integer> numAccidentesByDistrito = numAccidentesByDistrito();
        String distritoMoreAccidentes = Collections.max(numAccidentesByDistrito.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();

        String distritoMoreAccidentesWeekend = mapByDistrito.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .filter(accidente -> parseFecha(accidente.getFecha()).getDayOfWeek() == DayOfWeek.SATURDAY
                                || parseFecha(accidente.getFecha()).getDayOfWeek() == DayOfWeek.SUNDAY)
                        .collect(Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        if (distritoMoreAccidentes.equals(distritoMoreAccidentesWeekend)) {
            return new Triple<>(true, distritoMoreAccidentes, distritoMoreAccidentesWeekend);
        } else {
            return new Triple<>(false, distritoMoreAccidentes, distritoMoreAccidentesWeekend);
        }
    }

    public int numAccidentesWherePositiveAlcholoOrDrugsAndDeads() {
        return (int) listOfStorageReadFile.stream()
                .filter(accidente -> (!"NULL".equals(accidente.getPositivaDroga()) || !"N".equals(accidente.getPositivaAlcohol())) && accidente.getLesividad().contains("Fallecido"))
                .count();
    }

    public List<AccidenteDto> numAccidentesWhereHitPeople() {
        return listOfStorageReadFile.stream()
                .filter(accidente -> accidente.getTipoAccidente().contains("persona"))
                .collect(Collectors.toList());
    }

    public Map<String, List<AccidenteDto>> accidentesByMeteorologia() {
        return listOfStorageReadFile.stream()
                .collect(Collectors.groupingBy(AccidenteDto::getEstadoMeteorologico));
    }

    public List<AccidenteDto> accidentesWhereHitAnimal() {
        return listOfStorageReadFile.stream()
                .filter(accidente -> accidente.getTipoAccidente().contains("animal"))
                .collect(Collectors.toList());
    }
}
