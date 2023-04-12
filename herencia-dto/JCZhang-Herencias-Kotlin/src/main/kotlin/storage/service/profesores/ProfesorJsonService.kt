package storage.service.profesores

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dto.ProfesorListDto
import mappers.profesorListToDto
import mappers.toProfesorList
import models.Profesor
import mu.KotlinLogging
import storage.service.ProfesorStorageService
import java.io.File
import java.nio.file.Files.createFile


@OptIn(ExperimentalStdlibApi::class)
object ProfesorJsonService: ProfesorStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}profesor.json"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter<ProfesorListDto>()


    override fun saveAll(items: List<Profesor>) {
        logger.debug { "ProfesorJson: Guardando profesores en fichero json" }
        val file = File(filePath)
        if (!file.exists()){
            createFile(file.toPath())
        }

        file.writeText(jsonAdapter.indent("  ").toJson(items.profesorListToDto()))
    }

    override fun loadAll(): List<Profesor> {
        logger.debug { "ProfesorJson: Cargando profesores de fichero json" }
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }
        return jsonAdapter.fromJson(file.readText())!!.toProfesorList()
    }
}

