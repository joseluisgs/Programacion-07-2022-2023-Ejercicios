package storageService.Ingrediente

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import dto.ingrediente.IngredienteDto
import mapper.toDto
import mapper.toIngrediente
import model.Ingrediente
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class IngredienteStorageServiceJSON: IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"ingredientes.json")
    private val logger = KotlinLogging.logger {}

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter<List<IngredienteDto>>()

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el fichero JSON." }
        var json = jsonAdapter.indent("   ").toJson(entities.toDto().ingredientesDto)
        file.writeText(json)
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargán todos los ingredientes del fichero JSON." }
        if(!file.exists()){
            return emptyList()
        }
        val json = jsonAdapter.fromJson(file.readText())!!.map { it.toIngrediente() }
        return json
    }
}