import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.controllers.ControllerPrincipal;
import Ficheros.BurguerPig.controllers.ControllerQueries;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.repositories.IRepositoryToWriteRead;
import Ficheros.BurguerPig.repositories.RepositoryToWriteRead;
import Ficheros.BurguerPig.storages.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ConfigApp.init();

        // Decidimos el tipo de fichero con el que trabajaremos
        IRepositoryToWriteRead repositoryWithFilterStorage = decisionTypeStorage();

        // Controlador principal
        ControllerPrincipal controllerBurguer = new ControllerPrincipal(repositoryWithFilterStorage);
        // En primer lugar, cargamos todas las hamburguesas en el fichero seleccionado
        controllerBurguer.saveInFile();
        // Leemos las hamburguesas del fichero creado en el anterior paso
        List<Burguer> burguerListOfStorageReadFile = controllerBurguer.readInFile();

        // Controlador de las consultas con el tipo de storage ya decidido anteriormente
        ControllerQueries controllerQueries = new ControllerQueries(burguerListOfStorageReadFile);

        System.out.println("1. Hamburguesa más cara");
        System.out.println(controllerQueries.burguerMoreExpensive());
        System.out.println();

        System.out.println("2. Hamburguesa con más ingredientes");
        System.out.println(controllerQueries.burguerMoreIngredients());
        System.out.println();

        System.out.println("3. Número de Hamburguesas por ingrediente");
        controllerQueries.numBurguerByIngredient().forEach((ingredient, numBurguers) -> {
            System.out.println("Ingrediente: " + ingredient + " -> Número Hamburguesas: " + numBurguers);
        });
        System.out.println();

        System.out.println("4. Hamburguesas agrupadas por total de ingredientes");
        controllerQueries.burguersByIngredient().forEach((ingredient, burgers) -> {
            String burgerNames = burgers.stream().map(Burguer::getName).collect(Collectors.joining(", "));
            System.out.println("Ingrediente: " + ingredient + " -> Hamburguesas: " + burgerNames);
        });
        System.out.println();

        System.out.println("5. Precio medio de las hamburguesas");
        System.out.println(controllerQueries.averagePriceBurguers());
        System.out.println();

        System.out.println("6. Precio medio de los ingredientes");
        System.out.println(controllerQueries.averagePriceIngredients());
        System.out.println();
    }

    public static IRepositoryToWriteRead decisionTypeStorage() {
        while (true) {
            System.out.println("Elige el tipo de fichero con el que quieres realizar la gestión: ");
            System.out.println("1: En Texto Plano");
            System.out.println("2: En Binario");
            System.out.println("3: En CSV");
            System.out.println("4: En GSON_JSON");
            System.out.println("5: En XML");
            System.out.println("0: Salir");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    return new RepositoryToWriteRead(new StorageTexto());
                case "2":
                    return new RepositoryToWriteRead(new StorageBinario());
                case "3":
                    return new RepositoryToWriteRead(new StorageCSV());
                case "4":
                    return new RepositoryToWriteRead(new StorageGson());
                case "5":
                    return new RepositoryToWriteRead(new StorageXml());
                case "0":
                    System.exit(0);
            }
        }
    }
}
