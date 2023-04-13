package services.storage.ingredientes

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Ingrediente
import mu.KotlinLogging
import utils.toPrettyJson
import java.io.File
import java.nio.file.Files


@ExperimentalStdlibApi
object IngredienteJsonService: IngredientesStorageService {

    private val logger = KotlinLogging.logger {  }
    private val programPath = System.getProperty("user.dir")
    private val filePath =("$programPath${File.separator}data${File.separator}ingredientes.json")

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val jsonAdapter = moshi.adapter<List<Ingrediente>>()





    override fun saveAll(items: List<Ingrediente>) {
        logger.debug { "IngredientesJSON: Guardando ingredientes en fichero JSON" }
        val file = File(filePath)

        if (!file.exists()){
            Files.createFile(file.toPath())
        }

        file.writeText(jsonAdapter.toPrettyJson(items))


    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug{"IngredientesJson: Cargando ingredientes de fichero Json"}
        val file = File(filePath)

        return jsonAdapter.fromJson(file.readText())!!
    }

}
