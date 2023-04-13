package org.example.controllers;

import org.example.models.Persona;
import org.example.repositories.persona.PersonaExtension;
import org.example.repositories.persona.PersonaRepository;

import java.util.List;

public class PersonaController implements PersonaExtension {
    private final PersonaRepository repo;
    public PersonaController(PersonaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Persona> getAll() {
        return repo.getAll();
    }

    @Override
    public Persona getById(Integer integer) {
        checkId(integer);
        return repo.getById(integer);
    }

    @Override
    public Persona save(Persona element, boolean storage) {
        checkId(element.getId());
        return repo.save(element, storage);
    }

    @Override
    public void saveAll(List<Persona> elements, boolean storage) {
        elements.forEach(e -> checkId(e.getId()));
        repo.saveAll(elements, storage);
    }

    @Override
    public Persona deleteById(Integer integer) {
        checkId(integer);
        return repo.deleteById(integer);
    }

    @Override
    public Persona update(Persona element) {
        checkId(element.getId());
        return repo.update(element);
    }

    @Override
    public Persona updateById(Integer integer, Persona element) {
        checkId(integer);
        checkId(element.getId());
        return repo.updateById(integer, element);
    }

    private void checkId(Integer id) {
        if (id < 0) throw new IllegalArgumentException("Id no puede ser negativo");
    }
}
