package repositories

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.*
import okio.buffer
import okio.source
import org.simpleframework.xml.core.Persister
import java.io.File

@ExperimentalStdlibApi
class PersonaRepository {
    var repositorioList:MutableList<Persona> = readCSV()
    val logger = mu.KotlinLogging.logger {}

    init {
        logger.info { "INICIANDO PersonaRepository " }
    }

    /***
     * Funcion que lee un CSV y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    fun readCSV(): MutableList<Persona>  {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.csv"
        val fichero = File(path)
        val lista = fichero.useLines { lines ->
            lines
                // ignoro la primera porque es el encabezado
                .drop(1)
                // separo por comas
                .map { linea -> linea.split(",") }
                // convierto a Persona
                .map { columnas ->
                    //sexamos Alumnos y Profesores por el primer campo que sera o un int o un nombre de la enum class
                    if (enumValues<NombreModulos>().map { it.name }.contains(columnas[0])) {
                        Profesor(
                            columnas[0],
                            columnas[1]
                        )
                    } else {
                        Alumno(
                            columnas[0].toInt(),
                            columnas[1]
                        )
                    }
                }.toMutableList()
        }
        return lista
    }

    /***
     * Funcion que lee un JSON y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun readJSON(): MutableList<Persona>  {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<List<PersonaDTO>> = moshi.adapter<List<PersonaDTO>>()
        val archivo = jsonAdapter.fromJson(
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.json")
                .inputStream().source().buffer()
        )!!
         val listaPersonas:MutableList<Persona> = mutableListOf()
         archivo.forEach { listaPersonas+= it.toPersona() }
        return listaPersonas
    }

    /***
     * Funcion que Lee el XML
     * @return HamburguesaList
     */
    fun readXML(): MutableList<Persona>  {
        val serializer = Persister()
        val archivo = serializer.read(PersonasDTOList::class.java,
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.xml")
        )
        var listaSalida:MutableList<Persona> = mutableListOf()
        archivo.listaPersonasDto.forEach { listaSalida+= it.toPersona() }
        return listaSalida
    }

    /***
     * Funcion que escribe en CSV
     */
    fun whriteCSV() {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.csv"
        val fichero = File(path)
        // si
        // no existe lo creamos
        if (!fichero.exists()) {
            fichero.createNewFile()
        }
        // Escribimos el encabezado, separados por comas
        fichero.writeText("Edad/Modulo,nombre \n")
        repositorioList.forEach {
            when (it) {
                is Alumno -> {
                    fichero.appendText("${it.edad},${it.nombre}\n")
                }

                is Profesor -> {
                    fichero.appendText("${it.modulo},${it.nombre}\n")
                }
            }
        }
        println("CSV guardado en  ${fichero.absolutePath}")
    }

    /***
     * Funcion que escribe el JSON
     */
    @OptIn(ExperimentalStdlibApi::class)
    /**
    fun whriteJSON() {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter<Profesor>()
        val jsonAdapter2 = moshi.adapter<Alumno>()
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.json")
        if (!file.exists()) {
            file.createNewFile()
        }
        file.delete()
        file.appendText("{\n \"listaPersonas\":[")
        for (i in repositorioList.listaPersonas.indices) {
            if (repositorioList.listaPersonas[i] is Profesor) {
                val pivote = repositorioList.listaPersonas[i] as Profesor
                val json = jsonAdapter.indent("   ").toJson(pivote)
                file.appendText(json)
                file.appendText(",\n")
            } else {
                val pivote = repositorioList.listaPersonas[i] as Alumno
                val json = jsonAdapter2.indent("   ").toJson(pivote)
                file.appendText(json)
                if (i < repositorioList.listaPersonas.size - 1) file.appendText(",\n")

            }
        }
        file.appendText("] \n }")
        println("JSON guardado en ${file.absolutePath}")
    } ***/
    fun whriteJSON() {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter<List<PersonaDTO>>()
        var listaDto:MutableList<PersonaDTO> = mutableListOf()
        repositorioList.forEach { listaDto+=it.toDTO() }
        val json = jsonAdapter.indent("   ").toJson(listaDto)
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.json")
        if (!file.exists()) {
            file.createNewFile()
        }
        file.delete()
        file.writeText(json)
        println("JSON guardado en ${file.absolutePath}")
    }
    /***
     * Funcion que escribe el XML
     */
    fun whriteXML() {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}personas.xml")
        if (!file.exists()) {
            file.createNewFile()
        }
        val serializer = Persister()
        var listaDTO:MutableList<PersonaDTO> = mutableListOf()
        repositorioList.forEach { listaDTO+= it.toDTO() }
        val escritura:PersonasDTOList = PersonasDTOList(listaDTO)
        serializer.write(escritura, file)
        println("XML guardado en ${file.absolutePath}")

    }

    fun Persona.toDTO(): PersonaDTO {
        //primero tenemos que ver si es alumno o profesor
        if (this is Profesor) {
            return PersonaDTO(tipo = "Profesor", modulo = this.modulo, edad = null, nombre = this.nombre)
        } else {
            this as Alumno
            return PersonaDTO(tipo = "Alumno", modulo = null, edad = this.edad.toString(), nombre = this.nombre)
        }
    }

    fun PersonaDTO.toPersona():Persona{
        if (this.tipo=="Profesor") {
            return Profesor(modulo = this.modulo ?:" ",nombre = this.nombre)
        } else {
            return Alumno( edad = this.edad?.toInt() ?:0, nombre = this.nombre)
        }
    }

}