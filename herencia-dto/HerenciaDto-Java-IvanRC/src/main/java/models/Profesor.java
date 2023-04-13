package models;

public final class Profesor extends Persona {
    String modulo;

    public Profesor(String nombre, String modulo) {
        super(nombre);
        this.modulo = modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "modulo='" + modulo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
