package org.example.models;


import org.example.dto.PersonaDto;

public abstract class Persona {
    protected static int count = 0;
    private final int id;
    private final String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
        id = count++;
    }
    public Persona(Integer id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public abstract PersonaDto toDto();

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
}
