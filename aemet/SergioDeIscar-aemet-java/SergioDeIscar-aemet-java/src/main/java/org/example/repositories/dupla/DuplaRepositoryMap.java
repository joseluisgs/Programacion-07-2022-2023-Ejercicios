package org.example.repositories.dupla;

import org.example.models.Dupla;
import org.example.services.storage.dupla.DuplaStorageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

public class DuplaRepositoryMap implements DuplaRepository{
    private final DuplaStorageService storageService;
    public DuplaRepositoryMap(DuplaStorageService storageService) {
        this.storageService = storageService;
    }

    //private final Map<LocalDate, Map<String, Dupla>> duplas = new LinkedHashMap<>();
    private final Map<String, Dupla> duplas = new LinkedHashMap<>();

    @Override
    public List<Dupla> getAll() {
        upgrade();
        return duplas.values().stream().toList();
    }

    @Override
    public Dupla getById(String s) {
        upgrade();
        return duplas.get(s);
    }

    @Override
    public Dupla save(Dupla element, boolean storage) {
        duplas.put(element.getPoblacion() + " " + element.getDay(), element);
        if (storage) downgrade();
        return element;
    }

    @Override
    public void saveAll(List<Dupla> elements, boolean storage) {
        elements.forEach(e -> save(e, storage));
    }

    @Override
    public Dupla deleteById(String s) {
        var element = duplas.get(s);
        if (element == null) return null;
        duplas.remove(s);
        downgrade();
        return element;
    }

    @Override
    public Dupla update(Dupla element) {
        return updateById(element.getPoblacion() + " " + element.getDay(), element);
    }

    @Override
    public Dupla updateById(String s, Dupla element) {
        if (duplas.get(s) == null) return null;
        duplas.put(s, element);
        downgrade();
        return element;
    }

    @Override
    public List<Dupla> upgrade() {
        duplas.clear();
        var load = storageService.loadAll();
        saveAll(load, false);
        return load;
    }

    @Override
    public List<Dupla> downgrade() {
        return storageService.saveAll(duplas.values().stream().toList());
    }

    @Override
    public Map<LocalDate, Map<String, Dupla>> maxTemPorLugar() {
        upgrade();
        Map<LocalDate, Map<String, Optional<Dupla>>> optional = duplas.values().stream()
                .collect(groupingBy(
                        Dupla::getDay,
                        groupingBy(
                                Dupla::getPoblacion,
                                Collectors.maxBy(Comparator.comparing(Dupla::getTemMax))
                        ))
                );
        // Cambiar Optional<Dupla> por Dupla
        return removeOptional(optional);
        // Java es peor que Kotlin en este aspecto
    }

    @Override
    public Map<LocalDate, Map<String, Dupla>> minTemPorLugar() {
        upgrade();
        Map<LocalDate, Map<String, Optional<Dupla>>> optional = duplas.values().stream()
                .collect(groupingBy(
                        Dupla::getDay,
                        groupingBy(
                                Dupla::getPoblacion,
                                Collectors.minBy(Comparator.comparing(Dupla::getTemMin))
                        ))
                );
        // Cambiar Optional<Dupla> por Dupla
        return removeOptional(optional);
    }

    private Map<LocalDate, Map<String, Dupla>> removeOptional(Map<LocalDate, Map<String, Optional<Dupla>>> optional) {
        return optional.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, e -> e.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        e2 -> e2.getValue().get()
                                ))
                ));
    }

    @Override
    public Map<String, Dupla> maxTemPorProvincia() {
        upgrade();
        return duplas.values().stream()
                .collect(Collectors.toMap(
                        Dupla::getProvincia,
                        e -> e,
                        (e1, e2) -> e1.getTemMax() > e2.getTemMax() ? e1 : e2
                ));
    }

    @Override
    public Map<String, Dupla> minTemPorProvincia() {
        upgrade();
        return duplas.values().stream()
                .collect(Collectors.toMap(
                        Dupla::getProvincia,
                        e -> e,
                        (e1, e2) -> e1.getTemMin() < e2.getTemMin() ? e1 : e2
                ));
    }

    @Override
    public Map<String, Double> mediaPorProvincia() {
        upgrade();
        return duplas.values().stream()
                .collect(Collectors.groupingBy(
                        Dupla::getProvincia,
                        Collectors.averagingDouble(e -> e.getTemMax() - e.getTemMin())
                ));
    }

    @Override
    public Map<LocalDate, Map<String, Double>> mediaPrecipitacionPorDiaYProvincia() {
        upgrade();
        return duplas.values().stream()
                .collect(groupingBy(
                        Dupla::getDay,
                        groupingBy(
                                Dupla::getProvincia,
                                Collectors.averagingDouble(Dupla::getPrecipitacion)
                        )
                ));
    }

    @Override
    public Double mediaTemMadrid() {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getProvincia().equals("Madrid"))
                .mapToDouble(e -> e.getTemMax() - e.getTemMin())
                .average()
                .orElse(0.0);
    }

    @Override
    public Double mediaTemMax() {
        upgrade();
        return duplas.values().stream()
                .mapToDouble(Dupla::getTemMax)
                .average()
                .orElse(0.0);
    }

    @Override
    public Double mediaTemMin() {
        upgrade();
        return duplas.values().stream()
                .mapToDouble(Dupla::getTemMin)
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<LocalDate, List<String>> maxTemAntes() {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getTimeMax().isBefore(LocalTime.of(15, 0)))
                .collect(groupingBy(
                        Dupla::getDay,
                        mapping(Dupla::getPoblacion, Collectors.toList())
                ));
    }

    @Override
    public Map<LocalDate, List<String>> minTemDespues() {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getTimeMin().isAfter(LocalTime.of(17, 30)))
                .collect(groupingBy(
                        Dupla::getDay,
                        mapping(Dupla::getPoblacion, Collectors.toList())
                ));
    }

    @Override
    public Dupla maxTemMadrid(LocalDate day) {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getPoblacion().equals("Madrid") && e.getDay().equals(day))
                .max(Comparator.comparing(Dupla::getTemMax))
                .orElse(null);
    }

    @Override
    public Dupla minTemMadrid(LocalDate day) {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getPoblacion().equals("Madrid") && e.getDay().equals(day))
                .min(Comparator.comparing(Dupla::getTemMin))
                .orElse(null);
    }

    @Override
    public Double mediaPrecipitacionMadrid(LocalDate day) {
        upgrade();
        return duplas.values().stream()
                .filter(e -> e.getPoblacion().equals("Madrid") && e.getDay().equals(day))
                .mapToDouble(Dupla::getPrecipitacion)
                .average()
                .orElse(0.0);
    }
}
