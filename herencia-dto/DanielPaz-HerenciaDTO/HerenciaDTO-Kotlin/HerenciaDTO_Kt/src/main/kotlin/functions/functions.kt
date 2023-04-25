package functions

import com.squareup.moshi.JsonAdapter
import dto.PersonasDto
import mappers.toDto
import mappers.toPersona
import models.Alumno
import models.Persona

fun <T> JsonAdapter<T>.toPrettyJson(value: T) = this.indent("  ").toJson(value)!!


fun menu(){
    println("******************************")
    println("1. Alumno mas joven.")
    println("2. Alumno mas viejo.")
    println("3. Media edad alumnos.")
    println("4. Media longitud nombre.")
    println("5. Listado agrupados por tipo.")
    println("******************************\n")
    print("Seleccione opcion:")
}

fun leerRespuesta(): Int{
    var respuesta = readln().toIntOrNull()?:-1

    while (respuesta !in 0..5){
        System.err.print("Opción no válida, inserte otra: ")
        respuesta= readln().toIntOrNull()?:-1
    }

    return respuesta
}

fun findJoven(personas:List<Persona>):Alumno{
    return personas.filterIsInstance<Alumno>().minBy { it.edad }
}

fun findMayor(personas: List<Persona>):Alumno{
    return personas.filterIsInstance<Alumno>().maxBy { it.edad }
}

fun mediaEdad(personas: List<Persona>):Double{
    return personas.filterIsInstance<Alumno>().map { it.edad }.average()
}

fun mediaLongitudNombre(personas: List<Persona>):Int{
    return personas.map { it.nombre.length }.average().toInt()
}

fun groupTipo(personas: List<Persona>):Map<String,List<Persona>>{
    val agrupacionDto = personas.toDto().personas.groupBy{it.tipo}

   return agrupacionDto.mapValues { it.value.map { p -> p.toPersona() }}
}
