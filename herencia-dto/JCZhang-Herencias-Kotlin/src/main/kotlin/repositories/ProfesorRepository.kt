package repositories

import enums.modulos
import models.Profesor
import storage.service.ProfesorStorageService

class ProfesorRepository(private val service: ProfesorStorageService) {

    private val profesor1 = Profesor("Pedro", modulos.PROGRAMACION)
    private val profesor2 = Profesor("Olga", modulos.ENTORNOS)
    private val profesores = listOf(profesor1, profesor2)

    fun save(){
        service.saveAll(profesores)
    }

    fun load(){
        service.loadAll()
    }
}