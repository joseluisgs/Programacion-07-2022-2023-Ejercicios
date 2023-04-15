package service.storage.ingredientes.binario

import config.ConfigApp
import models.Ingrediente
import mu.KotlinLogging
import service.storage.ingredientes.IngredientesStorageService
import java.io.File

private val logger = KotlinLogging.logger {}


object IngredientesFicheroBinarioService : IngredientesStorageService {

    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "ingredientes" + File.separator + "binario" + File.separator + "ingredientes.bin"

    /*
     * Escribimos cada atributo del objeto como string y luego lo pasamos a ByteArray seguido de un salto de línea \n para
     * luego en la lectura usarlo como delimitador y saber donde parar de leer.
     */
    override fun saveAll(items: List<Ingrediente>) {
        logger.info { "Guardando ingredientes en un fichero binario" }
        val file = File(localFile)

        file.outputStream().buffered().use {
            items.forEach { item ->
                it.write(item.id.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.name.toByteArray())
                it.write("\n".toByteArray())
                it.write(item.price.toString().toByteArray())
                it.write("\n".toByteArray())
            }
        }
    }


    override fun loadAll(): List<Ingrediente> {
        logger.info { "Cargando los ingredientes de un fichero binario" }
        val file = File(localFile)
        val ingredientes = mutableListOf<Ingrediente>()
        // Early return
        if (!file.exists()) return emptyList()

        file.inputStream().buffered().use {
            while (it.available() > 0) {
                // Leemos caracter a caracter y lo guardamos en un string builder y lo casteamos a su tipo
                val idString = StringBuilder()
                var char = it.read().toChar()
                while (char != '\n') {
                    idString.append(char)
                    char = it.read().toChar()
                }
                val id = idString.toString().toInt()

                val nombreString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    nombreString.append(char)
                    char = it.read().toChar()
                }
                val nombre = nombreString.toString()

                // Lo leemos para que no se solapen los datos ya el precio es calculado y no va en el constructor
                val precioString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    precioString.append(char)
                    char = it.read().toChar()
                }
                val precio = precioString.toString().toDouble()

                ingredientes.add(Ingrediente(id, nombre, precio))
            }
        }

        return ingredientes.toList()
    }
}