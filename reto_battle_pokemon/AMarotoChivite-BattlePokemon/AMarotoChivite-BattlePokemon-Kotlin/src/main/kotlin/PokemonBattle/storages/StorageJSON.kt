package PokemonBattle.storages

import PokemonBattle.config.ConfigApp
import PokemonBattle.models.Team
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageJSON : IStorageWritterReader<Team> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}teams.json"
    val file = File(localFile)

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter = moshi.adapter<List<Team>>()

    override fun saveFile(repository: List<Team>) {
        logger.debug { "Storage: Escribiendo en fichero JSON" }
        file.writeText(jsonAdapter.indent("  ").toJson(repository))
    }

    override fun readFile(): List<Team> {
        logger.debug { "Storage: Leyendo desde fichero JSON" }
        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}

