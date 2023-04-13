package repository.ingrediente;

import models.Ingrediente;
import repository.base.CrudRepository;
import java.util.List;


public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer> {
    List<Ingrediente> getByName(String name);
}
