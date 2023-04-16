package Ficheros.BurguerPig.storages;

import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class StorageTexto implements IStorageToWriteRead<Burguer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageTexto.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "burguerTexto.txt";
    private final File file = new File(localFile);

    @Override
    public void saveFile(List<Burguer> repository) {
        logger.debug("Storage: Escribiendo en Texto");

        try (PrintWriter writer = new PrintWriter(file)) {
            repository.forEach(burguer -> writer.println(
                    "Hamburguesa"+ "\n"+
                    burguer.getUUID() + "\n" +
                    burguer.getName() + "\n" +
                    burguer.getPrice() + "\n"+
                    printIngredients(burguer.getIngredients())+
                    "Fin Ingredientes"
            ));
        } catch (IOException e) {
            logger.error("Error al escribir en el archivo: ", e);
        }
    }

    private String printIngredients(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getID() + "\n" + ingredient.getName() + "\n" + ingredient.getPrice()+"\n")
                .collect(Collectors.joining());
    }

    @Override
    public List<Burguer> readFile() {
        logger.debug("Storage: Leyendo de Texto");

        List<Burguer> repository = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine(); // Saltar la línea "Hamburguesa"
                UUID uuid = UUID.fromString(scanner.nextLine().trim());
                String name = scanner.nextLine().trim();
                double price = Double.parseDouble(scanner.nextLine().trim());

                List<Ingredient> ingredients = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String ingredientId = scanner.nextLine().trim();
                    if (ingredientId.equals("Fin Ingredientes")) { // Limitador termina los ingredientes
                        break;
                    }
                    String ingredientName = scanner.nextLine().trim();
                    double ingredientPrice = Double.parseDouble(scanner.nextLine().trim());
                    ingredients.add(new Ingredient(ingredientName,ingredientPrice, Integer.parseInt(ingredientId)));
                }

                repository.add(new Burguer(name,ingredients,uuid,price));
            }
        } catch (FileNotFoundException e) {
            logger.error("El archivo no existe: ", e);
        }

        return repository;
    }
}
