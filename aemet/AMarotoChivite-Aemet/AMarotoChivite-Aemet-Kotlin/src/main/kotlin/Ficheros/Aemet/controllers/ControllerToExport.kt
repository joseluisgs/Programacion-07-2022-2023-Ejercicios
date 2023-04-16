package Ficheros.Aemet.controllers

import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.repositories.RepositoryToExport

class ControllerToExport(private val repositoryToExport: RepositoryToExport) {

    fun saveInRepository(list: List<Aemet>) {
        repositoryToExport.saveItems(list)
    }

    fun saveInFileDecisionProvincia(provincia: String) {
        repositoryToExport.saveInFileDecisionProvincia(provincia)
    }
}