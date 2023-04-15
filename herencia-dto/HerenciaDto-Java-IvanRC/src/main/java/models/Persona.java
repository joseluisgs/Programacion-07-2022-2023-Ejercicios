package models;

public abstract class Persona {
    static String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static <T> int getNombreLength(T t) {
        return nombre.length();
    }
}
