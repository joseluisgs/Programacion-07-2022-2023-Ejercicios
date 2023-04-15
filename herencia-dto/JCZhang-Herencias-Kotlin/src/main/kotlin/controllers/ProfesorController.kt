package controllers

import repositories.ProfesorRepository


class ProfesorController(private val repository: ProfesorRepository) {
    fun save(){
        repository.save()
    }

    fun load(){
        repository.load()
    }
}