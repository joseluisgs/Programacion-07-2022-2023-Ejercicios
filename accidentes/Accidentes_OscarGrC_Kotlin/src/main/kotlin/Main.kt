import repositories.AccidentesRespository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(){

    //iniciamos logger
    val logger = mu.KotlinLogging.logger {}
    logger.debug {"Hola Logger"}
    var repositorio= AccidentesRespository()
    //archivo cargado ahora podemos hacer las consultas
    println("   CONSULTAS")
    println("   ---------")
    logger.debug {""}
    println(" Accidentes en los que estan implicados alcohol o drogas.")
    println("   ---------")
     println(repositorio.archivo.listaAccidentes
         .filter { it.positiva_alcohol=="S"||it.positiva_droga=="1" }.size)
    println("   ---------")
    logger.debug {""}
    println("Accidentes que han dado positivo en alcohol y drogas.")
    println("   ---------")
    repositorio.archivo.listaAccidentes
        .filter { it.positiva_alcohol=="S"&& it.positiva_droga=="1" }
            //pongo solo 5 por claridad cuando lo ejecutes
        .take(5)
        .forEach { println(it.toString()) }
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por sexo.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.sexo }.map { "${it.key} ${it.value.size}" }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por mes.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.fecha.split("/")[1]}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por tipos de vehiculos.")
    println("   ---------")
    println("")
    println(repositorio.archivo.listaAccidentes.groupBy { it.tipo_vehiculo}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Accidentes ocurridos en la calle de leganes.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.filter { it.localizacion.contains("LEGANES") }.take(5))
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes por distrito.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Estadisticas por distrito.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}" +
               " ${it.value.groupBy { it.sexo }.map {"${it.key} ${it.value.size}"}}"
                // + "${it.value.groupBy { it.tipo_vehiculo }.map { "${it.key} ${it.value.size}" }}"
            }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Accidentes por distrito ordenadas de manera descendente")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})
    )
    println("   ---------")
    logger.debug {""}
    println("Listado de accidentes que se produzcan en fin de semana " +
            "y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes

        .filter { it.hora.split(":")[0].toInt()>= 20 ||
            it.hora.split(":")[0].toInt()<= 6 }
        //filtro por fin de semana todo puede ir en el mismo filtro pero lo pongo separado para que se vea mejor
        // el usar una fun a manija no te gusto asi que lo dejo comentado
        // .filter { Finde(it.fecha)}
        .filter { (it.fecha.toLocalDate().dayOfWeek.value == 6) || (it.fecha.toLocalDate().dayOfWeek.value == 7) }
        .take(5).joinToString())
    println("   ---------")
    logger.debug {""}
    println("Listado de accidentes que se produzcan en fin de semana , de noche donde se haya dado positivo en alcohol")
    println("   ---------")
    println("")
    println(repositorio.archivo.listaAccidentes

        .filter { it.hora.split(":")[0].toInt()>= 20 ||
            it.hora.split(":")[0].toInt()<= 6 }
        //filtro por fin de semana todo puede ir en el mismo filtro pero lo pongo separado para que se vea mejor
        .filter { Finde(it.fecha) && it.positiva_alcohol == "S"}
        .take(5).joinToString())
    println("   ---------")
    logger.debug {""}
    println("Accidentes donde haya un fallecido")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes
        .filter {it.cod_lesividad =="4"} .take(5))
    println("   ---------")
    logger.debug {""}
    println("Comprobar si el distrito con mas accidentes coincide con el distrito " +
            "donde hay más accidentes en fin de semana.")
    println("   ---------")
    println("Distrito con mas accidentes: "+repositorio.archivo.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})[0]
    )
    println("Distrito max en fin de semana: "+repositorio.archivo.listaAccidentes.filter{ Finde(it.fecha)}.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})[0]
    )
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes donde ha habido (alcohol o drogas) y victimas mortales.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.filter { it.cod_lesividad=="4" &&
            it.positiva_alcohol=="S" || it.positiva_droga=="1" }.size)
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes donde ha habido atropellos a personas.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.filter { it.tipo_accidente=="Atropello a persona" }.size)
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por estado metereológio.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.estado_meteorologico}
        .map { "${it.key} ${it.value.size}"
        }.joinToString())
    println("   ---------")
    logger.debug {""}
    println("Lista de accidentes donde haya habido un atropello animal")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes
        .filter { it.tipo_accidente=="Atropello a animal" }.take(5).joinToString())

    // ahora se nos pide guardarlo como json y xml
    logger.debug {"GUARDADO COMO JSON Y XML"}
    repositorio.whriteJSON()
    repositorio.whriteXML()
    // ahora los recuperamos
    logger.debug {"LECTURA COMO JSON"}
    repositorio.readJSON()
    //repito una consulta para comprobar
    println("Accidentes agrupados por sexo.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.sexo }.map { "${it.key} ${it.value.size}" }.joinToString())
    // ahora el xml
    logger.debug {"LECTURA COMO XML"}
    repositorio.readXML()
    println("Accidentes agrupados por sexo.")
    println("   ---------")
    println(repositorio.archivo.listaAccidentes.groupBy { it.sexo }.map { "${it.key} ${it.value.size}" }.joinToString())


}


fun Finde(fecha:String):Boolean{
    var fechaLocal = fecha

    when(fechaLocal.split("/")[1]){
        "01","10"-> if ((fechaLocal.split("/")[0] == "02") || (fechaLocal.split("/")[0] == "03") ||
                   (fechaLocal.split("/")[0] == "09") || (fechaLocal.split("/")[0] == "10") ||
                   (fechaLocal.split("/")[0] == "16") || (fechaLocal.split("/")[0] == "17") ||
                   (fechaLocal.split("/")[0] == "23") || (fechaLocal.split("/")[0]== "24") ||
                   (fechaLocal.split("/")[0]== "30") || (fechaLocal.split("/")[0]== "31")) return true
        "02","03","11"-> if ((fechaLocal.split("/")[0] == "06") || (fechaLocal.split("/")[0] == "07") ||
                   (fechaLocal.split("/")[0] == "13") || (fechaLocal.split("/")[0] == "14") ||
                   (fechaLocal.split("/")[0] == "20") || (fechaLocal.split("/")[0] == "21") ||
                   (fechaLocal.split("/")[0] == "27") || (fechaLocal.split("/")[0] == "28")) return true
        "04"-> if ((fechaLocal.split("/")[0] == "03") || (fechaLocal.split("/")[0] == "04") ||
                   (fechaLocal.split("/")[0] == "10") || (fechaLocal.split("/")[0] == "11") ||
                   (fechaLocal.split("/")[0] == "17") || (fechaLocal.split("/")[0]== "18") ||
                   (fechaLocal.split("/")[0] == "24") || (fechaLocal.split("/")[0] == "25")) return true
        "05"-> if ((fechaLocal.split("/")[0] == "01") || (fechaLocal.split("/")[0] == "02") ||
                   (fechaLocal.split("/")[0] == "08") || (fechaLocal.split("/")[0] == "09") ||
                   (fechaLocal.split("/")[0] == "15") || (fechaLocal.split("/")[0] == "16") ||
                   (fechaLocal.split("/")[0]== "22") || (fechaLocal.split("/")[0] == "23") ||
                   (fechaLocal.split("/")[0] == "29") || (fechaLocal.split("/")[0] == "30")) return true
        "06"-> if ((fechaLocal.split("/")[0] == "05") || (fechaLocal.split("/")[0]== "06") ||
                   (fechaLocal.split("/")[0] == "12") || (fechaLocal.split("/")[0] == "13") ||
                   (fechaLocal.split("/")[0] == "19") || (fechaLocal.split("/")[0] == "20") ||
                   (fechaLocal.split("/")[0] == "26") || (fechaLocal.split("/")[0] == "27")) return true
        "07"-> if ((fechaLocal.split("/")[0] == "03") || (fechaLocal.split("/")[0] == "04") ||
                   (fechaLocal.split("/")[0] == "08") || (fechaLocal.split("/")[0] == "09") ||
                   (fechaLocal.split("/")[0] == "15") || (fechaLocal.split("/")[0] == "16") ||
                   (fechaLocal.split("/")[0] == "22") || (fechaLocal.split("/")[0] == "23") ||
                   (fechaLocal.split("/")[0] == "29") || (fechaLocal.split("/")[0] == "30")) return true
        "08"-> if ((fechaLocal.split("/")[0] == "01") || (fechaLocal.split("/")[0] == "07") ||
                   (fechaLocal.split("/")[0] == "08") || (fechaLocal.split("/")[0] == "14") ||
                   (fechaLocal.split("/")[0] == "15") || (fechaLocal.split("/")[0] == "21") ||
                   (fechaLocal.split("/")[0] == "22") || (fechaLocal.split("/")[0] == "28") ||
                   (fechaLocal.split("/")[0] == "29")) return true
        "09","12"-> if ((fechaLocal.split("/")[0] == "04") || (fechaLocal.split("/")[0] == "05") ||
            (fechaLocal.split("/")[0] == "11") || (fechaLocal.split("/")[0] == "12") ||
            (fechaLocal.split("/")[0] == "18") || (fechaLocal.split("/")[0] == "19") ||
            (fechaLocal.split("/")[0] == "25") || (fechaLocal.split("/")[0] == "26")) return true
    }
    return false
}
// funcion de extension (nunca las suelo usar y son muy comodas) para parsear en vez de hacer DTO
fun String.toLocalDate():LocalDate{

    return LocalDate.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}
