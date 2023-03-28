package models

import dto.PersonaDto

abstract class Persona(
    val id: Int = count++,
    val nombre: String
){
    abstract fun toDto(): PersonaDto
    abstract fun toCsvRow(): String
    companion object{
        var count = 0
    }
    fun nextId(): Int{
        return count++
    }
}
