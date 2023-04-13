package services.storage.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import exceptions.JsonException
import models.Ingrediente
import models.dto.IngredientesDto
import models.dto.toDto
import models.dto.toIngredientes
import services.storage.IngredienteStorageService
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
object IngredienteJsonService : IngredienteStorageService {
    private val localFile = "${AppConfig.JSON_PATH}${File.separator}ingredientes.json"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter<IngredientesDto>()

    override fun saveAll(items: List<Ingrediente>) {
        File(localFile).writeText(adapter.indent("\t").toJson(items.toDto()))
    }

    override fun loadAll(): List<Ingrediente> {
        return adapter.fromJson(File(localFile).readText())?.toIngredientes()
            ?: throw JsonException("Error al leer el fichero JSON")
    }
}