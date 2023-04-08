package Ficheros.BurguerPig.storages.factories

import Ficheros.BurguerPig.repositories.RepositoryBurguer
import Ficheros.BurguerPig.storages.StorageBurguerMOSHI_JSON

class FactoryStorageBurguerMOSHI_JSON {
    @ExperimentalStdlibApi // Tenemos que propagar la anotación
    fun create(): RepositoryBurguer {
        return RepositoryBurguer(StorageBurguerMOSHI_JSON())
    }
}
