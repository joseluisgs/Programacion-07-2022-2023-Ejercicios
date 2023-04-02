package org.example.dto;

import org.example.models.Alumno;
import org.example.models.Persona;
import org.example.models.Profesor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "persona")
public class PersonaDto implements Serializable {
    public PersonaDto(String id, String nombre, String tipo, String edad, String modulo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.modulo = modulo;
    }

    @Attribute(name = "id")
    private String id;
    @Element(name = "nombre")
    private String nombre;
    @Element(name = "tipo")
    private String tipo;
    @Element(name = "edad", required = false)
    private String edad;
    @Element(name = "modulo", required = false)
    private String modulo;

    public Persona toClass(){
        return switch (tipo) {
            case "Alumno" -> new Alumno(Integer.parseInt(id), nombre, Integer.parseInt(edad));
            case "Profesor" -> new Profesor(Integer.parseInt(id), nombre, modulo);
            default -> throw new RuntimeException("Tipo de persona no reconocido: " + tipo);
        };
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public String getEdad() {
        return edad;
    }
    public String getModulo() {
        return modulo;
    }
}
