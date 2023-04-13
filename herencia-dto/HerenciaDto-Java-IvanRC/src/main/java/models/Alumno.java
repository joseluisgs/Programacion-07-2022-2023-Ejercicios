package models;

public final class Alumno extends Persona {
    static Integer edad;

    public Alumno(String nombre, Integer edad) {
        super(nombre);
        this.edad = edad;
    }

    public static Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "edad=" + edad +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public static <T> int getEdad(T t) {
        return getEdad();
    }
}
