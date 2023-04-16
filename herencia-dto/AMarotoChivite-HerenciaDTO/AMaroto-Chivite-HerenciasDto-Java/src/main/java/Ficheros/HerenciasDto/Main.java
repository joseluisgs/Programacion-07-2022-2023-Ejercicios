package Ficheros.HerenciasDto;


import Ficheros.HerenciasDto.config.ConfigApp;
import Ficheros.HerenciasDto.controllers.ControllerPrincipal;
import Ficheros.HerenciasDto.controllers.ControllerQueries;
import Ficheros.HerenciasDto.models.dto.PersonDTO;
import Ficheros.HerenciasDto.repositories.IRepositoryToWriteRead;
import Ficheros.HerenciasDto.repositories.RepositoryToWriteRead;
import Ficheros.HerenciasDto.storages.StorageCSV;
import Ficheros.HerenciasDto.storages.StorageGson;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConfigApp.init();

        // Decidimos el tipo de fichero al que exportaremos
        IRepositoryToWriteRead repository = decisionTypeStorage();
        ControllerPrincipal controllerPrincipalJava = new ControllerPrincipal(repository);
        controllerPrincipalJava.saveFile();

        // Leemos del fichero creado anteriormente
        List<PersonDTO> listModels = controllerPrincipalJava.readFile();

        // Consultas
        ControllerQueries controllerQueries = new ControllerQueries(listModels);
        finalInformQueries(controllerQueries);
    }

    public static IRepositoryToWriteRead decisionTypeStorage() {
        while (true) {
            System.out.println("Elige el tipo de fichero con el que quieres realizar la gestión: ");
            System.out.println("1: En Gson JSON");
            System.out.println("2: En CSV");
            System.out.println("0: Salir");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    return new RepositoryToWriteRead(new StorageGson());
                case "2":
                    return new RepositoryToWriteRead(new StorageCSV());
                case "0":
                    System.exit(0);
            }
        }
    }

    public static void finalInformQueries(ControllerQueries controllerQueries) {
        System.out.println("1. Profesor más anciano");
        System.out.println(controllerQueries.profesorMoreAncient());
        System.out.println();

        System.out.println("2. Alumno más joven");
        System.out.println(controllerQueries.alumnoLessAncient());
        System.out.println();

        System.out.println("3. Media de edad alumnos");
        System.out.println(controllerQueries.averageAgeAlumno());
        System.out.println();

        System.out.println("4. Media de longitud de nombre");
        System.out.println(controllerQueries.averageLongitudeName());
        System.out.println();

        System.out.println("5. Agrupo por tipo las personas");
        controllerQueries.groupByType().forEach((type, list) -> {
            List<PersonDTO> listaPersonas = list; // .stream().map(PersonDTO::getName).collect(Collectors.toList()); // Si queremos solo los nombres
            System.out.println("Tipo: " + type + " -> Lista: " + listaPersonas);
        });
        System.out.println();
    }
}
