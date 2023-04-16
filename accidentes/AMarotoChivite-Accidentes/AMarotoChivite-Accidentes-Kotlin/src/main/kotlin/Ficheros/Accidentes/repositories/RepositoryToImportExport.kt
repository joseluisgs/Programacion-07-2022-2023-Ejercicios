package Ficheros.Accidentes.repositories

import Ficheros.Accidentes.models.dto.AccidenteDTO
import Ficheros.Accidentes.storages.IStorageToImportExport
import mu.KotlinLogging

class RepositoryToImportExport(private val storage: IStorageToImportExport<AccidenteDTO>) :
    IRepositoryToImportExport<AccidenteDTO> {

    private val logger = KotlinLogging.logger {}

    override fun saveAllModelsInFile() {
        logger.debug { "Repository: Escribiendo en fichero" }
        storage.saveInFileWithFilter()
    }

    override fun readAllModelsInFile(): List<AccidenteDTO> {
        logger.debug { "Repository: Leyendo de fichero" }
        return storage.readAllModelsInFile()
    }
}