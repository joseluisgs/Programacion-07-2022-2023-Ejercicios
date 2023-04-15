package storage.service.profesores


import models.Profesor
import mu.KotlinLogging
import storage.service.ProfesorStorageService
import java.io.File
import java.nio.file.Files

object ProfesorCsvService: ProfesorStorageService {


    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}alumno.csv"

    override fun saveAll(items: List<Profesor>) {
        logger.debug { "ProfesorCSV: Guardando profesores en fichero CSV" }
        val file = File(filePath)
        if (!file.exists()){
            Files.createFile(file.toPath())
        }
        //Escribo el prólogo
        file.writeText("nombre, edad" + "\n")
        items.forEach { file.appendText("${it.nombre},${it.modulo}" +"\n") }
    }

    override fun loadAll(): List<Profesor> {
        logger.debug { "ProfesorCSV: Cargando profesores en fichero CSV" }
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }

        val readFile = file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { campo-> Profesor(
                nombre = campo[0],
                modulo = enumValueOf(campo[1]))
            }
        readFile.forEach { println(it) }
        return readFile
    }
}