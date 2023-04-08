package Ficheros.BurguerPig.storages

import Ficheros.BurguerPig.models.Burguer

interface IStorageGeneral<Model> {
    fun saveInFile(toList: List<Model>)
    fun readAllModelsInFile(): List<Model>
}