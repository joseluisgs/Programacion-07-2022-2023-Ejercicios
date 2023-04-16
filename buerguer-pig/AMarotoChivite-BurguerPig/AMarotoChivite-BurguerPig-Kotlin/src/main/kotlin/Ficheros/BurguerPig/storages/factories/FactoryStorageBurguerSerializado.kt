package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerSerializado

class FactoryStorageBurguerSerializado {
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerSerializado())
    }
}
