package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.Ingredient
import mu.KotlinLogging
import java.io.File
import java.util.*


class StorageBurguerTexto : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerTexto.txt"
    val file = File(localFile)

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en Texto Plano" }

        file.writeText("")
        repository.forEach {
            file.appendText("Hamburguesa" + "\n")
            file.appendText(it.getUUID().toString() + "\n")
            file.appendText(it.name + "\n")
            file.appendText(it.getPrice().toString() + "\n")

            // Ingredientes
            it.ingredients.forEach { ingredient ->
                file.appendText(ingredient.getID().toString() + "\n")
                file.appendText(ingredient.name + "\n")
                file.appendText(ingredient.price.toString() + "\n")
            }
            file.appendText("Fin_Ingredientes" + "\n")
        }
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero de Texto Plano" }

        // Filtro por si no existe el archivo
        if (!file.exists()) return emptyList()

        val listBurguer = mutableListOf<Burguer>()

        file.bufferedReader().use {
            /*
            1. Forma para limitar el while:
            while (it.readLine() != null) {...}
            (obliga a tener filtros de salto de líneas o delimitadores)

            2.Forma
            while (it.ready()) {...}
             */

            // Primer delimitador 'Hamburguesa' que no saltamos con la condición
            while (it.readLine() != null) {
                val uuid = UUID.fromString(it.readLine())
                val name = it.readLine()
                val price = it.readLine().toDouble()

                val ingredients = mutableListOf<Ingredient>()

                // Segundo delimitador 'Fin_Ingredientes'
                while (true) {
                    // Cuando lea Fin_Ingredientes dará null y saldremos de bucle
                    val idIngredient = it.readLine().toIntOrNull() ?: break
                    val nameIngredient = it.readLine()
                    val priceIngredient = it.readLine().toDouble()
                    val ingredient = Ingredient(nameIngredient, priceIngredient, idIngredient)
                    ingredients.add(ingredient)
                }

                val burguer = Burguer(name, ingredients, uuid, price)
                listBurguer.add(burguer)
            }
        }
        return listBurguer
    }
}
