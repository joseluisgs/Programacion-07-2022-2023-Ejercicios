package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import mu.KotlinLogging
import java.io.*


class StorageBurguerSerializado : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerSerializado.ser"

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en Serializado" }
        val file = File(localFile)

        ObjectOutputStream(FileOutputStream(file)).use {
            // Podemos escribir cualquier objeto serializable, ya sea un objeto o una colección
            it.writeObject(repository)
        }
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero Serializado" }
        val file = File(localFile)
        if (!file.exists()) return emptyList()

        ObjectInputStream(FileInputStream(file)).use {
            // Podemos leer cualquier objeto serializable, ya sea un objeto o una colección!!!
            return it.readObject() as MutableList<Burguer>
        }

    }
}