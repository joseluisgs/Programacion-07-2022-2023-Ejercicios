package service.storage.hamburguesas.binario

import config.ConfigApp
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import service.storage.hamburguesas.HamburguesasStorageService
import java.io.File
import java.util.*

private val logger = KotlinLogging.logger {}

object hamburguesasFicheroBinarioService : HamburguesasStorageService {

    private val localFile =
        "${ConfigApp.APP_DATA}${File.separator}" + "hamburgesa" + File.separator + "binario" + File.separator + "hamburgesa.bin"

    override fun saveAll(items: List<Hamburguesa>) {
        logger.info { "Guardando hamburguesas en un fichero de binario" }
        val file = File(localFile)

        file.outputStream().buffered().use {
            items.forEach { item ->
                it.write(item.id.toString().toByteArray())
                it.write("\n".toByteArray())
                it.write(item.name.toByteArray())
                it.write("\n".toByteArray())
                it.write(convertRecetaToWrite(item.receta).toByteArray())
                it.write("\n".toByteArray())
                it.write(item.precio.toString().toByteArray())
                it.write("\n".toByteArray())
            }
        }
    }

    private fun convertRecetaToWrite(receta: List<Ingrediente>): String {
        return receta.joinToString(separator = "|", prefix = "[", postfix = "]") {
            "${it.id};${it.name};${it.price}" // Importante que la separación entre atibutos no sea el mismo que para los atributos de la hamburgesa
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.info { "Cargando hamburguesas de un fichero binario" }
        val file = File(localFile)
        val hamburguesas = mutableListOf<Hamburguesa>()
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
                val id = UUID.fromString(idString.toString())

                val nombreString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    nombreString.append(char)
                    char = it.read().toChar()
                }
                val nombre = nombreString.toString()

                val recetaString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    recetaString.append(char)
                    char = it.read().toChar()
                }
                val receta = convertRecetaToRead(recetaString.toString())

                val precioString = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    precioString.append(char)
                    char = it.read().toChar()
                }
                val precio = precioString.toString().toDouble()

                hamburguesas.add(Hamburguesa(id, nombre, receta))
            }
        }

        return hamburguesas.toList()
    }

    private fun convertRecetaToRead(columna: String): List<Ingrediente> {
        return columna
            .replace("[", "")
            .replace("]", "")
            .split("|") // Separación ente ingredientes
            .map { it.split(";") } // Separa los atributos de cada ingrediente
            .map {
                Ingrediente(
                    id = it[0].toInt(),
                    name = it[1],
                    price = it[2].toDouble()
                )
            }
    }
}


