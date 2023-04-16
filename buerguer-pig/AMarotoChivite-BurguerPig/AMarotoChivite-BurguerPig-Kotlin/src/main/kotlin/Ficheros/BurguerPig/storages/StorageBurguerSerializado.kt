package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import mu.KotlinLogging
import java.io.*


class StorageBurguerSerializado : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerSerializado.ser"
    val file = File(localFile)

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en Serializado" }

        val fileOutPut: FileOutputStream = FileOutputStream(file)
        val escritura: ObjectOutputStream = ObjectOutputStream(fileOutPut)

        repository.forEach {
            escritura.writeObject(it)
        }
        escritura.close()
    }

    /**
     * Leemos de los ficheros de la carpeta data
     * @return la lista de hamburguesas ya leídas de nuestro/s ficheros
     */
    override fun readAllModelsInFile(): List<Burguer> {
        logger.debug { "Storage: Leyendo desde fichero Serializado" }

        if (!file.exists()) return emptyList()

        val fileInPut: FileInputStream = FileInputStream(file)
        val lectura: ObjectInputStream = ObjectInputStream(fileInPut)

        val burguers = mutableListOf<Burguer>()

        while (true) {
            try {
                val burguer = lectura.readObject() as Burguer
                burguers.add(burguer)
            } catch (e: EOFException) {
                break
            }
        }
        lectura.close()
        return burguers

    }
}