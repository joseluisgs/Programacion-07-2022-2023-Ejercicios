package storage.service.alumnos

import models.Alumno
import mu.KotlinLogging
import storage.service.AlumnoStorageService
import storage.service.profesores.ProfesorJsonService
import java.io.File
import java.nio.file.Files

object AlumnoCsvService: AlumnoStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}alumno.csv"

    override fun saveAll(items: List<Alumno>) {
        logger.debug { "AlumnoCSV: Guardando alumnos en fichero CSV" }
        val file = File(filePath)
        if (!file.exists()){
            Files.createFile(file.toPath())
        }
        //Escribo el prólogo
        file.writeText("nombre, edad" + "\n")
        items.forEach { file.appendText("${it.nombre},${it.edad}" +"\n") }
    }

    override fun loadAll(): List<Alumno> {
        logger.debug { "AlumnoCSV: Cargando alumnos en fichero CSV" }
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }

        val readFile = file.readLines()
            .drop(1)
            .map { it.split(",") }
            .map { campo-> Alumno(
                nombre = campo[0],
                edad = campo[1].toInt())
            }
        readFile.forEach { println(it) }
        return readFile
    }
}