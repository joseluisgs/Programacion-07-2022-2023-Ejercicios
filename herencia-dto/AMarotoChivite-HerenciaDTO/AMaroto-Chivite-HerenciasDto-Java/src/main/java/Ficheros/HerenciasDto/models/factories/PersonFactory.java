package Ficheros.HerenciasDto.models.factories;

import Ficheros.HerenciasDto.models.dto.PersonDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PersonFactory {
    private final List<String> modulos = Arrays.asList("Programación", "Entornos");
    private final List<String> nombres = Arrays.asList(
            "Juan", "Ana", "Pedro", "María", "Luis", "Carla", "David", "Elena",
            "Fernando", "Sara", "Javier", "Lucía", "Miguel", "Pilar", "Rosa", "Sofía",
            "Diego", "Jorge", "Laura", "Raquel"
    );

    private PersonDTO createRandomPersona() {
        double random = Math.random();
        String name = nombres.get(new Random().nextInt(nombres.size()));
        int edad = new Random().nextInt(23) + 18;

        if (random < 0.1) {
            String modulo = modulos.get(new Random().nextInt(modulos.size()));
            return new PersonDTO.Profesor(name, Integer.toString(edad), modulo);
        } else {
            return new PersonDTO.Alumno(name, Integer.toString(edad));
        }
    }

    public List<PersonDTO> createPersonas(int n) {
        List<PersonDTO> personas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            personas.add(createRandomPersona());
        }
        return personas;
    }
}

