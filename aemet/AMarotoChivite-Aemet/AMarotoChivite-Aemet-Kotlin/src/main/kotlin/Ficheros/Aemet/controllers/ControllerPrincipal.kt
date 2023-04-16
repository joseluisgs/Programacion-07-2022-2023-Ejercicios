package Ficheros.Aemet.controllers

import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.repositories.RepositoryToImportExport
import mu.KotlinLogging

class ControllerPrincipal(private val repositoryToExportImport: RepositoryToImportExport) {

    private val logger = KotlinLogging.logger {}

    fun saveInRepository(list: List<Aemet>) {
        repositoryToExportImport.saveItems(list)
    }

    fun saveInFile() {
        logger.debug { "Controller: Escribiendo (sobreescribiendo)" }
        repositoryToExportImport.saveAllModelsInFileOverride()
    }

    fun readInFile(): List<Aemet> {
        logger.debug { "Controller: Leyendo" }
        return repositoryToExportImport.readAllModelsInFile()
    }

}