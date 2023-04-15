package Repositories

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.*
import org.simpleframework.xml.core.Persister
import java.io.File
import java.lang.IllegalArgumentException

class ArchivoXMLRepository( val programPatch: String): IArchivosAEMET {
    val logger = mu.KotlinLogging.logger {}
    init{
        logger.debug { "INICIANDO ArchivoXMLRepository " }
    }

    override var archivo: RegistroGeneralRDRM = RegistroGeneralRDRM()
    override fun leer(): RegistroGeneralRDRM?{
        val serializer = Persister()
        archivo= serializer.read(RegistroGeneralRDRM::class.java, File(programPatch))
        return archivo
    }

    override fun escribir() {
        val serializer = Persister()
        serializer.write(archivo, File(programPatch))
    }

    override fun leerArchivoOriginalCSV(ficheroEntrada: File): RegistroDiarioRM {
        val fichero = leerArchivoOriginal(ficheroEntrada)
        val nombre = ficheroEntrada.name.replace("Aemet" ,"").split(".")[0]
        val nombreSalida:String = "${nombre[6]}${nombre[7]}/${nombre[4]}${nombre[5]}/" +
                "${nombre[0]}${nombre[1]}${nombre[2]}${nombre[3]}  "
        return RegistroDiarioRM(nombreSalida,fichero)
    }
    private  fun leerArchivoOriginal(ficheroEntrada: File): MutableList<RegistroMeteo> {
        val fichero = ficheroEntrada
        fichero.useLines { lines ->
            return lines
                // separo por comas
                .map { linea -> linea.split(";") }
                // convierto a Accidente
                .map { columnas ->
                    RegistroMeteo(
                        nombreEstacion = columnas[0],
                        provincia = columnas[1],
                        temperaturaMax = RegistroTemperatura(temperatura = columnas[2], horaTemperatura = columnas[3]),
                        temperaturaMin = RegistroTemperatura(temperatura = columnas[4], horaTemperatura = columnas[5]),
                        precipitacion = columnas[6]
                    )
                }.toMutableList()
        }}

    override fun addRegistro(RegistroDiario:RegistroDiarioRM ) {
        archivo.registro.add(RegistroDiario)
    }
    override fun informeMadrid(ficheroEntrada: File) {
        //primero llamamos a la funcion leerCsvOriginal para que nos de el registroDiario
        var registroDiario = leerArchivoOriginalCSV(ficheroEntrada)
        var temperaturaMedia:String = ""
        var temperaturaMaxima:String= ""
        var horaTemperaturaMaxima:String = ""
        var estacionTemperaturaMaxima:String =""
        var cantidadYLugar:Array<String> = arrayOf()
        //ahora ya podemos realizar las consultas y genererar el informe.
        println("")
        var informe: String = "      INFORME DIARIO COMUNIDAD DE MADRID DIA:${registroDiario.dia}\n" +
                "      ---------------------------------------------------- \n" +
                "      - Temeperatura Media Comunidad: "
        registroDiario.registros
            .filter { it.provincia == "Madrid" }
            .groupBy { it.provincia }
            .forEach { (provincia, registros) ->
                val tempMedia = registros.map { registro ->
                    ((registro.temperaturaMax.temperatura.toDouble() +
                            registro.temperaturaMin.temperatura.toDouble()) / 2)
                }.average()
                val tempsalida = ((tempMedia * 100).toInt() / 100).toDouble().toString()
                temperaturaMedia = tempsalida
                informe += "$tempsalida ºC  \n"
            }
        informe += "      - Temperatura Maxima (Lugar y Momento): "
        var consulta = registroDiario.registros
            .filter { it.provincia == "Madrid" }
            .maxByOrNull { it.temperaturaMax.temperatura }
        informe += "${consulta!!.temperaturaMax.temperatura}ºC en estacion de ${consulta.nombreEstacion}" +
                "a las ${consulta.temperaturaMax.horaTemperatura}\n"

        temperaturaMaxima = consulta!!.temperaturaMax.temperatura
        horaTemperaturaMaxima = consulta.temperaturaMax.horaTemperatura
        estacionTemperaturaMaxima = consulta.nombreEstacion

        informe += "      - Temperatura Minima (Lugar y Momento): "
        consulta = registroDiario.registros
            .filter { it.provincia == "Madrid" }
            .minByOrNull { it.temperaturaMin.temperatura }
        informe += "${consulta!!.temperaturaMin.temperatura}ºC en estacion de ${consulta.nombreEstacion}" +
                " a las ${consulta.temperaturaMin.horaTemperatura}\n"

        var listaRegistro: List<RegistroMeteo> = mutableListOf()
        var isRainningMan: Boolean = false
        registroDiario.registros
            // si se quiere probar informe con precipitacion eliminar filtro
            .filter { it.provincia == "Madrid" }
            .forEach {
                if (it.precipitacion.toDouble() > 0.0) {
                    isRainningMan = true
                    listaRegistro += it
                }
            }
        informe += "      - Precipitaciones: ${isRainningMan} \n"
        if(isRainningMan){
            for(i in listaRegistro.indices){
                informe +="      ${listaRegistro[i].precipitacion} l/m en estacion ${listaRegistro[i].nombreEstacion} \n"
                cantidadYLugar+=("  ${listaRegistro[i].precipitacion} l/m en estacion ${listaRegistro[i].nombreEstacion} \n")
            }
        }
        println(informe)
        //ahora podemos guardar el Informe. Me parecia soso guardar el informe como texto plano. vamos a rescatar
        // las consultas como valores en el objeto por si los necesitaramos posteriormente.
        val informeComoObjeto = Informe(fecha = DimeFecha(ficheroEntrada), temperaturaMedia = temperaturaMedia,
            temperaturaMaxima=temperaturaMaxima, horaTemperaturaMaxima = horaTemperaturaMaxima,
            estacionTemperaturaMaxima = estacionTemperaturaMaxima,temperaturaMinima = consulta.temperaturaMin.temperatura,
            horaTemperaturaMinima =consulta.temperaturaMin.horaTemperatura,estacionTemperaturaMinima=consulta.nombreEstacion
            , precipitacion = isRainningMan, cantidadYLugar = cantidadYLugar )
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Informe::class.java)
        val json = jsonAdapter.indent("   ").toJson(informeComoObjeto)

        File("${System.getProperty("user.dir")}${File.separator}data${File.separator}" +
                "InformePelicanoMadrid.json").writeText(json)

        val serializer = Persister()
        serializer.write(informeComoObjeto, File("${System.getProperty("user.dir")}${File.separator}data${File.separator}" +
                "InformePelicanoMadrid.xml"))
    }
    private fun DimeFecha(ficheroEntrada: File):String{
        var nombre = ficheroEntrada.name.replace("Aemet","")
        var año = nombre.substring(startIndex = 0, endIndex =  4)
        var mes = nombre.substring(startIndex = 4, endIndex =  6)
        var dia = nombre.substring(startIndex = 6, endIndex =  8)
        return dia+"/"+mes+"/"+año
    }
}
