package Ficheros.Aemet.controllers;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.utils.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerQueries {
    private final List<Aemet> listOfStorageReadFile;
    // Agrupar por día y ubicación (utilizando pares)
    private Map<Pair<LocalDate, String>, List<Aemet>> mapByDateAndLocate;
    // Agrupar por provincia
    private Map<Pair<LocalDate, String>, List<Aemet>> mapByDateAndProvincia;

    public ControllerQueries(List<Aemet> listOfStorageReadFile) {
        this.listOfStorageReadFile = listOfStorageReadFile;
        initMaps();
    }

    public void initMaps() {
        Set<Pair<LocalDate, String>> uniqueDateAndLocate = new HashSet<>();
        Set<Pair<LocalDate, String>> uniqueDateAndProvincia = new HashSet<>();

        this.mapByDateAndLocate = listOfStorageReadFile.stream()
                .filter(aemet -> uniqueDateAndLocate.add(new Pair<>(aemet.getDate(), aemet.getNombrePoblacion())))
                .collect(Collectors.groupingBy(aemet -> new Pair<>(aemet.getDate(), aemet.getNombrePoblacion())));

        this.mapByDateAndProvincia = listOfStorageReadFile.stream()
                .filter(aemet -> uniqueDateAndProvincia.add(new Pair<>(aemet.getDate(), aemet.getNombreProvincia())))
                .collect(Collectors.groupingBy(aemet -> new Pair<>(aemet.getDate(), aemet.getNombreProvincia())));
    }

    public Map<Pair<LocalDate, String>, Double> temperatureMaxPerDayAndLocate() {

        Map<Pair<LocalDate, String>, Double> mapByMaxTemperatures = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndLocate.entrySet()) {
            Double maxTemp = entry.getValue().stream().mapToDouble(Aemet::getTemperaturaMaxima).max().orElse(0.0);
            mapByMaxTemperatures.put(entry.getKey(), maxTemp);
        }
        return mapByMaxTemperatures;
    }

    public Map<Pair<LocalDate, String>, Double> temperatureMinPerDayAndLocate() {

        Map<Pair<LocalDate, String>, Double> mapByMinTemperatures = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndLocate.entrySet()) {
            Double maxTemp = entry.getValue().stream().mapToDouble(Aemet::getTemperaturaMinima).min().orElse(0.0);
            mapByMinTemperatures.put(entry.getKey(), maxTemp);
        }
        return mapByMinTemperatures;
    }

    public Map<Pair<LocalDate, String>, List<String>> temperatureMaxByProvincia() {
        // Value: List(día, lugar, valor y momento)
        Map<Pair<LocalDate, String>, List<String>> map = new HashMap<>();

        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            List<String> tempList = new ArrayList<>();
            if (aemetList != null && !aemetList.isEmpty()) {
                Aemet aemet = aemetList.get(0); // Tomamos el primer elemento de la lista, ya que no habrá más de uno
                tempList.add(aemet.getDate().toString());
                tempList.add(aemet.getNombrePoblacion());
                tempList.add(aemet.getTemperaturaMaxima().toString());
                tempList.add(aemet.getHoraTemperaturaMaxima().toString());
            }
            map.put(entry.getKey(), tempList);
        }
        return map;
    }

    public Map<Pair<LocalDate, String>, List<String>> temperatureMinByProvincia() {

        // Value: List(día, lugar, valor y momento)
        Map<Pair<LocalDate, String>, List<String>> map = new HashMap<>();

        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            List<String> tempList = new ArrayList<>();
            if (aemetList != null && !aemetList.isEmpty()) {
                Aemet aemet = aemetList.get(0); // Tomamos el primer elemento de la lista, ya que no habrá más de uno
                tempList.add(aemet.getDate().toString());
                tempList.add(aemet.getNombrePoblacion());
                tempList.add(aemet.getTemperaturaMinima().toString());
                tempList.add(aemet.getHoraTemperaturaMaxima().toString());
            }
            map.put(entry.getKey(), tempList);
        }
        return map;
    }

    public Map<Pair<LocalDate, String>, List<String>> temperatureAverageByProvincia() {

        // Value:  (día, lugar y valor)
        Map<Pair<LocalDate, String>, List<String>> map = new HashMap<>();

        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            List<String> tempList = new ArrayList<>();
            if (aemetList != null && !aemetList.isEmpty()) {
                Aemet aemet = aemetList.get(0); // Tomamos el primer elemento de la lista, ya que no habrá más de uno
                tempList.add(aemet.getDate().toString());
                tempList.add(aemet.getNombrePoblacion());
                tempList.add(aemet.getTemperatureAverage().toString());
            }
            map.put(entry.getKey(), tempList);
        }
        return map;
    }

    public List<String> getListPrecipitacionAverageByDayAndProvincia() {
        // Calculamos la precipitación media de cada grupo
        Map<Pair<LocalDate, String>, Double> mapAverages = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            Double maxTemp = entry.getValue().stream().mapToDouble(Aemet::getPrecipitacion).average().orElse(0.0);
            if (!maxTemp.equals(0.0)) {
                mapAverages.put(entry.getKey(), maxTemp);
            }
        }

        // Creamos una lista de strings con la información
        return mapAverages.entrySet().stream()
                .map(entry -> "Día: " + entry.getKey().getFirst() +
                        ", Provincia: " + entry.getKey().getSecond() +
                        ", PrecipitaciónMedia: " + entry.getValue())
                .collect(Collectors.toList());
    }

    public List<String> numLocatesWasRainyByDayAndProvincia() {
        // Calculamos el número de veces ha llovido por grupo
        Map<Pair<LocalDate, String>, Long> mapRainy = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            Long count = aemetList.stream().filter(it -> it.getPrecipitacion() > 0.0).count();
            if (count > 0) {
                mapRainy.put(entry.getKey(), count);
            }
        }
        // Creamos una lista de strings con la información
        return mapRainy.entrySet().stream()
                .map(entry -> "Día: " + entry.getKey().getFirst() +
                        ", Provincia: " + entry.getKey().getSecond() +
                        ", VecesLlovido: " + entry.getValue())
                .collect(Collectors.toList());
    }

    public List<String> temperatureAverageFilterByOneProvincia(String provincia) {
        // Calcular precipitación media para cada grupo y almacenar en mapa
        Map<Pair<LocalDate, String>, Double> mapAverages = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            Double tempMedia = aemetList.stream()
                    .filter(it -> it.getNombreProvincia().equals(provincia))
                    .mapToDouble(it -> (it.getTemperaturaMaxima() + it.getTemperaturaMinima()) / 2)
                    .average()
                    .orElse(0.0);
            if (!tempMedia.equals(0.0)) {
                mapAverages.put(entry.getKey(), tempMedia);
            }
        }

        // Creamos una lista de strings con la información
        return mapAverages.entrySet().stream()
                .map(entry -> "Día: " + entry.getKey().getFirst() +
                        ", Provincia: " + entry.getKey().getSecond() +
                        ", PrecipitaciónMedia: " + entry.getValue())
                .collect(Collectors.toList());
    }

    public List<String> temperatureMaxAverage() {
        // Calcular precipitación media para cada grupo y almacenar en mapa
        Map<Pair<LocalDate, String>, Double> mapAverages = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            Double tempMedia = aemetList.stream()
                    .mapToDouble(it -> it.getTemperaturaMaxima())
                    .average()
                    .orElse(0.0);
            if (!tempMedia.equals(0.0)) {
                mapAverages.put(entry.getKey(), tempMedia);
            }
        }

        // Creamos una lista de strings con la información
        return mapAverages.entrySet().stream()
                .map(entry -> "Día: " + entry.getKey().getFirst() +
                        ", Provincia: " + entry.getKey().getSecond() +
                        ", PrecipitaciónMedia: " + entry.getValue())
                .collect(Collectors.toList());
    }

    public List<String> temperatureMinAverage() {
        // Calcular precipitación media para cada grupo y almacenar en mapa
        Map<Pair<LocalDate, String>, Double> mapAverages = new HashMap<>();
        for (Map.Entry<Pair<LocalDate, String>, List<Aemet>> entry : mapByDateAndProvincia.entrySet()) {
            List<Aemet> aemetList = entry.getValue();
            Double tempMedia = aemetList.stream()
                    .mapToDouble(it -> it.getTemperaturaMinima())
                    .average()
                    .orElse(0.0);
            if (!tempMedia.equals(0.0)) {
                mapAverages.put(entry.getKey(), tempMedia);
            }
        }

        // Creamos una lista de strings con la información
        return mapAverages.entrySet().stream()
                .map(entry -> "Día: " + entry.getKey().getFirst() +
                        ", Provincia: " + entry.getKey().getSecond() +
                        ", PrecipitaciónMedia: " + entry.getValue())
                .collect(Collectors.toList());
    }

    // Antes de las 15:00 por día
    public List<String> locatesWhereTempMaxWasBeforeAnHourByDay() {
        List<Aemet> listFilterTime = listOfStorageReadFile.stream()
                .filter(aemet -> aemet.getHoraTemperaturaMaxima().isBefore(LocalTime.of(15, 0)))
                .collect(Collectors.toList());

        // Creamos una lista de strings con la información
        return listFilterTime.stream().map(aemet ->
                "Día: " + aemet.getDate() + ", Provincia: " + aemet.getNombreProvincia()
                        + ", Poblacion: " + aemet.getNombrePoblacion()
                        + ", HoraTemperaturaMax: " + aemet.getHoraTemperaturaMaxima().getHour() + "h"
        ).collect(Collectors.toList());
    }

    public List<String> locatesWhereTempMinWasAfterAnHourByDay() {
        List<Aemet> listFilterTime = listOfStorageReadFile.stream()
                .filter(aemet -> aemet.getHoraTemperaturaMinima().isAfter(LocalTime.of(17, 30)))
                .collect(Collectors.toList());

        // Creamos una lista de strings con la información
        return listFilterTime.stream()
                .map(aemet -> "Día: " + aemet.getDate() + ", Provincia: " + aemet.getNombreProvincia()
                        + ", Poblacion: " + aemet.getNombrePoblacion()
                        + ", HoraTemperaturaMin: " + aemet.getHoraTemperaturaMinima().toString() + "h")
                .collect(Collectors.toList());
    }
}
