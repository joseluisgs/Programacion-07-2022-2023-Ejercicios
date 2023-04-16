package Ficheros.Accidentes;

import Ficheros.Accidentes.config.ConfigApp;
import Ficheros.Accidentes.controllers.ControllerPrincipal;
import Ficheros.Accidentes.controllers.ControllerQueries;
import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.repositories.RepositoryToWriteRead;
import Ficheros.Accidentes.storages.StorageGson;
import Ficheros.Accidentes.storages.StorageXml;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kotlin.io.ConsoleKt.readln;

public class Main {
    public static void main(String[] args) {
        ConfigApp.init();

        // Decidimos el tipo de fichero al que exportaremos
        RepositoryToWriteRead repositoryWithFilterStorageToExport = decisionTypeStorageToExport();
        // Controlador general donde ÚNICAMENTE exportaremos los datos (la importación está acoplada, es decir la lectura)
        ControllerPrincipal controllerPrincipalJava = new ControllerPrincipal(repositoryWithFilterStorageToExport);
        controllerPrincipalJava.saveAllModelsInFile();
        List<AccidenteDto> listModels = controllerPrincipalJava.readAllModelsInFile();

        // Consultas
        ControllerQueries controllerQueries = new ControllerQueries(listModels);
        finalInformQueries(controllerQueries);
    }

    public static RepositoryToWriteRead decisionTypeStorageToExport() {
        while (true) {
            System.out.println("Elige el tipo de fichero al que quieres exportar los datos: ");
            System.out.println("1: En GSON_JSON");
            System.out.println("2: En XML");
            System.out.println("0: Salir");

            switch (readln()) {
                case "1" -> {
                    return new RepositoryToWriteRead(new StorageGson());
                }
                case "2" -> {
                    return new RepositoryToWriteRead(new StorageXml());
                }
                case "0" -> System.exit(0);
            }
        }
    }

    public static void finalInformQueries(ControllerQueries controllerQueries) {
        System.out.println("1. Accidentes en los que están implicados alcohol o drogas");
        // Para imprimir solo la cantidad y no ensuciar la consola
        System.out.println(controllerQueries.getAccidentesOnlyAlcoholOrDrugs().size());
        // Para imprimir todos los registros de accidentes
        // System.out.println(controllerQueries.getAccidentesOnlyAlcoholOrDrugs().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("2. Numero de accidentes que han dado positivo en alcohol y drogas.");
        System.out.println(controllerQueries.numAccidentesOnlyAlcoholAndDrugs());
        System.out.println();

        System.out.println("3. Accidentes agrupados por sexo");
        Map<String, List<AccidenteDto>> accidentesPorSexo = controllerQueries.accidentesBySexo();
        accidentesPorSexo.forEach((sexo, listaAccidentes) -> {
            // Para imprimir solo la cantidad y no ensuciar la consola
            System.out.println("Sexo: " + sexo + " -> Accidentes: " + listaAccidentes.size());
            // Para imprimir todos los registros de accidentes
            //System.out.println("Sexo: " + sexo + " -> Accidentes: " + listaAccidentes);
        });
        System.out.println();

        System.out.println("4. Accidentes agrupados por meses");
        Map<String, List<AccidenteDto>> accidentesPorMes= controllerQueries.accidentesByMonths();
        accidentesPorMes.forEach((mes, listaAccidentes) -> {
            // Para imprimir solo la cantidad y no ensuciar la consola
            System.out.println("Mes: " + mes + " -> Accidentes: " + listaAccidentes.size());
            // Para imprimir todos los registros de accidentes
            //System.out.println("Mes: " + mes + " -> Accidentes: " + listaAccidentes);
        });
        System.out.println();

        System.out.println("5. Accidentes agrupados por tipos de vehiculos");
        Map<String, List<AccidenteDto>> accidentesPorVehiculo= controllerQueries.accidentesByVehiculos();
        accidentesPorVehiculo.forEach((vehiculo, listaAccidentes) -> {
            // Para imprimir solo la cantidad y no ensuciar la consola
            System.out.println("Vehiculo: " + vehiculo + " -> Accidentes: " + listaAccidentes.size());
            // Para imprimir todos los registros de accidentes
            //System.out.println("Vehiculo: " + vehiculo + " -> Accidentes: " + listaAccidentes);
        });
        System.out.println();

        System.out.println("6. Accidentes ocurridos en la calle de leganes");
        System.out.println(controllerQueries.accidentesByLocalizacion("leganes").stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("7. Numero de accidentes por distrito");
        Map<String, Integer> numAccidentesPorDistrito = controllerQueries.numAccidentesByDistrito();
        numAccidentesPorDistrito.forEach((distrito, cantidad) -> {
            System.out.println("Distrito: " + distrito + " -> Accidentes: " + cantidad);
        });
        System.out.println();

        // TODO
        System.out.println("8. Estadisticas por distrito");
        //System.out.println(controllerQueries.estadisticasByDistrito());
        System.out.println();

        // TODO
        System.out.println("9. Accidentes por distrito ordenadas de manera descendente");
/*        Map<String, List<AccidenteDto>> accidentesPorDistrito = controllerQueries.accidentesByDistritoOrderDesc(3);
        accidentesPorDistrito.forEach((distrito, listAccidentes) -> {
            System.out.println("Distrito: " + distrito + " -> Accidentes: " + listAccidentes);
        });*/
        System.out.println();

        System.out.println("10. Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana)");
        // Para imprimir solo la cantidad y no ensuciar la consola
        System.out.println(controllerQueries.accidentesEnFinDeSemanaAndNoche().size());
        // Para imprimir todos los registros de accidentes
        // System.out.println(controllerQueries.accidentesEnFinDeSemanaAndNoche().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("11. Listado de accidentes que se produzcan en fin de semana y de noche donde se haya dado positivo en alcohol");
        System.out.println(controllerQueries.accidentesEnFinDeSemanaAndNocheAndPositiveAlcohol().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("12. Accidentes donde haya fallecidos");
        System.out.println(controllerQueries.accidentesWhereDead().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();

        System.out.println("13. Comprobar si el distrito con mas accidentes coincide con el distrito donde hay más accidentes en fin de semana");
        System.out.println("Coincidencia: "+controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().getFirst());
        System.out.println("Distrito Más Accidentes: "+controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().getSecond());
        System.out.println("Distrito Más Accidentes (fin de semana): "+controllerQueries.isDistritoMoreRiskySameAsDistritoMoreRiskyInWeekend().getThird());
        System.out.println();

        System.out.println("14. Número de accidentes donde ha habido (alcohol o drogas) y victimas mortales");
        System.out.println(controllerQueries.numAccidentesWherePositiveAlcholoOrDrugsAndDeads());
        System.out.println();

        System.out.println("15. Numero de accidentes donde ha habido atropellos a personas");
        // Para imprimir solo la cantidad y no ensuciar la consola
        System.out.println(controllerQueries.numAccidentesWhereHitPeople().size());
        System.out.println();

        System.out.println("16. Accidentes agrupados por estado metereológio");
        Map<String, List<AccidenteDto>> accidentesPorMeteorologia = controllerQueries.accidentesByMeteorologia();
        accidentesPorMeteorologia.forEach((meteo, listAccidentes) -> {
            // Para imprimir solo la cantidad y no ensuciar la consola
            System.out.println("Meteorología: " + meteo + " -> Accidentes: " + listAccidentes.size());
            // Para imprimir todos los registros de accidentes
            // System.out.println("Meteorología: " + meteo + " -> Accidentes: " + listAccidentes);
        });
        System.out.println();

        System.out.println("17. Lista de accidentes donde haya habido un atropello animal");
        System.out.println(controllerQueries.accidentesWhereHitAnimal().stream().map(Object::toString).collect(Collectors.joining("\n")));
        System.out.println();
    }

}
