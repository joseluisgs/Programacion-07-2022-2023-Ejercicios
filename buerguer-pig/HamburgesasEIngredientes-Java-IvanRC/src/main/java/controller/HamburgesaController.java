package controller;

import exception.hamburgesaException.HamburgesaException;
import models.Hamburgesa;
import repository.hamburgesa.HamburgesaRepository;
import repository.ingrediente.IngredienteRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static validator.Validator.validateHamburgesa;

public class HamburgesaController {

    private final HamburgesaRepository hamburgesaRepository;

    private final IngredienteRepository ingredienteRepository;

    public HamburgesaController(HamburgesaRepository hamburgesaRepository, IngredienteRepository ingredienteRepository) {
        this.hamburgesaRepository = hamburgesaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Hamburgesa> getAll() throws Exception {
        return hamburgesaRepository.getAll();
    }

    public Hamburgesa getById(String id) throws Exception {
        Hamburgesa hamburgesa = hamburgesaRepository.getById(UUID.fromString(id));
        if(hamburgesa !=null){
            return hamburgesa;
        }else {
            throw new HamburgesaException.HamburgesaNotFoundException(id);
        }
    }

    public Hamburgesa add(Hamburgesa entity) throws Exception {
        validateHamburgesa(entity);
        Hamburgesa hamburgesa = hamburgesaRepository.add(entity);
        if(hamburgesa !=null){
            return hamburgesa;
        }else {
            throw new HamburgesaException.HamburgesaAlreadyExistsException(""+entity.getId());
        }
    }

    public Hamburgesa update(Hamburgesa entity) {
        return null;
    }

    public Hamburgesa remove(Integer id) {
        return null;
    }

    public List<Hamburgesa> getByName(String name) {
        return null;
    }
}
