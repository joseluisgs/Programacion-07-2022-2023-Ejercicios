package repository.hamburgesa;

import models.Hamburgesa;
import repository.base.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface HamburgesaRepository extends CrudRepository<Hamburgesa, UUID> {
    List<Hamburgesa> getByName(String name);
}
