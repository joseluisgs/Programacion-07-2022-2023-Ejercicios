package storage.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dto.AccidenteListDto
import mappers.accidenteListToListOfDto
import mappers.toAccidenteList
import models.Accidente
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files.createFile

@ExperimentalStdlibApi
object AccidenteJsonService: AccidentesService {
    private val logger = KotlinLogging.logger {  }
    val programPath = System.getProperty("user.dir")
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter<AccidenteListDto>()

    override fun loadAll(): List<Accidente> {
        logger.debug { "AccidenteJson: Cargando Accidentes de fichero json" }

        val fileJson = File("${programPath}${File.separator}data${File.separator}accidentes.json")
        if(!fileJson.exists()){
            return emptyList()
        }
        return adapter.fromJson(fileJson.readText())!!.toAccidenteList()

    }

    override fun saveAll(items: List<Accidente>) {
        logger.debug { "AccidenteJson: Guardando Accidentes en fichero json" }
        val fileJson = File("${programPath}${File.separator}data${File.separator}accidentes.json")
        if (!fileJson.exists()){
            createFile(fileJson.toPath())
        }
        fileJson.writeText(adapter.indent("  ").toJson(items.accidenteListToListOfDto()))
    }
}