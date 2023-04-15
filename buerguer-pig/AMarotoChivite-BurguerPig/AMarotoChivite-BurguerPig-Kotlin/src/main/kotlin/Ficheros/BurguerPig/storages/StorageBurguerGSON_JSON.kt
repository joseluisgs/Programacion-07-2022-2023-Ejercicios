package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.dto.BurguerDTO
import Ficheros.BurguerPig.models.dto.BurguerListDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mappers.BurguerListMapper
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi // Para usar el método adapter con enlazado de Kotlin
class StorageBurguerGSON_JSON : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerGSON.json"
    val file = File(localFile)

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en JSON con GSON" }

        // Utilizando GSON (en clases no abstractas y embebidas)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(BurguerListMapper().toDto(repository).list)
        file.writeText(jsonString)
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero JSON" }

        // Utilizando GSON (en clases no abstractas y embebidas)
        val gson = Gson()
        val jsonString = file.readText()
        val type = object : TypeToken<List<BurguerDTO>>() {}.type
        val dtoList = gson.fromJson<List<BurguerDTO>>(jsonString, type)
        return BurguerListMapper().toModelList(BurguerListDto(dtoList))
    }
}

