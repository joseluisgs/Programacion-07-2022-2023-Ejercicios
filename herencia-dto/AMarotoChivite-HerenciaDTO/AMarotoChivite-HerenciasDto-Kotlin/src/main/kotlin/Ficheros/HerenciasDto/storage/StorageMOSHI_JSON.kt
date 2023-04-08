package Ficheros.HerenciasDto.storage

import Ficheros.HerenciasDto.config.ConfigApp
import Ficheros.HerenciasDto.models.dto.PersonDTO
import Ficheros.HerenciasDto.utils.PersonAdapterFinal
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageMOSHI_JSON : IStorageGeneral<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}personMOSHI.json"
    val file = File(localFile)

    private val moshi = Moshi
        .Builder()
        .add(PersonAdapterFinal())
        .add(KotlinJsonAdapterFactory())
        .build()
    private val jsonAdapter =
        moshi.adapter<List<PersonDTO>>(Types.newParameterizedType(List::class.java, PersonDTO::class.java))

    override fun saveInFile(repository: List<PersonDTO>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en JSON con MOSHI" }

        file.writeText(jsonAdapter.indent("  ").toJson(repository))
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Storage: Leyendo desde fichero JSON con MOSHI" }

        return jsonAdapter.fromJson(file.readText()) ?: emptyList()
    }
}