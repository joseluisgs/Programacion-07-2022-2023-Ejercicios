package repositories

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Accidente
import models.AccidenteList
import okio.buffer
import okio.source
import org.simpleframework.xml.core.Persister
import java.io.File

class AccidentesRespository() {
    // 1º leer Fichero CSV de Resources. (Si el Path cambiara lo meteriamos por constructor)
    val programPath = AccidenteList::class.java.getResource("/accidentes.csv").file
        ?: throw IllegalAccessError("Error al cargar el CSV o el fichero no existe")
    var archivo:AccidenteList = readCSV()

    /***
     * Funcion que Lee el CSV
     * @return AccidenteList
     */
    fun readCSV():AccidenteList{

        return AccidenteList(listaAccidentes = readOriginal())
    }
   private fun readOriginal(): List<Accidente> {
        println(programPath)
        val fichero = File(programPath)
        fichero.useLines { lines ->
            return lines
                // ignoro la primera porque es el encabezado
                .drop(1)
                // separo por comas
                .map { linea -> linea.split(";") }
                // convierto a Accidente
                .map { columnas ->
                    Accidente(
                        num_expediente =   columnas[0],
                        fecha =            columnas[1],
                        hora =             columnas[2],
                        localizacion =     columnas[3],
                        numero =           columnas[4],
                        cod_distrito =     columnas[5],
                        distrito =         columnas[6],
                        tipo_accidente =   columnas[7],
                        estado_meteorologico =   columnas[8],
                        tipo_vehiculo =   columnas[9],
                        tipo_persona =   columnas[10],
                        rango_edad =   columnas[11],
                        sexo =   columnas[12],
                        cod_lesividad =   columnas[13],
                        lesividad =   columnas[14],
                        coordenada_x_utm =   columnas[15],
                        coordenada_y_utm =   columnas[16],
                        positiva_alcohol =   columnas[17],
                        positiva_droga =   columnas[18])
                }.toMutableList()
        }

    }
    /***
     * Funcion que Lee el JSON
     * @return AccidenteList
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun readJSON(): AccidenteList {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<AccidenteList> = moshi.adapter<AccidenteList>()
        val archivo= jsonAdapter.fromJson(
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.json")
                .inputStream().source().buffer())!!
        return  archivo
    }
    /***
     * Funcion que Lee el XML
     * @return AccidenteList
     */
    fun readXML(): AccidenteList {
        val serializer = Persister()
        val archivo = serializer.read(AccidenteList::class.java, File("${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.xml"))
        return archivo
    }
    /***
     * Funcion que escribe el JSON
     */
    fun whriteJSON(){
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(AccidenteList::class.java)
        val json = jsonAdapter.indent("   ").toJson(archivo)

        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.json")
        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText(json)
        println("JSON guardado en ${file.absolutePath}")
    }

    /***
     * Funcion que escribe el XML
     */
    fun whriteXML(){
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.xml")
            if (!file.exists()) {
                file.createNewFile()
            }
        val serializer = Persister()
        serializer.write(archivo,file)
        println("XML guardado en ${file.absolutePath}")
    }
}