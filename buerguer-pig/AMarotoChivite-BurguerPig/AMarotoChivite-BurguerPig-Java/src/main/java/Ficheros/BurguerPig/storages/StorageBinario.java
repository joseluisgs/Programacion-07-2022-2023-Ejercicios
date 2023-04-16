package Ficheros.BurguerPig.storages;

import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StorageBinario implements IStorageToWriteRead<Burguer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageBinario.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "burguerTexto.txt";
    private final File file = new File(localFile);

    @Override
    public void saveFile(List<Burguer> repository) {
        logger.debug("Storage: Escribiendo en Binario");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(byteArrayOutputStream);

        // Escribimos cada objeto Burguer en el DataOutputStream
        for (Burguer burguer : repository) {
            try {
                dataOutput.writeUTF(burguer.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                dataOutput.writeInt(burguer.getIngredients().size()); // Cantidad de ingredientes
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Ingredient ingredient : burguer.getIngredients()) {
                try {
                    dataOutput.writeUTF(ingredient.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dataOutput.writeDouble(ingredient.getPrice());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                dataOutput.writeLong(burguer.getUUID().getMostSignificantBits());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                dataOutput.writeLong(burguer.getUUID().getLeastSignificantBits());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                dataOutput.writeDouble(burguer.getPrice());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Escribimos los bytes en el archivo
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Cerramos los streams
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Burguer> readFile() {
        logger.debug("Storage: Leyendo desde fichero Binario");

        if (!file.exists()) {
            return Collections.emptyList();
        }

        List<Burguer> burguers = new ArrayList<>();
        FileInputStream fileInputStream = null;
        DataInputStream dataInput = null;

        try {
            fileInputStream = new FileInputStream(file);
            dataInput = new DataInputStream(fileInputStream);

            while (dataInput.available() > 0) {
                String name = dataInput.readUTF();
                int ingredientsSize = dataInput.readInt();
                List<Ingredient> ingredients = new ArrayList<>();
                for (int i = 0; i < ingredientsSize; i++) {
                    String ingredientName = dataInput.readUTF();
                    double ingredientPrice = dataInput.readDouble();
                    ingredients.add(new Ingredient(ingredientName, ingredientPrice));
                }
                UUID uuid = new UUID(dataInput.readLong(), dataInput.readLong());
                double price = dataInput.readDouble();

                Burguer burguer = new Burguer(name, ingredients, uuid, price);
                burguers.add(burguer);
            }
        } catch (IOException e) {
            logger.error("Error al leer el archivo binario de hamburguesas", e);
        } finally {
            try {
                if (dataInput != null) {
                    dataInput.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                logger.error("Error al cerrar los streams de entrada", e);
            }
        }

        return burguers;
    }
}
