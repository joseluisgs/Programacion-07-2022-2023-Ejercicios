package org.example;

import dto.ListaPersonasDto;
import factory.PersonasFactory;
import mapper.PersonaMapper;
import models.Alumno;
import models.Persona;
import models.Profesor;
import storage.personas.PersonaStorageServiceCsv;
import storage.personas.PersonaStorageServiceJson;
import storage.personas.PersonaStorageServiceXml;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Persona> personas = PersonasFactory.getInstance().createPersonasRandom(20);
        personas.forEach(System.out::println);

        System.out.println();

        ListaPersonasDto personasDto = PersonaMapper.toPersonasDto(personas);
        personasDto.getPersonaDtos().forEach(System.out::println);

        System.out.println();

        System.out.println("Ahora las guardo en el fichero JSON:");
        PersonaStorageServiceJson json = new PersonaStorageServiceJson();
        json.saveAll(personas);
        System.out.println();
        System.out.println("Volvemos a leer las personas del archivo JSON:");
        json.loadAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Ahora las guardo en el fichero XML:");
        PersonaStorageServiceXml xml = new PersonaStorageServiceXml();
        xml.saveAll(personas);
        System.out.println();
        System.out.println("Volvemos a leer las personas del archivo XML:");
        xml.loadAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Ahora las guardo en el fichero CSV:");
        PersonaStorageServiceCsv csv = new PersonaStorageServiceCsv();
        csv.saveAll(personas);
        System.out.println();
        System.out.println("Volvemos a leer las personas del archivo CSV:");
        csv.loadAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Ahora empiezo con las consultas:");

        System.out.println();

        System.out.println("Profesor más mayor, pero como el profesor no tiene edad, cojo el profesor con el nombre más largo:");
        Profesor profesor1 = (Profesor) personas.stream().filter(p -> p instanceof Profesor).max(Comparator.comparingInt(Profesor::getNombreLength)).orElseGet(null);
        System.out.println(profesor1);

        System.out.println();

        System.out.println("Alumno más joven:");
        Alumno alumno1 = (Alumno) personas.stream().filter(p -> p instanceof Alumno).max(Comparator.comparingInt(Alumno::getEdad)).orElseGet(null);
        System.out.println(alumno1);

        System.out.println();

        System.out.println("Media de edad de los alumnos:");
        Double decimal1 = personas.stream().filter(p -> p instanceof Alumno).collect(Collectors.averagingDouble(Alumno::getEdad));
        System.out.println(decimal1);

        System.out.println();

        System.out.println("Media longitud de nombres:");
        Double decimal2 = personas.stream().collect(Collectors.averagingDouble(Persona::getNombreLength));
        System.out.println(decimal2);

        System.out.println();

        System.out.println("Listado agrupados segun el tipo:");
        Map<String, List<Persona>> mapa1 = personas.stream().collect(Collectors.groupingBy(p -> p instanceof Profesor ? "Profesores" : "Alumnos"));
        mapa1.keySet().forEach(k ->
                System.out.println(k+"--"+mapa1.get(k))
        );
    }
}