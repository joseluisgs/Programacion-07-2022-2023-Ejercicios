package org.example.factories;

import org.example.models.Alumno;
import org.example.models.Persona;
import org.example.models.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonaFactory {
    //Singleton
    private static PersonaFactory instance = null;
    public static PersonaFactory getInstance() {
        if (instance == null) {
            instance = new PersonaFactory();
        }
        return instance;
    }

    public List<Persona> getRdnPersonas(){
        List<Persona> personas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personas.add(
                getRdnPersona()
            );
        }
        return personas;
    }

    public Persona getRdnPersona(){
        int rdn = (int) (Math.random() * 100);
        Random random = new Random();
        if (rdn < 10) {
            return new Alumno(
                getRdnNombre(),
                (random.nextInt(40-18) + 18)
            );
        } else {
            return new Profesor(
                getRdnNombre(),
                getRdnModulo()
            );
        }
    }

    private String getRdnNombre(){
        String[] nombres = {"Pepe", "Juan", "Ana"};
        return nombres[(int) (Math.random() * nombres.length)];
    }
    private String getRdnModulo(){
        String[] modulos = {"Programación", "Entornos"};
        return modulos[(int) (Math.random() * modulos.length)];
    }
}
