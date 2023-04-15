package factory;

import models.Alumno;
import models.Persona;
import models.Profesor;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PersonasFactory {

    private static PersonasFactory instance = null;
    public PersonasFactory() {}
    public static PersonasFactory getInstance() {
        if (instance == null) instance = new PersonasFactory();
        return instance;
    }

    public List<Persona> createPersonasRandom(int numPersonas){
        List<Persona> listaPersonas = new ArrayList<>();
        String[] nombres = {"Iván", "Alberto", "Roberto", "Erfesto", "Nefesto", "Detesto", "Alex", "Alexa", "Alejandrino", "Nefer", "Romeo", "Julieta", "Ash", "Dante", "Nero"};
        for(int i = 0; i<numPersonas; i++){
            int chance = (int)(Math.random()*99+1);
            if(chance <= 10){
                String[] modulos = {"Programacion", "Entornos"};
                listaPersonas.add(
                        new Profesor(
                                nombres[(int)(Math.random()* nombres.length)],
                                modulos[(int)(Math.random()* modulos.length)]
                        )
                );
            }else{
                int edad = (int)(Math.random()*22+18);;
                listaPersonas.add(
                        new Alumno(
                                nombres[(int)(Math.random()* nombres.length)],
                                edad
                        )
                );
            }
        }
        return listaPersonas;
    }

}
