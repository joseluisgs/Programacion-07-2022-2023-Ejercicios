package Ficheros.BurguerPig.repositories;

import Ficheros.BurguerPig.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RepositoryIngredient {
    private List<Ingredient> repository = new ArrayList<>();

    // Agrego ingredientes en el repository sin límite de stock, para ahorrarnos introducirlos desde el Main
    // a través del controlador
    public RepositoryIngredient() {
        repository.add(new Ingredient("Tomate", 0.4));
        repository.add(new Ingredient("Lechuga", 0.2));
        repository.add(new Ingredient("Cerdo", 2.2));
        repository.add(new Ingredient("Pollo", 1.5));
        repository.add(new Ingredient("Atún", 2.1));
        repository.add(new Ingredient("Mostaza", 0.5));
        repository.add(new Ingredient("Ketchup", 0.5));
        repository.add(new Ingredient("Queso", 1.3));
    }

    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(repository);
    }
}
