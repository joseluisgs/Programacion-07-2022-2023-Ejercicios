package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerXML

class FactoryStorageBurguerXML {
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerXML())
    }
}
