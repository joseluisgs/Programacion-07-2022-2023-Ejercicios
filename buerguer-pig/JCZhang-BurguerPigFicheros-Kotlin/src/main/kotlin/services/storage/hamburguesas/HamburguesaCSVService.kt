package services.storage.hamburguesas

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile
import java.util.*

object HamburguesaCSVService : HamburguesaStorageService {

    private val logger = KotlinLogging.logger { }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}Hamburguesas.csv"


    override fun saveAll(items: List<Hamburguesa>) {
        logger.debug("HamburguesasCSV: Guardando ingredientes en fichero")
        val file = File(filePath)

        if (!file.exists()) {
            createFile(file.toPath())
        }
        //escribo el encabezado
        file.writeText("id, nombre, ingrediente, precio" + "\n")
        items.forEach {
            file.appendText("${it.id},${it.nombre},${it.ingredientes.joinToString("|")},${it.precio} "+ "\n")
        }
    }


    override fun loadAll(): List<Hamburguesa> {
        logger.debug("HamburguesaCSV: Cargando ingredientes desde fichero")
        val file = File(filePath)

        if (!file.exists()) {
            return emptyList()
        }

        val listBurguers = mutableListOf<Hamburguesa>()
        val fileRead = file.readLines()
            //salto la primera línea porque es el prólogo
            .drop(1)
            .map { campo -> campo.split(",") }
            .map { campo ->
                Hamburguesa(
                    id = UUID.fromString(campo[0]),
                    nombre = campo[1].trim(),
                    ingredientes = (fromCsvToIngredientsList(campo[2])),
                    precio = campo[3].toDouble()
                )
            }
        fileRead.forEach { println(it) }
        return fileRead

    }

    private fun fromCsvToIngredientsList(campo: String): List<Ingrediente> {
        val campo1 = campo.replace("[", "").replace("]", "").split("|")
        val ingredientes = mutableListOf<Ingrediente>()
        campo1.forEach {
            ingredientes.add(Ingrediente.parseFromString(it))
        }
        return ingredientes
    }
}