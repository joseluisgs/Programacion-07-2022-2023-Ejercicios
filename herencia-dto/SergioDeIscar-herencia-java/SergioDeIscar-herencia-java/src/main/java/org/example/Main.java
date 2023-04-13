package org.example;

import org.example.controllers.PersonaController;
import org.example.factories.PersonaFactory;
import org.example.models.Persona;
import org.example.repositories.persona.PersonaRepositoryMap;
import org.example.services.storage.persona.PersonaFileCsv;
import org.example.services.storage.persona.PersonaFileJson;
import org.example.services.storage.persona.PersonaFileXml;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PersonaController> controllers = List.of(
            new PersonaController(
                new PersonaRepositoryMap(
                    PersonaFileCsv.getInstance()
                )
            ),
            new PersonaController(
                new PersonaRepositoryMap(
                    PersonaFileJson.getInstance()
                )
            ),
            new PersonaController(
                new PersonaRepositoryMap(
                    PersonaFileXml.getInstance()
                )
            )
        );
        List<Persona> personas = PersonaFactory.getInstance().getRdnPersonas();
        saveAllControllers(controllers, personas);
        System.out.println("Tienen el mismo contenido: " + checkControllers(controllers));
    }

    private static Boolean checkControllers(List<PersonaController> controllers) {
        return controllers.stream().allMatch(c -> c.getAll().equals(controllers.get(0).getAll()));
    }

    private static void saveAllControllers(List<PersonaController> controllers, List<Persona> personas){
        controllers.forEach(c -> c.saveAll(personas, true));
    }
}