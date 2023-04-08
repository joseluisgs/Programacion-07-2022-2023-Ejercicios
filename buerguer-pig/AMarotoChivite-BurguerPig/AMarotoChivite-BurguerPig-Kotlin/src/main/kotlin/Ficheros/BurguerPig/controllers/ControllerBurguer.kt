package Ficheros.BurguerPig.controllers

import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.repositories.RepositoryBurguer
import mu.KotlinLogging


class ControllerBurguer(private val repository: RepositoryBurguer) {

    private val logger = KotlinLogging.logger {}

    /**
     * Guardamos todas las hamburguesas en el tipo de fichero inyectado en el Main,
     * y se creará el fichero en data
     */
    fun saveInFile() {
        logger.debug { "Controller: Escribiendo hamburguesas (sobreescribiendo)" }
        repository.saveAllBurguersInFileOverride()
    }

    /**
     * Leemos de nuestros ficheros creados en data, que nos redirigirá al repository
     * @return lista de hamburguesas ya leídas de nuestros ficheros desde el repository
     */
    fun readInFile(): List<Burguer> {
        logger.debug { "Controller: Leyendo hamburguesas" }
        return repository.readAllBurguersInFile()
    }
}