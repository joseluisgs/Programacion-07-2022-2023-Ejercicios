package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerBinario

class FactoryStorageBurguerBinario {
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerBinario())
    }
}
