package Ficheros.Aemet.repositories

import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.storages.IStorageToExport
import mu.KotlinLogging

class RepositoryToExport(private val storage: IStorageToExport<Aemet>) : IRepositoryToExport {

    private val logger = KotlinLogging.logger {}

    private var repository = listOf<Aemet>()

    // Actualizamos los datos del repositorio si queremos manipular con otros datos
    fun saveItems(list: List<Aemet>) {
        repository = list
    }

    override fun saveAllModelsInFileOverride() {
        logger.debug { "Repository: Escribiendo" }
        storage.saveInFileWithFilter(repository.toList())
    }

    fun saveInFileDecisionProvincia(provincia: String) {
        logger.debug { "Repository: Escribiendo fichero para la provincia $provincia" }

        // Filtro del informe que requerimos por provincia
        val filterRepository = repository.filter { it.nombreProvincia == provincia }
        storage.saveInFileWithFilter(filterRepository.toList())
    }
}