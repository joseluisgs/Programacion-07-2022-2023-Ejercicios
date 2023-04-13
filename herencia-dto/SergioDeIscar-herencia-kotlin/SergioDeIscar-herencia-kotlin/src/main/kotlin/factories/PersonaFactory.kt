package factories

import models.Alumno
import models.Persona
import models.Profesor

object PersonaFactory {
    fun getRdnPersonas(): List<Persona>{
        val list = mutableListOf<Persona>()
        repeat(20){ list.add(getRdnPersona()) }
        return list
    }

    fun getRdnPersona(): Persona{
        return when((0..100).random()){
            in 0..10 -> Alumno(
                nombre = getRdnNombre(),
                edad = (18..40).random()
            )
            else -> Profesor(
                nombre = getRdnNombre(),
                modulo = getRdnModulo()
            )
        }
    }

    private fun getRdnNombre(): String{
        return arrayOf("Pepe", "Juan", "Ana").random()
    }
    private fun getRdnModulo(): String{
        return arrayOf("Programación", "Entornos").random()
    }
}