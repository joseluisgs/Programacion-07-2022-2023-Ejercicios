package Ficheros.Aemet.repositories

import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.storages.IStorageToImportExport
import mu.KotlinLogging

class RepositoryToImportExport(private val storage: IStorageToImportExport<Aemet>) : IRepositoryToImportExport<Aemet> {

    private val logger = KotlinLogging.logger {}

    private var repository = listOf<Aemet>()

    // Actualizamos los datos del repositorio si queremos manipular con otros datos
    fun saveItems(list: List<Aemet>) {
        repository = list
    }

    override fun saveAllModelsInFileOverride() {
        logger.debug { "Repository: Escribiendo y (sobreescribiendo)" }
        storage.saveInFileWithFilter(repository.toList())
    }

    override fun readAllModelsInFile(): List<Aemet> {
        logger.debug { "Repository: Leyendo" }
        return storage.readAllModelsInFile()
    }
}