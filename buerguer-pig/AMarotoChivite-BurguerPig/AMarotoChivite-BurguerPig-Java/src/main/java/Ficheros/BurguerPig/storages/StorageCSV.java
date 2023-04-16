package Ficheros.BurguerPig.storages;

import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.mappers.BurguerListMapper;
import Ficheros.BurguerPig.mappers.BurguerMapper;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.models.dto.BurguerListDto;
import ch.qos.logback.classic.Logger;
import org.simpleframework.xml.core.Persister;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StorageCSV implements IStorageToWriteRead<Burguer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageCSV.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "burguerCSV.csv";
    private final File file = new File(localFile);

    @Override
    public void saveFile(List<Burguer> repository) {
        logger.debug("Storage: Escribiendo (sobreescribiendo) en CSV");

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("id_burguer,nombre_burguer,precio_burguer,ingrediente_id;ingrediente_nombre;ingrediente_precio");
            repository.forEach(burguer -> writer.println(
                    burguer.getUUID() + "," +
                            burguer.getName() + "," +
                            burguer.getPrice() + "," +
                            printIngredients(burguer.getIngredients())
            ));
        } catch (IOException e) {
            logger.error("Error al escribir en el archivo CSV: ", e);
        }
    }

    private String printIngredients(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getID() + ";" + ingredient.getName() + ";" + ingredient.getPrice())
                .collect(Collectors.joining("|"));
    }

    @Override
    public List<Burguer> readFile() {
        logger.debug("Storage: Leyendo desde fichero CSV");

        // Filtro por si no existe el archivo
        if (!file.exists()) return Collections.emptyList();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            logger.error("Error al leer desde CSV", e);
        }

        // Eliminamos primera fila
        if (lines != null) {
            lines = lines.subList(1, lines.size());
        }

        List<Burguer> burgers = new ArrayList<>();
        for (String line : lines) {

            String[] fields = line.split(",");
            UUID id = UUID.fromString(fields[0]);
            String nombre = fields[1];
            double precio = Double.parseDouble(fields[2]);
            String[] ingredientesString = fields[3].split("\\|");
            List<Ingredient> ingredientes = new ArrayList<>();

            for (String ingredienteString : ingredientesString) {
                String[] ingredienteFields = ingredienteString.split(";");
                int idIngrediente = Integer.parseInt(ingredienteFields[0]);
                String nombreIngrediente = ingredienteFields[1];
                double precioIngrediente = Double.parseDouble(ingredienteFields[2]);
                Ingredient ingrediente = new Ingredient(nombreIngrediente, precioIngrediente, idIngrediente);
                ingredientes.add(ingrediente);
            }
            Burguer burguer = new Burguer(nombre, ingredientes, id, precio);
            burgers.add(burguer);
        }
        return burgers;
    }
}
