package Ficheros.BurguerPig.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Burguer implements Serializable {
    private final String name;
    private final List<Ingredient> ingredients;
    private final UUID id;
    private double priceCalculate;

    public Burguer(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.id = UUID.randomUUID();
        this.priceCalculate = ingredients.stream().mapToDouble(Ingredient::getPrice).sum() * 1.5;
    }

    public Burguer(String name, List<Ingredient> ingredients, UUID id, double priceCalculate) {
        this.name = name;
        this.ingredients = ingredients;
        this.id = id;
        this.priceCalculate = priceCalculate;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public UUID getUuid() {
        return id;
    }

    public double getPrice() {
        return priceCalculate;
    }

    @Override
    public String toString() {
        return "Burguer{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", id=" + id +
                ", priceCalculate=" + priceCalculate +
                '}';
    }

    public UUID getUUID() {
        return id;
    }
}
