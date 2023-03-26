import models.AccidenteList

fun main(){

    //creamos un objeto e iniciamos logger
    val logger = mu.KotlinLogging.logger {}
    logger.debug {"Hola Logger"}
    var listaAc= AccidenteList()
    //archivo cargado ahora podemos hacer las consultas
    println("   CONSULTAS")
    println("   ---------")
    println("")
    logger.debug {""}
    println(" Accidentes en los que estan implicados alcohol o drogas.")
    println("   ---------")
    println("")
     println(listaAc.listaAccidentes.filter { it.positiva_alcohol=="S"||it.positiva_droga=="1" }.size)
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes que han dado positivo en alcohol y drogas.")
    println("   ---------")
    println("")
    listaAc.listaAccidentes.filter { it.positiva_alcohol=="S"&& it.positiva_droga=="1" }
        .forEach { println(it.toString()) }
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por sexo.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.sexo }.map { "${it.key} ${it.value.size}" }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por mes.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.fecha.split("/")[1]}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por tipos de vehiculos.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.tipo_vehiculo}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes ocurridos en la calle de leganes.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.localizacion.contains("LEGANES") })
    println("")
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes por distrito.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}" }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Estadisticas por distrito.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}" +
               " ${it.value.groupBy { it.sexo }.map {"${it.key} ${it.value.size}"}}"
                // + "${it.value.groupBy { it.tipo_vehiculo }.map { "${it.key} ${it.value.size}" }}"
            }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes por distrito ordenadas de manera descendente")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})
    )
    println("")
    println("   ---------")
    logger.debug {""}
    println("Listado de accidentes que se produzcan en fin de semana " +
            "y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.hora.split(":")[0].toInt()>= 20 ||
            it.hora.split(":")[0].toInt()<= 6 }
        //filtro por fin de semana todo puede ir en el mismo filtro pero lo pongo separado para que se vea mejor
        .filter { Finde(it.fecha)}
        .joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Listado de accidentes que se produzcan en fin de semana , de noche donde se haya dado positivo en alcohol")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.hora.split(":")[0].toInt()>= 20 ||
            it.hora.split(":")[0].toInt()<= 6 }
        //filtro por fin de semana todo puede ir en el mismo filtro pero lo pongo separado para que se vea mejor
        .filter { Finde(it.fecha) && it.positiva_alcohol == "S"}
        .joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes donde haya un fallecido")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter {it.cod_lesividad =="4"})
    println("")
    println("   ---------")
    logger.debug {""}
    println("Comprobar si el distrito con mas accidentes coincide con el distrito " +
            "donde hay más accidentes en fin de semana.")
    println("   ---------")
    println("")
    println("Distrito con mas accidentes: "+listaAc.listaAccidentes.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})[0]
    )
    println("Distrito max en fin de semana: "+listaAc.listaAccidentes.filter{ Finde(it.fecha)}.groupBy { it.distrito}
        .map { "${it.key} ${it.value.size}"}.toMutableList()
        .sortedWith(compareByDescending { it.split(" ").last().toInt()})[0]
    )
    println("")
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes donde ha habido (alcohol o drogas) y victimas mortales.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.cod_lesividad=="4" &&
            it.positiva_alcohol=="S" || it.positiva_droga=="1" }.size)
    println("")
    println("   ---------")
    logger.debug {""}
    println("Numero de accidentes donde ha habido atropellos a personas.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.tipo_accidente=="Atropello a persona" }.size)
    println("")
    println("   ---------")
    logger.debug {""}
    println("Accidentes agrupados por estado metereológio.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.estado_meteorológico}
        .map { "${it.key} ${it.value.size}"
        }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println("Lista de accidentes donde haya habido un atropello animal")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.filter { it.tipo_accidente=="Atropello a animal" }.joinToString())
    println("")
    println("   ---------")
    logger.debug {""}
    println(" EXTRA Numero de accidentes por edad.")
    println("   ---------")
    println("")
    println(listaAc.listaAccidentes.groupBy { it.rango_edad }.map{"${it.key} ${it.value.size}"}.joinToString())
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
