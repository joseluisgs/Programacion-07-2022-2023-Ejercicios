package services.storage.hamburguesa

import config.AppConfig
import factories.IngredienteFactory
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import validator.canReed
import validator.canWrite
import java.io.BufferedInputStream
import java.io.File

private val logger = KotlinLogging.logger {}

object HamburguesaFileBinario: HamburguesaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.bin"

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileBinario ->\tsaveAll: ${elements.joinToString("\t")}" }
        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        file.outputStream().buffered().use {
            elements.forEach{ element ->
                it.write((
                        "${element.id}," +
                        "${element.nombre}," +
                        "${fromIngredientesToStringRow(element.ingredientes)},"
                        ).toByteArray(/*Charsets.UTF_8*/))
            }
        }
        return elements
    }

    private fun fromIngredientesToStringRow(ingredientes: List<Ingrediente>): String {
        return ingredientes.joinToString(separator = "|"){
            "${it.id};${it.nombre};${it.precio}"
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileBinario ->\tloadAll" }
        val file = File(localFile)
        if(!canReed(file)) return emptyList()

        val hamburguesas = mutableListOf<Hamburguesa>()
        file.inputStream().buffered().use {
            while (it.available() > 0){
                val id = readCampoBinario(it).toInt()
                val nombre = readCampoBinario(it).replace("Ã±","ñ")
                val ingredientes = fromStringRowToIngredientes(readCampoBinario(it))
                hamburguesas.add(Hamburguesa(id, nombre, ingredientes))
            }
        }

        return hamburguesas.toList()
    }

    private fun fromStringRowToIngredientes(string: String): List<Ingrediente>{
        return string.split("|")
            .map { it.split(";") }
            .map {
                Ingrediente(
                    id = it[0].toInt(),
                    nombre = it[1],
                    precio = it[2].toFloat()
                )
            }
    }

    private fun readCampoBinario(it: BufferedInputStream, separador: Char = ','): String {
        val builder = StringBuilder()
        var char = it.read().toChar()
        while (char != separador) {
            builder.append(char)
            char = it.read().toChar()
        }
        return builder.toString()
    }
}