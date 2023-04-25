package factories

import models.Alumno
import models.Persona
import models.Profesor

const val MAX_PERSONAS = 20
const val MAX_PROFESORES = 2


fun personasFactory():List<Persona>{

    val personas = mutableListOf<Persona>()
    var contadorProfesores = 0


    for (i in 0 until MAX_PERSONAS){

        if ((1..4).random()==1 && contadorProfesores!= MAX_PROFESORES){
            personas.add(
                Profesor(nombreRandom(), modulosRandom())
            )
            contadorProfesores++
        }else{
            personas.add(
                Alumno(nombreRandom(),(18..40).random())
            )
        }
    }
    return personas
}

 fun nombreRandom(): String{

     val nombres = listOf(
         "Daniel",
         "Victor",
         "Sergio",
         "Juana",
         "Marta",
         "Laura",
         "Claudia",
         "Luis",
         "Pepe",
         "Ramon"
     )
     return nombres.random()
 }

 fun modulosRandom(): String{

     val modulos = listOf("Programacion","Entornos")

     return modulos.random()
 }