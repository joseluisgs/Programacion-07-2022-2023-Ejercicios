package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.Ingredient
import mu.KotlinLogging
import java.io.*
import java.util.*


class StorageBurguerBinario : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerBinario.bin"
    val file = File(localFile)

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en Binario" }

        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutput = DataOutputStream(byteArrayOutputStream)

        // Escribimos cada objeto Burguer en el DataOutputStream
        for (burguer in repository) {
            dataOutput.writeUTF(burguer.name)
            dataOutput.writeInt(burguer.ingredients.size) // Cantidad de ingredientes
            for (ingredient in burguer.ingredients) {
                dataOutput.writeUTF(ingredient.name)
                dataOutput.writeDouble(ingredient.price)
            }
            dataOutput.writeLong(burguer.getUUID().mostSignificantBits)
            dataOutput.writeLong(burguer.getUUID().leastSignificantBits)
            dataOutput.writeDouble(burguer.getPrice())
        }

        // Escribimos los bytes en el archivo
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())

        // Cerramos los streams
        fileOutputStream.close()
        dataOutput.close()
        byteArrayOutputStream.close()
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero Binario" }
        if (!file.exists()) return emptyList()

        val burguers = mutableListOf<Burguer>()
        val fileInputStream = FileInputStream(file)
        val dataInput = DataInputStream(fileInputStream)

        try {
            while (dataInput.available() > 0) {
                val name = dataInput.readUTF()
                val ingredientsSize = dataInput.readInt()
                val ingredients = mutableListOf<Ingredient>()
                for (i in 0 until ingredientsSize) {
                    val ingredientName = dataInput.readUTF()
                    val ingredientPrice = dataInput.readDouble()
                    ingredients.add(Ingredient(ingredientName, ingredientPrice))
                }
                val uuid = UUID(dataInput.readLong(), dataInput.readLong())
                val price = dataInput.readDouble()

                val burguer = Burguer(name, ingredients, uuid, price)
                burguers.add(burguer)
            }
        } catch (e: IOException) {
            logger.error(e) { "Error al leer el archivo binario de hamburguesas" }
        } finally {
            fileInputStream.close()
            dataInput.close()
        }

        return burguers
    }
}


