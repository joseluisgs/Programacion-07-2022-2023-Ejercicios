package services.storage.ingredientes


import models.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile

object IngredientesCSVService : IngredientesStorageService {

    private val logger = KotlinLogging.logger {}
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}Ingredientes.csv"

    override fun saveAll(items: List<Ingrediente>) {
        logger.debug("IngredientesCSV Service: saveAll()")
        val file = File(filePath)
        if (!file.exists()) {
            createFile(file.toPath())
        }
        //Encabezado de los ficheros CSV. Escribir cada campo de la clase Ingrediente
        file.writeText("id, nombre, precio" + "\n")
        items.forEach {
            file.appendText("${it.ID},${it.nombre},${it.precio}" + "\n")
        }

    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug("IngredientesCSV Service: loadAll()")
        val file = File(filePath)
        if (!file.exists()) {
            return emptyList()
        }
        val fileRead = file.readLines()
            //descartamos la primera línea, ya que es el prólogo
            .drop(1)
            .map {campo -> campo.split(",")}
            .map { campo -> Ingrediente(
                ID = campo[0].toInt(),
                nombre = campo[1].trim(),
                precio = campo[2].trim().toDouble()
            ) }
        fileRead.forEach { println(it) }
        return fileRead
    }
}