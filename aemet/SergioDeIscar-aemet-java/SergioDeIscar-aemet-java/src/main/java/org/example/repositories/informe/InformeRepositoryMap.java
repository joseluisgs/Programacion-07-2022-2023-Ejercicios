package org.example.repositories.informe;

import org.example.models.Informe;
import org.example.services.storage.informe.InformeStorageService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InformeRepositoryMap implements InformeRepository{
    private final InformeStorageService storageService;

    public InformeRepositoryMap(InformeStorageService storageService) {
        this.storageService = storageService;
    }
    private Map<LocalDate, Informe> informes = new LinkedHashMap<>();

    @Override
    public List<Informe> getAll() {
        upgrade();
        return informes.values().stream().toList();
    }

    @Override
    public Informe getById(LocalDate localDate) {
        return informes.get(localDate);
    }

    @Override
    public Informe save(Informe element, boolean storage) {
        informes.put(element.getDay(), element);
        if (storage) downgrade();
        return element;
    }

    @Override
    public void saveAll(List<Informe> elements, boolean storage) {
        elements.forEach(e -> save(e, storage));
    }

    @Override
    public Informe deleteById(LocalDate localDate) {
        return informes.remove(localDate);
    }

    @Override
    public Informe update(Informe element) {
        return updateById(element.getDay(), element);
    }

    @Override
    public Informe updateById(LocalDate localDate, Informe element) {
        if (getById(localDate) == null) return null;
        informes.put(localDate, element);
        downgrade();
        return element;
    }

    @Override
    public List<Informe> upgrade() {
        informes.clear();
        var load = storageService.loadAll();
        saveAll(load, false);
        return load;
    }

    @Override
    public List<Informe> downgrade() {
        return storageService.saveAll(informes.values().stream().toList());
    }
}
