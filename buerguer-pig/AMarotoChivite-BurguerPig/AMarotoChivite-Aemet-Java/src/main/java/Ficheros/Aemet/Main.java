package Ficheros.Aemet;

import Ficheros.Aemet.config.ConfigApp;
import Ficheros.Aemet.controllers.ControllerPrincipal;
import Ficheros.Aemet.controllers.ControllerQueries;
import Ficheros.Aemet.controllers.ControllerToWrite;
import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.repositories.RepositoryToWrite;
import Ficheros.Aemet.repositories.RepositoryToWriteRead;
import Ficheros.Aemet.storages.StorageCSV;
import Ficheros.Aemet.storages.StorageGson;
import Ficheros.Aemet.storages.StorageXml;
import Ficheros.Aemet.utils.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kotlin.io.ConsoleKt.readln;

public class Main {
    public static void main(String[] args) {
        ConfigApp.init();

        // Lista de los items leídos desde el CSV en conjunto para poder exportar a XML o JSON
        // De esta menera nos ahorramos que JSON o XML lean, solo escriban desde esta lista!
        List<Aemet> listModels;

        ControllerPrincipal controllerNest = new ControllerPrincipal(new RepositoryToWriteRead(new StorageCSV()));
        // Leemos los ficheros y sacamos la lista general de todos los datos en conjunto
        listModels = controllerNest.readInFile();

        // Introducimos los datos leídos en el repository para poder crear un CSV
        controllerNest.saveInRepository(listModels);
        // Creamos el fichero CSV en conjunto
        controllerNest.saveInFile();

        // Decidimos el tipo de fichero al que exportaremos
        RepositoryToWrite repositoryWithFilterStorageToExport = decisionTypeStorageToExport();
        // Controlador ÚNICAMENTE para exportar
        ControllerToWrite controllerToExport = new ControllerToWrite(repositoryWithFilterStorageToExport);
        // Guardamos en el repositorio los datos leídos anteriormente para poder leerlo y exportar a XML y JSON
        controllerToExport.saveInRepository(listModels);
        // Exportamos
        String provincia = "Madrid";
        controllerToExport.saveInFileDecisionProvincia(provincia);

        // Consultas
        ControllerQueries controllerQueries = new ControllerQueries(listModels);
        finalInformQueries(controllerQueries);
    }

    public static RepositoryToWrite decisionTypeStorageToExport() {
        while (true) {
            System.out.println("Elige el tipo de fichero al que quieres exportar los datos: ");
            System.out.println("1: En GSON_JSON");
            System.out.println("2: En XML");
            System.out.println("0: Salir");

            switch (readln()) {
                case "1" -> {
                    return new RepositoryToWrite(new StorageGson());
                }
                case "2" -> {
                    return new RepositoryToWrite(new StorageXml());
                }
                case "0" -> System.exit(0);
            }
        }
    }

    public static void finalInformQueries(ControllerQueries controllerQueries) {
        System.out.println("1.Temperatura máxima por día y lugar");
        Map<Pair<LocalDate, String>, Double> map1 = controllerQueries.temperatureMaxPerDayAndLocate();
        map1.forEach((pair, tempMax) -> {
            System.out.println("Fecha - Ubicación: " + pair.getFirst() + " - " + pair.getSecond() + " -> TempMax: " + tempMax);
        });
        System.out.println();

        System.out.println("2.Temperatura mínima por día y lugar");
        Map<Pair<LocalDate, String>, Double> map2 = controllerQueries.temperatureMinPerDayAndLocate();
        map2.forEach((pair, tempMax) -> {
            System.out.println("Fecha - Ubicación: " + pair.getFirst() + " - " + pair.getSecond() + " -> TempMax: " + tempMax);
        });
        System.out.println();

        System.out.println("3.Temperatura máxima por provincia (día, lugar, valor y momento)");
        Map<Pair<LocalDate, String>, List<String>> map3 = controllerQueries.temperatureMaxByProvincia();
        map3.forEach((pair, listInfo) -> {
            System.out.println("Provincia: " + pair.getSecond() + " -> InfoList[fecha, lugar, tempMax, hora]: " + listInfo);
        });
        System.out.println();

        System.out.println("4.Temperatura mínima por provincia (día, lugar, valor y valor)");
        Map<Pair<LocalDate, String>, List<String>> map4 = controllerQueries.temperatureMinByProvincia();
        map4.forEach((pair, listInfo) -> {
            System.out.println("Provincia: " + pair.getSecond() + " -> InfoList[fecha, lugar, tempMin, hora]: " + listInfo);
        });
        System.out.println();

        System.out.println("5.Temperatura media por provincia (día, lugar y valor)");
        Map<Pair<LocalDate, String>, List<String>> map5 = controllerQueries.temperatureAverageByProvincia();
        map5.forEach((pair, listInfo) -> {
            System.out.println("Provincia: " + pair.getSecond() + " -> InfoList[fecha, lugar, tempMedia]: " + listInfo);
        });
        System.out.println();

        System.out.println("6.Listado de precipitación media por día y provincia");
        System.out.println(controllerQueries.getListPrecipitacionAverageByDayAndProvincia().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("7.Numero de lugares en el que llovíó por día y provincia");
        System.out.println(controllerQueries.numLocatesWasRainyByDayAndProvincia().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("8.Temperatura media de la provincia de Madrid");
        System.out.println(controllerQueries.temperatureAverageFilterByOneProvincia("Madrid").stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("9.Media de temperatura máxima total");
        System.out.println(controllerQueries.temperatureMaxAverage().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("10.Media de temperatura minima total");
        System.out.println(controllerQueries.temperatureMinAverage().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("11.Lugares donde la máxima temperatura ha sido antes de las 15:00 por día");
        System.out.println(controllerQueries.locatesWhereTempMaxWasBeforeAnHourByDay().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("12.Lugares donde la mínima temperatura ha sido después de las 17:30 por día");
        System.out.println(controllerQueries.locatesWhereTempMinWasAfterAnHourByDay().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();
    }
}
