package factory

import models.Alumno
import models.Persona
import models.Profesor
import mu.KotlinLogging

object PersonaFactory {

    private val logger = KotlinLogging.logger {  }

    fun createPersonasRandom(numPersonas: Int): List<Persona>{
        logger.debug { "Se crean un total de $numPersonas personas, de forma aleatoria." }
        val listaPersonas = mutableListOf<Persona>()
        val nombres = arrayOf("Iván", "Alberto", "Roberto", "Erfesto", "Nefesto", "Detesto", "Alex", "Alexa", "Alejandrino", "Nefer", "Romeo", "Julieta", "Ash", "Dante", "Nero")
        repeat(numPersonas){
            val chance = (1..100).random()
            if(chance <= 10){
                val modulos = arrayOf("Programacion", "Entornos")
                listaPersonas.add(
                    Profesor(
                        nombre = nombres.random(),
                        modulo = modulos.random()
                    )
                )
            }else{
                val edad = (18..40).random()
                listaPersonas.add(
                    Alumno(
                        nombre = nombres.random(),
                        edad = edad
                    )
                )
            }
        }
        return listaPersonas
    }
}