package controller;

import exception.ingredienteException.IngredienteException;
import models.Ingrediente;
import repository.ingrediente.IngredienteRepository;

import java.io.IOException;
import java.util.List;

import static validator.Validator.validateIngrediente;
import static validator.Validator.validateNumber;

public class IngredienteController{

    private final IngredienteRepository repository;

    public IngredienteController(IngredienteRepository repository) {
        this.repository = repository;
    }

    public List<Ingrediente> getAll() throws Exception {
        return repository.getAll();
    }

    public Ingrediente getById(String id) throws Exception {
        validateNumber(id, new IngredienteException.IngredienteBadRequestException(id), 1);
        Ingrediente ingrediente = repository.getById(Integer.parseInt(id));
        if(ingrediente !=null){
            return ingrediente;
        }else {
            throw new IngredienteException.IngredienteNotFoundException(id);
        }
    }

    public Ingrediente add(Ingrediente entity) throws Exception {
        validateIngrediente(entity);
        Ingrediente ingrediente = repository.add(entity);
        if(ingrediente !=null){
            return ingrediente;
        }else {
            throw new IngredienteException.IngredienteAlreadyExistsException(""+entity.getId());
        }
    }

    public Ingrediente update(Ingrediente entity) {
        return null;
    }

    public Ingrediente remove(Integer id) {
        return null;
    }

    public List<Ingrediente> getByName(String name) {
        return null;
    }
}
