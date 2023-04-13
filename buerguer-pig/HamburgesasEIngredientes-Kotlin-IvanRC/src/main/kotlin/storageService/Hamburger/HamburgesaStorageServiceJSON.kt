package storageService.Hamburger

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.hamburgesa.HamburgesaDto
import mapper.toDto
import mapper.toHamburgesa
import model.Hamburgesa
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class HamburgesaStorageServiceJSON: HamburgesaStorageService{

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesa.json")
    private val logger = KotlinLogging.logger {}

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter<List<HamburgesaDto>>()

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todas las hamburgesas en el fichero JSON." }
        var json = jsonAdapter.indent("   ").toJson(entities.toDto().hamburgesasDto)
        file.writeText(json)
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargán todas las hamburgesas del fichero JSON." }
        if(!file.exists()){
            return emptyList()
        }
        var json = jsonAdapter.fromJson(file.readText())!!.map { it.toHamburgesa() }
        return json
    }
}