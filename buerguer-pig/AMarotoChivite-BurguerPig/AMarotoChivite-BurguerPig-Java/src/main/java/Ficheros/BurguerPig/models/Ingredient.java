package Ficheros.BurguerPig.models;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private Double price;
    private Integer id;

    private static int counter = 0;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = autoCount();
    }

    public Ingredient(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ingredient(name='" + name + "', price=" + price + ", id=" + id + ")";
    }

    private static int autoCount() {
        return counter++;
    }
}
