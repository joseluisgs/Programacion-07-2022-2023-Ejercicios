package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.dto.BurguerListDto
import mappers.BurguerListMapper
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import java.io.File


class StorageBurguerXML : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerXML.xml"
    val file = File(localFile)

    private val serializer = Persister()

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en XML" }

        serializer.write(BurguerListMapper().toDtoList(repository), file)
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero de XML" }

        return BurguerListMapper().toModelList(serializer.read(BurguerListDto::class.java, file))
    }
}