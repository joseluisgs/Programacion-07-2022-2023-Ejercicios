package org.example.repositories.persona;

import org.example.models.Persona;
import org.example.services.storage.persona.PersonaStorageService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PersonaRepositoryMap implements PersonaRepository{
    private final Map<Integer, Persona> personas = new LinkedHashMap<>();
    private final PersonaStorageService storageService;

    public PersonaRepositoryMap(PersonaStorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public List<Persona> getAll() {
        return personas.values().stream().toList();
    }

    @Override
    public Persona getById(Integer integer) {
        return personas.get(integer);
    }

    @Override
    public Persona save(Persona element, boolean storage) {
        personas.put(element.getId(), element);
        if (storage) downgrade();
        return element;
    }

    @Override
    public void saveAll(List<Persona> elements, boolean storage) {
        elements.forEach(e -> save(e, storage));
    }

    @Override
    public Persona deleteById(Integer integer) {
        return personas.remove(integer);
    }

    @Override
    public Persona update(Persona element) {
        return updateById(element.getId(), element);
    }

    @Override
    public Persona updateById(Integer integer, Persona element) {
        if (personas.get(integer) == null) return null;
        personas.put(integer, element);
        return element;
    }

    @Override
    public List<Persona> upgrade() {
        personas.clear();
        var load = storageService.loadAll();
        saveAll(load, false);
        return load;
    }

    @Override
    public List<Persona> downgrade() {
        return storageService.saveAll(getAll());
    }
}
