package storage.accidentes

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.conjuntoDeAccidentes.ConjuntoDeAccidenteDto
import mapper.accidente.toAccidente
import mapper.accidente.toAccidentes
import mapper.accidente.toAccidentesDto
import mapper.accidente.toDto
import model.Accidente
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class AccidenteStorageServiceJSON : AccidenteStorageService{

    private val configApp = ConfigApp
    private val file = File(configApp.APP_DATA+File.separator+"accidentes.json")
    private val logger = KotlinLogging.logger {}

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter<ConjuntoDeAccidenteDto>()

    override fun saveAll(entities: List<Accidente>) {
        logger.debug { "Se guardan todos los accidentes en el fichero JSON." }
        file.writeText(jsonAdapter.indent("   ").toJson(ConjuntoDeAccidenteDto(entities.toAccidentesDto())))
    }

    override fun loadAll(): List<Accidente> {
        logger.debug { "Se cargan todos los accidentes del fichero JSON." }
        if(!file.exists()) return emptyList()
        return jsonAdapter.fromJson(file.readText())!!.accidentes.toAccidentes()
    }

}