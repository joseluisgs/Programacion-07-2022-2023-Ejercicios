import Controllers.MeteoControler
import Repositories.ArchivoCSVRepository
import java.io.File

fun main() {

    val logger = mu.KotlinLogging.logger {}
    logger.debug { "Hola Meteo. Main " }
    // Esta parte es el codigo que use para generar el primer csv fusion
    /***
    val fichero = ArchivoCSVRepository(
    "${System.getProperty("user.dir")}${File.separator}data${File.separator}fusion.csv")
    var reg1 = fichero.leerArchivoOriginalCSV(
    File("${System.getProperty("user.dir")}${File.separator}data${File.separator}Aemet20171029.csv"))
    var reg2 = fichero.leerArchivoOriginalCSV(
    File("${System.getProperty("user.dir")}${File.separator}data${File.separator}Aemet20171030.csv"))
    var reg3 = fichero.leerArchivoOriginalCSV(
    File("${System.getProperty("user.dir")}${File.separator}data${File.separator}Aemet20171031.csv"))
    println("Ahora añadir")
    fichero.archivo.registro.add(reg1)
    fichero.addRegistro(reg2)
    fichero.archivo.registro.add(reg3)
     ***/
    //
    val registroGeneral =MeteoControler( ArchivoCSVRepository(
        "${System.getProperty("user.dir")}${File.separator}data${File.separator}fusion.csv"
    ))
    // leemos el archivo esto se podria poner automatico y seria mejor, pero lo dejo asi para que se vea el paso
    registroGeneral.repositorio.archivo = registroGeneral.leer()!!

    println("CONSULTAS")
    println("----------")
    logger.debug { "TEMPERATURAS MAXIMAS X DIA " }
    registroGeneral.repositorio.archivo.registro.forEach {
        print("${it.dia}   \n")
        it.registros
            //pongo take para coger solo 5 por dia para facilidad visual
            .take(5)
            .forEach { print("[${it.nombreEstacion} ${it.temperaturaMax.temperatura}] ") }
        println("")
    }
        println("")
        println("----------")
        logger.debug { "TEMPERATURA MINIMA X DIA " }
        registroGeneral.repositorio.archivo.registro.forEach {
            print("${it.dia}   \n")
            it.registros
                //pongo take para coger solo 5 por dia para facilidad visual
                .take(5)
                .forEach { print("[${it.nombreEstacion} ${it.temperaturaMin.temperatura}] ") }
            println("")}
        logger.debug { "TEMPERATURA Max X PROVINCIA Y DIA " }
    val consultaA = registroGeneral.repositorio.archivo.registro.joinToString("\n") { registro ->
                "${registro.dia}, " +
                            registro.registros.groupBy { it.provincia }
                            .map { provincia -> "${provincia.key} ${
                            provincia.value.maxOfOrNull { it.temperaturaMax.temperatura } ?: "N/A"
                        }"
                    }.joinToString(" ")
    }
    println(consultaA)
    logger.debug { "TEMPERATURA Max X PROVINCIA  (día, nombreEstacion, temperatura)" }
    // Esta consulta me dio dolores de cabeza. Al final la resolvi creando una lista con los valores de provincia
    val provincias = registroGeneral.repositorio.archivo.registro.flatMap { it.registros }.map { it.provincia }.distinct().toList()
    var respuesta1:String = ""
    // ahora recorro la lista buscando el mayor valor para cada una de las provincias y filtro todos los dias con un if
    for(i in provincias.indices){
        var estacion:String = ""
        var temp:Double = 0.0
        var hora:String = ""
        var dia:String = ""
        registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
            registroDiarioRM.registros.filter { it.provincia == provincias[i] }
                .maxByOrNull { it.temperaturaMax.temperatura }?.let { registroMeteo ->
                    if(registroMeteo.temperaturaMax.temperatura.toDouble() > temp){
                        estacion = registroMeteo.nombreEstacion
                        temp = registroMeteo.temperaturaMax.temperatura.toDouble()
                        hora = registroMeteo.temperaturaMax.horaTemperatura
                        dia = registroDiarioRM.dia
                    }
                }
        }
        respuesta1 += "${provincias[i]} ${temp}ºC El ${dia.trim()} a las $hora en la estación de $estacion.   \n"
    }
    println(respuesta1)
    logger.debug { "TEMPERATURA min X PROVINCIA  (día, nombre, estación, temperatura)" }
    var respuesta2:String = ""
    // ahora recorro la lista buscando el mayor valor para cada una de las provincias y filtro todos los dias con un if
    for(i in provincias.indices){
        var estacion:String = ""
        var temp:Double = 50.0
        var hora:String = ""
        var dia:String = ""
        registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
            registroDiarioRM.registros.filter { it.provincia == provincias[i] }
                .minByOrNull { it.temperaturaMin.temperatura }?.let { registroMeteo ->
                    if(registroMeteo.temperaturaMin.temperatura.toDouble() < temp){
                        estacion = registroMeteo.nombreEstacion
                        temp = registroMeteo.temperaturaMin.temperatura.toDouble()
                        hora = registroMeteo.temperaturaMin.horaTemperatura
                        dia = registroDiarioRM.dia
                    }
                }
        }
        respuesta2 += "${provincias[i]} ${temp}ºC El ${dia.trim()} a las $hora en la estación de $estacion.   \n"
    }
    println(respuesta2)

    logger.debug { "Temperatura media por provincia (día y valor)" }
    registroGeneral.repositorio.archivo.registro.forEach { registroDiario ->
        println("Día ${registroDiario.dia.trim()}")
        registroDiario.registros.groupBy { it.provincia }
            .forEach { (provincia, registros) ->
                val temperaturaMedia = registros.map { it.temperaturaMax.temperatura.toDouble() }
                    .average()
                var temperaturaString = ((temperaturaMedia*100).toInt()/100).toDouble().toString()
                println("- COMUNIDAD DE $provincia: $temperaturaString ºC")
            }
    }
    logger.debug { " Listado de precipitación media por día y provincia" }
    registroGeneral.repositorio.archivo.registro.forEach { rd ->
        println("Dia:  ${rd.dia}")
        rd.registros.groupBy { it.provincia }.forEach { (provincia, registros) ->
            val precipitacionMedia = registros.map { it.precipitacion.toDouble() }
                .average()
            var precipitacionSalida = ((precipitacionMedia*100).toInt()/100).toDouble().toString()
            println("- $provincia: $precipitacionSalida l/m3")
        }}
    logger.debug { "Numero de lugares en el que llovío por día y provincia" }
    registroGeneral.repositorio.archivo.registro.forEach { rd ->
        println("Dia:  ${rd.dia}")
        rd.registros.filter { it.precipitacion.toDouble() > 0.0 }
            .groupBy { it.provincia }.forEach { (provincia, registros) ->
            registros.map { it.precipitacion}
            println("- $provincia: ${registros.size} ")
        }}
    logger.debug { "Temperatura média de la provincia de Madrid" }
    registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
        print("Dia: ${registroDiarioRM.dia.trim()}  ")
        registroDiarioRM.registros.filter { it.provincia=="Madrid" }.groupBy { it.provincia }
            .forEach { (provincia, registros) ->
            val temperaturaMedia = registros.map { registro ->
                ((registro.temperaturaMax.temperatura.toDouble() + registro.temperaturaMin.temperatura.toDouble()) / 2)
            }.average()
            val tempsalida = ((temperaturaMedia*100).toInt()/100).toDouble().toString()
            println("- $provincia: $tempsalida ºC")
        }}
        logger.debug { "Media de temperatura máxima total" }
        registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
            print("Dia: ${registroDiarioRM.dia.trim()}  ")
            val temperaturamedia = registroDiarioRM.registros.map { it.temperaturaMax.temperatura.toDouble() }.average()
            val tempsalida = ((temperaturamedia * 100).toInt() / 100).toDouble().toString()
            println("- Media temperatura maxima Españita: $tempsalida ºC")
        }

       logger.debug { "Media de temperatura minima total" }
    registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
        print("Dia: ${registroDiarioRM.dia.trim()}  ")
        val temperaturamedia = registroDiarioRM.registros.map { it.temperaturaMin.temperatura.toDouble() }.average()
        val tempsalida = ((temperaturamedia * 100).toInt() / 100).toDouble().toString()
        println("- Media temperatura minima Españita: $tempsalida ºC")}

    logger.debug { "Lugares donde la máxima ha sido antes de las 15:00 por día" }
    registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
        println("Dia: ${registroDiarioRM.dia.trim()}  ")
        val LugaresMaxPre = registroDiarioRM.registros
            .filter { it.temperaturaMax.horaTemperatura.split(":")[0].toInt() < 15 }
            // como antes cojo solo 5 por claridad visual
            .take(5).forEach {
                println("- Estacion: ${it.nombreEstacion} Temperatura: ${it.temperaturaMax.temperatura}ºC hora: " +
                        it.temperaturaMax.horaTemperatura
                )
            }}

    logger.debug { "Lugares donde la mínima ha sido después de las 17:30 por día" }
    registroGeneral.repositorio.archivo.registro.forEach { registroDiarioRM ->
        println("Dia: ${registroDiarioRM.dia.trim()}  ")
        val LugaresMinPre = registroDiarioRM.registros
            .filter { it.temperaturaMin.horaTemperatura.split(":")[0].toInt() >= 17
                    && it.temperaturaMin.horaTemperatura.split(":")[1].toInt() > 30 }
            .forEach {
                println("- Estacion: ${it.nombreEstacion} Temperatura: ${it.temperaturaMin.temperatura}ºC hora: " +
                        it.temperaturaMin.horaTemperatura
                )
            }}

    logger.debug { "GENERAR INFORME COMUNIDAD DE MADRID" }

    registroGeneral.informeMadrid(
        File("${System.getProperty("user.dir")}${File.separator}data${File.separator}Aemet20171031.csv"))




    }

