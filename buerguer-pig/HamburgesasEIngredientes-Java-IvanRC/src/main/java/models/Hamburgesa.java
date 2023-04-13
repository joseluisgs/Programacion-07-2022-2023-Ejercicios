package models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Hamburgesa implements Serializable {
    private final UUID id;
    private final String nombre;
    private final List<Ingrediente> ingredientes;
    private final double precio;

    public Hamburgesa(UUID id, String nombre, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = ingredientes.stream().mapToDouble(ingrediente -> ingrediente.getPrecio()).sum();
    }

    public int getNumberOfIngredientes(){ return ingredientes.size(); }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Hamburgesa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ingredientes=" + ingredientes +
                ", precio=" + precio +
                '}';
    }
}
