package org.example;

import models.Medicion;
import models.Registro;
import storage.medicion.MedicionStorageServiceCsv;
import storage.medicion.MedicionStorageServiceJson;
import storage.medicion.MedicionStorageServiceXml;
import storage.registros.RegistroStorageServiceJson;
import storage.registros.RegistroStorageServiceXml;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        // Consigo una lista con todos los nombres de los archivos .csv en la carpeta resources.
        String path = ClassLoader.getSystemResource("config.properties").getPath().replace("config.properties", "");
        File file = new File(path);
        List<String> listaFicherosResources = Arrays.stream(file.listFiles()).filter(it -> it.getName().contains(".csv")).map(it -> it.getName()).toList();
        listaFicherosResources.forEach(System.out::println);

        // Junto todas las colleciones en una sola
        List<Medicion> medicionesTotales = new ArrayList<>();

        for(int i=0; i<listaFicherosResources.size(); i++){
            medicionesTotales.addAll(leerFicherosCsv(listaFicherosResources.get(i)));
        }

        // medicionesTotales.forEach(System.out::println);

        Map<LocalDate, List<Medicion>> mapaMedicionesTotalesAgruadasPorDia =
                medicionesTotales.stream().collect(Collectors.groupingBy(it -> it.getDia()));

        // guardarEnFicheroJson(mapaMedicionesTotalesAgruadasPorDia);

        // guardarEnFicheroXml(mapaMedicionesTotalesAgruadasPorDia);

        consultas(medicionesTotales, mapaMedicionesTotalesAgruadasPorDia);

        // registros(medicionesTotales);

    }

    private static void registros(List<Medicion> medicionesTotales) throws IOException {
        System.out.println("Empezamos con los registros, sobre Madrid unicamente, para los ficheros JSON y XML:");
        System.out.println();

        Map<LocalDate, List<Medicion>> mapaRegistrosMadrid = medicionesTotales.stream().filter(it -> it.getProvincia().toLowerCase().contains("madrid"))
                .collect(Collectors.groupingBy(it -> it.getDia()));

        System.out.println(mapaRegistrosMadrid.get(mapaRegistrosMadrid.keySet().stream().findFirst().orElseGet(null)).stream().max(Comparator.comparing(Medicion::getTemperaturaMax)).orElseGet(null));

        System.out.println("Temperatura media:");
        Map<LocalDate, Registro> tempMedia = new HashMap<>();
        mapaRegistrosMadrid.keySet().forEach(k ->
                tempMedia.put(k,
                        new Registro(
                                k,
                                mapaRegistrosMadrid.get(k).stream().collect(Collectors.averagingDouble(Medicion::getTemperaturaMedia))
                        )
                ));
        tempMedia.keySet().forEach(k ->
                System.out.println(k+"--"+tempMedia.get(k))
        );

        System.out.println();

        System.out.println("Temperatura máxima:");
        Map<LocalDate, Registro> tempMax = new HashMap<>();
        mapaRegistrosMadrid.keySet().forEach(k ->
                tempMax.put(k,
                        new Registro(
                                k,
                                mapaRegistrosMadrid.get(k).stream().max(Comparator.comparing(Medicion::getTemperaturaMax)).orElseGet(null).getTemperaturaMax(),
                                mapaRegistrosMadrid.get(k).stream().max(Comparator.comparing(Medicion::getTemperaturaMax)).orElseGet(null).getPoblacion(),
                                mapaRegistrosMadrid.get(k).stream().max(Comparator.comparing(Medicion::getTemperaturaMax)).orElseGet(null).getHoraTempMax()
                        )
                ));
        tempMax.keySet().forEach(k ->
                System.out.println(k+"--"+tempMax.get(k))
        );

        System.out.println();

        System.out.println("Temperatura mínima:");
        Map<LocalDate, Registro> tempMin = new HashMap<>();
        mapaRegistrosMadrid.keySet().forEach(k ->
                tempMin.put(k,
                        new Registro(
                                k,
                                mapaRegistrosMadrid.get(k).stream().min(Comparator.comparing(Medicion::getTemperaturaMin)).orElseGet(null).getTemperaturaMin(),
                                mapaRegistrosMadrid.get(k).stream().min(Comparator.comparing(Medicion::getTemperaturaMin)).orElseGet(null).getPoblacion(),
                                mapaRegistrosMadrid.get(k).stream().min(Comparator.comparing(Medicion::getTemperaturaMin)).orElseGet(null).getHoraTempMax(),
                                null
                        )
                ));
        tempMin.keySet().forEach(k ->
                System.out.println(k+"--"+tempMin.get(k))
        );

        System.out.println();

        System.out.println("Precipitaciones???:");
        Map<LocalDate, Registro> precipitaciones = new HashMap<>();
        mapaRegistrosMadrid.keySet().forEach(k ->
                precipitaciones.put(k,
                        new Registro(
                                k,
                                mapaRegistrosMadrid.get(k).stream().max(Comparator.comparing(Medicion::getPrecipitacion)).orElseGet(null).getPrecipitacion() > 0.0 ? "Si" : "No",
                                mapaRegistrosMadrid.get(k).stream().max(Comparator.comparing(Medicion::getPrecipitacion)).orElseGet(null).getPrecipitacion()
                        )));
        precipitaciones.keySet().forEach(k ->
                System.out.println(k+"--"+precipitaciones.get(k))
        );

        System.out.println();

        List<Map<LocalDate, Registro>> conjuntoDePrecipitaciones = new ArrayList<>();
        conjuntoDePrecipitaciones.add(tempMedia);
        conjuntoDePrecipitaciones.add(tempMax);
        conjuntoDePrecipitaciones.add(tempMin);
        conjuntoDePrecipitaciones.add(precipitaciones);

        System.out.println("Lo meto en el fichero Json:");

        RegistroStorageServiceJson json = new RegistroStorageServiceJson();

        json.saveAll(conjuntoDePrecipitaciones);

        json.loadAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Lo meto en el fichero Xml:");

        RegistroStorageServiceXml xml = new RegistroStorageServiceXml();

        xml.saveAll(conjuntoDePrecipitaciones);

        xml.loadAll().forEach(System.out::println);
    }

    private static void consultas(List<Medicion> medicionesTotales, Map<LocalDate, List<Medicion>> mapaMedicionesTotalesAgruadasPorDia) {
        AtomicInteger contador = new AtomicInteger();

        System.out.println("Empezamos con las consultas:");
        System.out.println();

        System.out.println("Temperatura maxima por dia y lugar(no supe como hacerlo):");

        System.out.println();

        System.out.println("Temperatura minima por dia y lugar(no supe como hacerlo):");

        System.out.println();

        System.out.println("Temperatura maxima por provincia:");

        Map<String, List<Medicion>> mapaAgrupadoPorProvincia = medicionesTotales.stream().collect(Collectors.groupingBy(Medicion::getProvincia));

        List<Double> valores = new ArrayList<>();

        mapaAgrupadoPorProvincia.values().forEach(value ->
                valores.add(
                        value.stream().max(Comparator.comparingDouble(it -> it.getTemperaturaMax())).map(it -> it.getTemperaturaMax()).orElseGet(null)
                )
        );

        Map<String, Double> mapa3 = new HashMap<>();

        contador.set(0);
        mapaAgrupadoPorProvincia.keySet().forEach(k ->
                mapa3.put(
                        k,
                        valores.get(contador.getAndIncrement())
                )
        );

        mapa3.keySet().stream().limit(10).forEach(k ->
                System.out.println(k+"--"+mapa3.get(k))
        );

        System.out.println();

        System.out.println("Temperatura min por provincia:");

        valores.removeAll(valores);

        mapaAgrupadoPorProvincia.values().forEach(value ->
                valores.add(
                        value.stream().min(Comparator.comparingDouble(it -> it.getTemperaturaMin())).map(it -> it.getTemperaturaMin()).orElseGet(null)
                )
        );

        Map<String, Double> mapa4 = new HashMap<>();

        contador.set(0);
        mapaAgrupadoPorProvincia.keySet().forEach(k ->
                mapa4.put(
                        k,
                        valores.get(contador.getAndIncrement())
                )
        );

        mapa4.keySet().stream().limit(10).forEach(k ->
                System.out.println(k+"--"+mapa4.get(k))
        );

        System.out.println();

        System.out.println("Temperatura media por provincia:");

        valores.removeAll(valores);

        mapaAgrupadoPorProvincia.values().forEach(value ->
                valores.add(
                        value.stream().collect(Collectors.averagingDouble(Medicion::getTemperaturaMedia))
                )
        );

        Map<String, Double> mapa5 = new HashMap<>();

        contador.set(0);
        mapaAgrupadoPorProvincia.keySet().forEach(k ->
                mapa5.put(
                        k,
                        valores.get(contador.getAndIncrement())
                )
        );

        mapa5.keySet().stream().limit(10).forEach(k ->
                System.out.println(k+"--"+mapa5.get(k))
        );

        System.out.println();

        System.out.println("Lista de precipitacion media por día y provincia(no supe como hacerlo):");

        System.out.println();

        System.out.println("Numero de lugares en los que llovio por día y provincia(no supe como hacerlo):");

        System.out.println();

        System.out.println("Temperatura media de la provincia de Madrid:");

        Double tempMedia = medicionesTotales.stream().filter(it -> it.getProvincia().toLowerCase().contains("madrid")).collect(Collectors.averagingDouble(Medicion::getTemperaturaMedia));

        System.out.println(tempMedia);

        System.out.println();

        System.out.println("Media de temperatura maxima total:");

        tempMedia = medicionesTotales.stream().collect(Collectors.averagingDouble(Medicion::getTemperaturaMax));

        System.out.println(tempMedia);

        System.out.println();

        System.out.println("Media de temperatura minima total:");

        tempMedia = medicionesTotales.stream().collect(Collectors.averagingDouble(Medicion::getTemperaturaMin));

        System.out.println(tempMedia);

        System.out.println();

        System.out.println("Lugares donde la temperatura maxima a sido captada antes de las 15:00, por día:");

        Map<LocalDate, List<Medicion>> valores1 = medicionesTotales.stream().filter(it -> it.getHoraTempMax().isBefore(LocalTime.of(15, 0, 0))).collect(Collectors.groupingBy(Medicion::getDia));

        Map<LocalDate, List<String>> mapa6 = new HashMap<>();

        valores1.keySet().forEach(k ->
                mapa6.put(
                        k,
                        valores1.get(k).stream().map(it -> it.getPoblacion()).toList()
                )
        );

        mapa6.keySet().stream().limit(10).forEach(k ->
                System.out.println(k+"--"+mapa6.get(k))
        );

        System.out.println();

        System.out.println("Lugares donde la temperatura minima a sido captada despues de las 17:30, por día:");

        Map<LocalDate, List<Medicion>> valores2 = medicionesTotales.stream().filter(it -> it.getHoraTempMin().isAfter(LocalTime.of(17, 30, 0))).collect(Collectors.groupingBy(Medicion::getDia));

        Map<LocalDate, List<String>> mapa7 = new HashMap<>();

        valores2.keySet().forEach(k ->
                mapa7.put(
                        k,
                        valores2.get(k).stream().map(it -> it.getPoblacion()).toList()
                )
        );

        mapa7.keySet().stream().limit(10).forEach(k ->
                System.out.println(k+"--"+mapa7.get(k))
        );

    }

    private static void guardarEnFicheroXml(Map<LocalDate, List<Medicion>> mapaMedicionesTotalesAgruadasPorDia) throws IOException {
        MedicionStorageServiceXml xml = new MedicionStorageServiceXml();
        xml.saveAll(mapaMedicionesTotalesAgruadasPorDia);

        Map<LocalDate, List<Medicion>> xmlMap = xml.loadAll();
        xmlMap.keySet().forEach(k ->
                System.out.println(k+"--"+xmlMap.get(k))
        );
    }

    private static void guardarEnFicheroJson(Map<LocalDate, List<Medicion>> mapaMedicionesTotalesAgruadasPorDia) throws IOException {
        MedicionStorageServiceJson json = new MedicionStorageServiceJson();
        json.saveAll(mapaMedicionesTotalesAgruadasPorDia);

        Map<LocalDate, List<Medicion>> jsonMap = json.loadAll();
        jsonMap.keySet().forEach(k ->
                System.out.println(k+"--"+jsonMap.get(k))
        );
    }

    private static List<Medicion> leerFicherosCsv(String name) throws IOException {
        MedicionStorageServiceCsv csv = new MedicionStorageServiceCsv(name);
        return csv.loadAll();
    }
}