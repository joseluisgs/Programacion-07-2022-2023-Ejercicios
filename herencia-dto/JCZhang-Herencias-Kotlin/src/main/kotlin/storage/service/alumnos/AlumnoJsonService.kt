package storage.service.alumnos

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dto.AlumnoListDto
import mappers.alumnoListToDto
import mappers.profesorListToDto
import mappers.toAlumnoList
import mappers.toProfesorList
import models.Alumno
import mu.KotlinLogging
import storage.service.AlumnoStorageService
import storage.service.profesores.ProfesorJsonService
import java.io.File
import java.nio.file.Files

@OptIn(ExperimentalStdlibApi::class)
object AlumnoJsonService: AlumnoStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}alumno.json"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter<AlumnoListDto>()


    override fun saveAll(items: List<Alumno>) {
        logger.debug { "AlumnoJson: Guardando profesores en fichero json" }
        val file = File(filePath)
        if (!file.exists()){
            Files.createFile(file.toPath())
        }

        file.writeText(jsonAdapter.indent("  ").toJson(items.alumnoListToDto()))
    }

    override fun loadAll(): List<Alumno> {
        logger.debug { "AlumnoJson: Cargando profesores de fichero json" }
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }
        return jsonAdapter.fromJson(file.readText())!!.toAlumnoList()
    }
}