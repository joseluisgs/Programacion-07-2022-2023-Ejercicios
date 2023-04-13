package org.example.models;

import org.example.dto.PersonaDto;

import java.io.Serializable;

public class Alumno extends Persona implements Serializable {
    private final int edad;

    public Alumno(String nombre, int edad) {
        super(nombre);
        this.edad = edad;
    }

    public Alumno(Integer id, String nombre, int edad) {
        super(id, nombre);
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", edad=" + edad +
                '}';
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public PersonaDto toDto() {
        return new PersonaDto(
                Integer.toString(getId()),
                getNombre(),
                "Alumno",
                Integer.toString(getEdad()),
                "null"
        );
    }
}
