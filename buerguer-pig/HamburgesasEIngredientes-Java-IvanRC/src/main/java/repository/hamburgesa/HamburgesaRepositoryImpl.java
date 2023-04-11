package repository.hamburgesa;

import models.Hamburgesa;
import storage.hamburgesa.HamburgesaStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HamburgesaRepositoryImpl implements HamburgesaRepository{

    private final HamburgesaStorageService storage;

    public HamburgesaRepositoryImpl(HamburgesaStorageService storage) {
        this.storage = storage;
    }

    private ArrayList<Hamburgesa> hamburgesas = new ArrayList<>();

    @Override
    public List<Hamburgesa> getAll() throws Exception {
        loadAll();
        return hamburgesas;
    }

    @Override
    public Hamburgesa getById(UUID id) throws Exception {
        loadAll();
        return hamburgesas.stream().filter(hamburesa -> hamburesa.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Hamburgesa add(Hamburgesa entity) throws Exception {
        Hamburgesa hamburgesa = getById(entity.getId());
        if(hamburgesa == null){
            hamburgesas.add(entity);
            saveAll();
            return entity;
        }
        return null;
    }

    @Override
    public Hamburgesa update(Hamburgesa entity) {
        return null;
    }

    @Override
    public Hamburgesa remove(UUID id) {
        return null;
    }

    @Override
    public List<Hamburgesa> getByName(String name) {
        return null;
    }


    private void loadAll() throws Exception {
        hamburgesas.clear();
        storage.loadAll().forEach(hamburgesa ->
                hamburgesas.add(hamburgesa)
        );
    }

    private void saveAll() throws Exception {
        storage.saveAll(hamburgesas);
    }
}
