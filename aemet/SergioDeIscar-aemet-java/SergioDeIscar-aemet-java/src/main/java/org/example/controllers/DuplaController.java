package org.example.controllers;

import org.example.models.Dupla;
import org.example.repositories.dupla.DuplaExtension;
import org.example.repositories.dupla.DuplaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DuplaController implements DuplaExtension {
    private final DuplaRepository repo;
    public DuplaController(DuplaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Dupla> getAll() {
        return repo.getAll();
    }

    @Override
    public Dupla getById(String s) {
        return repo.getById(s);
    }

    @Override
    public Dupla save(Dupla element, boolean storage) {
        return repo.save(element, storage);
    }

    @Override
    public void saveAll(List<Dupla> elements, boolean storage) {
        var paco = elements.stream().filter(Objects::isNull).count();
        repo.saveAll(elements, storage);
    }

    @Override
    public Dupla deleteById(String s) {
        return repo.deleteById(s);
    }

    @Override
    public Dupla update(Dupla element) {
        return repo.update(element);
    }

    @Override
    public Dupla updateById(String s, Dupla element) {
        return repo.updateById(s, element);
    }

    @Override
    public Map<LocalDate, Map<String, Dupla>> maxTemPorLugar() {
        return repo.maxTemPorLugar();
    }

    @Override
    public Map<LocalDate, Map<String, Dupla>> minTemPorLugar() {
        return repo.minTemPorLugar();
    }

    @Override
    public Map<String, Dupla> maxTemPorProvincia() {
        return repo.maxTemPorProvincia();
    }

    @Override
    public Map<String, Dupla> minTemPorProvincia() {
        return repo.minTemPorProvincia();
    }

    @Override
    public Map<String, Double> mediaPorProvincia() {
        return repo.mediaPorProvincia();
    }

    @Override
    public Map<LocalDate, Map<String, Double>> mediaPrecipitacionPorDiaYProvincia() {
        return repo.mediaPrecipitacionPorDiaYProvincia();
    }

    @Override
    public Double mediaTemMadrid() {
        return repo.mediaTemMadrid();
    }

    @Override
    public Double mediaTemMax() {
        return repo.mediaTemMax();
    }

    @Override
    public Double mediaTemMin() {
        return repo.mediaTemMin();
    }

    @Override
    public Map<LocalDate, List<String>> maxTemAntes() {
        return repo.maxTemAntes();
    }

    @Override
    public Map<LocalDate, List<String>> minTemDespues() {
        return repo.minTemDespues();
    }

    @Override
    public Dupla maxTemMadrid(LocalDate day) {
        return repo.maxTemMadrid(day);
    }

    @Override
    public Dupla minTemMadrid(LocalDate day) {
        return repo.minTemMadrid(day);
    }

    @Override
    public Double mediaPrecipitacionMadrid(LocalDate day) {
        return repo.mediaPrecipitacionMadrid(day);
    }
}
