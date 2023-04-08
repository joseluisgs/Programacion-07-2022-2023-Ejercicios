package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.utils.UuidAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi // Para usar el método adapter con enlazado de Kotlin
class StorageBurguerMOSHI_JSON : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerMOSHI.json"
    val file = File(localFile)

    // Utilizando MOSHI con adapter personalizado (en clases no abstractas y embebidas)
    private val moshi = Moshi.Builder()
        .add(UuidAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<List<Burguer>>()

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en JSON" }

        // Utilizando MOSHI con adapter personalizado (en clases no abstractas y embebidas)
        file.writeText(jsonAdapter.indent("  ").toJson(repository))
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero JSON" }

        // Utilizando MOSHI con adapter personalizado (en clases no abstractas y embebidas)
        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}

