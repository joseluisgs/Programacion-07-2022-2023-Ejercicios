package Ficheros.HerenciasDto.storage.factories

import Ficheros.HerenciasDto.repositories.Repository
import Ficheros.HerenciasDto.storage.StorageXML

class FactoryStorageXML {
    fun create(): Repository {
        return Repository(StorageXML())
    }
}