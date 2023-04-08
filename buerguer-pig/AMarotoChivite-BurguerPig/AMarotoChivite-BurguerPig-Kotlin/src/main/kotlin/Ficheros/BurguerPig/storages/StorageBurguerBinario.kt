package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.config.ConfigApp
import Ficheros.BurguerPig.models.Burguer
import mu.KotlinLogging
import java.io.*


class StorageBurguerBinario : IStorageGeneral<Burguer> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}burguerBinario.bin"

    /**
     * Guardamos los objetos del repositorio en el fichero
     * @param repository lista de hamburguesas del repository
     */
    override fun saveInFile(repository: List<Burguer>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en Binario" }
        val file = File(localFile)

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
        logger.debug { "Storage: Leyendo desde fichero Binario" }
        val file = File(localFile)
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

