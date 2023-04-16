package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerCSV

class FactoryStorageBurguerCSV {
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerCSV())
    }
}