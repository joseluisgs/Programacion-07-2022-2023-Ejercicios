package repository.ingrediente;

import models.Ingrediente;
import storage.ingrediente.IngredienteStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteRepositoryImpl implements IngredienteRepository {

    private final IngredienteStorageService storage;

    public IngredienteRepositoryImpl(IngredienteStorageService storage) {
        this.storage = storage;
    }

    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();

    @Override
    public List<Ingrediente> getAll() throws Exception {
        loadAll();
        return ingredientes;
    }

    @Override
    public Ingrediente getById(Integer id) throws Exception {
        loadAll();
        return ingredientes.stream().filter(ingrediente -> ingrediente.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Ingrediente add(Ingrediente entity) throws Exception {
        Ingrediente ingrediente = getById(entity.getId());
        if(ingrediente == null){
            ingredientes.add(entity);
            saveAll();
            return entity;
        }
        return null;
    }

    @Override
    public Ingrediente update(Ingrediente entity) {
        return null;
    }

    @Override
    public Ingrediente remove(Integer id) {
        return null;
    }

    @Override
    public List<Ingrediente> getByName(String name) {
        return null;
    }


    private void loadAll() throws Exception {
        ingredientes.clear();
        storage.loadAll().forEach(ingrediente ->
            ingredientes.add(ingrediente)
        );
    }

    private void saveAll() throws Exception {
        storage.saveAll(ingredientes);
    }
}
