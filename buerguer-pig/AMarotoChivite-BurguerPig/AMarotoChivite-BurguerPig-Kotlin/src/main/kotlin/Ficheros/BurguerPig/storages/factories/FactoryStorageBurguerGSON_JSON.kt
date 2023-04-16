package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerGSON_JSON

class FactoryStorageBurguerGSON_JSON {
    @ExperimentalStdlibApi // Tenemos que propagar la anotación
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerGSON_JSON())
    }
}
