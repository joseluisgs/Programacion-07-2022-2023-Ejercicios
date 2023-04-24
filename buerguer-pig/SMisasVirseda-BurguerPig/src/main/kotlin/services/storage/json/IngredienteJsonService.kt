package services.storage.json

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import config.AppConfig
import dto.IngredientesDto
import dto.toDto
import dto.toIngredientes
import exceptions.JsonException
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

@OptIn(ExperimentalStdlibApi::class)
object IngredienteJsonService : IngredienteStorageService {
    private val path = AppConfig.jsonPath + File.separator + "Ingredientes.json"
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter<IngredientesDto>()

    override fun cargar(): List<Ingrediente> {
        return adapter.fromJson(File(path).readText())?.toIngredientes() ?: throw JsonException("Error al leer JSON")
    }

    override fun guardar(items: List<Ingrediente>) {
        File(path).writeText(adapter.indent("\t").toJson(items.toDto()))
    }
}
