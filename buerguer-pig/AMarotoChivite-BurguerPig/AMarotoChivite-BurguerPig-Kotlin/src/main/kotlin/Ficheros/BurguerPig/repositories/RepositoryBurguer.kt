package Ficheros.BurguerPig.repositories

import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.models.factories.BurguerFactory
import Ficheros.BurguerPig.storages.IStorageGeneral
import mu.KotlinLogging


class RepositoryBurguer(private val storageBurguer: IStorageGeneral<Burguer>) {

    private val logger = KotlinLogging.logger {}

    private val repository = BurguerFactory.create()

    /**
     * Actualizamos el fichero dirigiéndonos al storage
     */
    fun saveAllBurguersInFileOverride() {
        logger.debug { "Repository: Escribiendo hamburguesas (sobreescribiendo)" }
        storageBurguer.saveInFile(repository.toList())
    }

    /**
     * Leemos de nuestros ficheros creados en data, que nos redirigirá al storage
     * @return lista de hamburguesas ya leídas de nuestros ficheros desde el storage
     */
    fun readAllBurguersInFile(): List<Burguer> {
        logger.debug { "Repository: Leyendo hamburguesas" }
        return storageBurguer.readAllModelsInFile()
    }
}