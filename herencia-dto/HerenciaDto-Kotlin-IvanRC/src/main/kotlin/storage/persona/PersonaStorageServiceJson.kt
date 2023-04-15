package storage.persona

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.ListaPersonasDto
import mapper.toPersonas
import mapper.toPersonasDto
import models.Persona
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class PersonaStorageServiceJson: PersonaStorageService {

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+File.separator+"personas.json")
    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter<ListaPersonasDto>()

    private val logger = KotlinLogging.logger {  }

    override fun saveAll(entities: List<Persona>) {
        logger.debug { "Se guardan todas las personas en el fichero JSON" }
        file.writeText(adapter.indent("   ").toJson(entities.toPersonasDto()))
    }

    override fun loadAll(): List<Persona> {
        logger.debug { "Se cargan todas las personas del fichero JSON" }
        if(!file.exists()) return emptyList()
        return adapter.fromJson(file.readText())!!.toPersonas()
    }
}