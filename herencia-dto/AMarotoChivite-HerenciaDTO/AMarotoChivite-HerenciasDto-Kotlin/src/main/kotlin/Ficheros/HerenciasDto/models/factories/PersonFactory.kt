package Ficheros.HerenciasDto.models.factories

import Ficheros.HerenciasDto.models.dto.PersonDTO
import kotlin.random.Random

class PersonaFactory {
    private val modulos = listOf("Programación", "Entornos")
    private val nombres = listOf(
        "Juan", "Ana", "Pedro", "María", "Luis", "Carla", "David", "Elena",
        "Fernando", "Sara", "Javier", "Lucía", "Miguel", "Pilar", "Rosa", "Sofía",
        "Diego", "Jorge", "Laura", "Raquel"
    )

    private fun createRandomPersona(): PersonDTO {
        val random = Random.nextDouble()
        val name = nombres.random()
        val edad = (18..40).random()

        return if (random < 0.1) {
            val modulo = modulos.random()
            PersonDTO.Profesor(name, edad.toString(), modulo)
        } else {
            PersonDTO.Alumno(name, edad.toString())
        }
    }

    fun createPersonas(n: Int): List<PersonDTO> {
        val personas = mutableListOf<PersonDTO>()
        repeat(n) {
            personas.add(createRandomPersona())
        }
        return personas
    }
}