package org.example.models;

import org.example.dto.PersonaDto;

public class Profesor extends Persona{
    private final String modulo;

    public Profesor(String nombre, String modulo) {
        super(nombre);
        this.modulo = modulo;
    }

    public Profesor(Integer id, String nombre, String modulo) {
        super(id, nombre);
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", modulo='" + modulo + '\'' +
                '}';
    }

    public String getModulo() {
        return modulo;
    }

    @Override
    public PersonaDto toDto() {
        return new PersonaDto(Integer.toString(getId()), getNombre(), "Profesor", "null", getModulo());
    }
}
