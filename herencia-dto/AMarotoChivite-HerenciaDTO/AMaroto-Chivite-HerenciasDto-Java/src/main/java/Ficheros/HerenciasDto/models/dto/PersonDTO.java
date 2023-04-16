package Ficheros.HerenciasDto.models.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public abstract sealed class PersonDTO {

    public abstract String getName();

    public abstract int getEdad();

    public abstract String getType();

    @Root(name = "alumno")
    public static final class Alumno extends PersonDTO {

        @Attribute(name = "type")
        private final String type = "Alumno";
        @Element(name = "nombreAlumno")
        private String name;
        @Element(name = "edad")
        private String edad;

        public Alumno() {
            // Constructor sin argumentos requerido para SimpleXML
        }

        public Alumno(
                @Element(name = "nombreAlumno") String name,
                @Element(name = "edad") String edad
        ) {
            this.name = name;
            this.edad = edad;
        }

        @Override
        public String getName() {
            return name;
        }

        public String setName(String name) {
            this.name = name;
            return name;
        }

        @Override
        public int getEdad() {
            return Integer.parseInt(edad);
        }

        public String setEdad(String edad) {
            this.edad = edad;
            return edad;
        }

        @Override
        public String getType() {
            return type;
        }

        public String setType(String type) {
            return type;
        }

        @Override
        public String toString() {
            return "Alumno(name='" + name + "', edad=" + edad + ")";
        }
    }

    @Root(name = "profesor")
    public static final class Profesor extends PersonDTO {

        @Attribute(name = "type")
        private final String type = "Profesor";
        @Element(name = "nombreProfesor")
        private String name;
        @Element(name = "edad")
        private String edad;
        @Element(name = "modulo")
        private String modulo;

        public Profesor() {
            // Constructor sin argumentos requerido por SimpleXML
        }

        public Profesor(
                @Element(name = "nombreProfesor") String name,
                @Element(name = "edad") String edad,
                @Element(name = "modulo") String modulo
        ) {
            this.name = name;
            this.edad = edad;
            this.modulo = modulo;
        }

        @Override
        public String getName() {
            return name;
        }

        public String setName(String name) {
            this.name = name;
            return name;
        }

        @Override
        public int getEdad() {
            return Integer.parseInt(edad);
        }

        public String setEdad(String edad) {
            this.edad = edad;
            return edad;
        }

        @Override
        public String getType() {
            return type;
        }

        public String setType(String type) {
            return type;
        }

        public String getModulo() {
            return modulo;
        }

        @Override
        public String toString() {
            return "Profesor(name='" + name + "', edad=" + edad + ", modulo='" + modulo + "')";
        }
    }
}

