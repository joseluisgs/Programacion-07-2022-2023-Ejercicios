package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.Ingredient
import mu.KotlinLogging
import java.io.File
import java.util.*


class StorageBurguerCSV : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerCSV.csv"

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en CSV" }

        val file = File(localFile)
        file.writeText("id_burguer,nombre_burguer,precio_burguer,ingrediente_id;ingrediente_nombre;ingrediente_precio" + "\n")
        repository.forEach {
            file.appendText("${it.getUUID()},${it.name},${it.getPrice()},${printIngredients(it.ingredients)}" + "\n")
        }
    }

    /**
     * Para disponer de cadenas en el elemento ingredientes, ya que es una lista siendo un atributo de hamburguesa
     * y poder escribirlo en el fichero con distinción de atributos
     * @param ingredients la lista de ingredientes ed la hamburguesa
     * @return la lista de ingredientes en formato cadena y con delimitadores identificativos
     */
    private fun printIngredients(ingredients: List<Ingredient?>): String {
        return ingredients.joinToString("|") {
            "${it!!.getID()};${it.name};${it.price}"
        }
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero CSV" }
        val file = File(localFile)

        // Filtro por si no existe el archivo
        if (!file.exists()) return emptyList()

        // Leer el fichero completo y eliminamos la primera fila
        val lines = file.readLines().drop(1)

        return lines.map { line ->
            val fields = line.split(',')
            val id = UUID.fromString(fields[0])
            val nombre = fields[1]
            val precio = fields[2].toDouble()
            val ingredientesString = fields[3].split('|')
            val ingredientes = ingredientesString.map { ingredienteString ->
                val ingredienteFields = ingredienteString.split(';')
                val idIngrediente = ingredienteFields[0].toInt()
                val nombreIngrediente = ingredienteFields[1]
                val precioIngrediente = ingredienteFields[2].toDouble()
                Ingredient(nombreIngrediente, precioIngrediente, idIngrediente)
            }
            Burguer(nombre, ingredientes, id, precio)
        }
    }
}