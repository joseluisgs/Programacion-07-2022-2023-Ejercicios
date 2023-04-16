package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerTexto

class FactoryStorageBurguerTexto {
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerTexto())
    }
}
