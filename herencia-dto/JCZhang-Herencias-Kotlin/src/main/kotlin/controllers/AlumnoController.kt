package controllers

import repositories.AlumnoRepository

class AlumnoController(private val repository: AlumnoRepository) {
    fun save(){
        repository.save()
    }

    fun load(){
        repository.load()
    }
}
