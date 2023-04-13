package org.example.controllers;

import org.example.models.Informe;
import org.example.repositories.informe.InformeExtension;
import org.example.repositories.informe.InformeRepository;

import java.time.LocalDate;
import java.util.List;

public class InformeController implements InformeExtension {
    private final InformeRepository repo;
    public InformeController(InformeRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<Informe> getAll() {
        return repo.getAll();
    }

    @Override
    public Informe getById(LocalDate localDate) {
        return repo.getById(localDate);
    }

    @Override
    public Informe save(Informe element, boolean storage) {
        return repo.save(element, storage);
    }

    @Override
    public void saveAll(List<Informe> elements, boolean storage) {
        repo.saveAll(elements, storage);
    }

    @Override
    public Informe deleteById(LocalDate localDate) {
        return repo.deleteById(localDate);
    }

    @Override
    public Informe update(Informe element) {
        return repo.update(element);
    }

    @Override
    public Informe updateById(LocalDate localDate, Informe element) {
        return repo.updateById(localDate, element);
    }
}
