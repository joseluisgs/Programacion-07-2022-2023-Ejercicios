package Ficheros.Accidentes.controllers

import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.repositories.IRepositoryToImportExport
import mu.KotlinLogging

class ControllerPrincipal(private val repositoryToExportImport: IRepositoryToImportExport<AccidenteDTO>) :
    IRepositoryToImportExport<AccidenteDTO> {

    private val logger = KotlinLogging.logger {}

    override fun readAllModelsInFile(): List<AccidenteDTO> {
        logger.debug { "Controller: Leyendo de fichero" }
        return repositoryToExportImport.readAllModelsInFile()
    }

    override fun saveAllModelsInFile() {
        logger.debug { "Controller: Escribiendo en fichero" }
        repositoryToExportImport.saveAllModelsInFile()
    }

}