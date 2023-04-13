package repositories

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Ingrediente
import models.IngredienteList
import okio.buffer
import okio.source
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class IngredienteRespository {
    var listaRepositorio= readCSV()
    val logger = mu.KotlinLogging.logger {}
    init {
        logger.info { "INICIANDO INGREDIENTES Respository " }
    }
    /***
     * Funcion que lee un de texto y devuelve una IngredienteList
     * @return IngredienteList
     */
    fun readText(): IngredienteList {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.txt")
        val listaIngredientes =  mutableListOf<Ingrediente>()
        file.bufferedReader().use { reader ->
            reader.readLine() // Se omite la primera línea
                .drop(1)
            reader.forEachLine { linea ->
                val columnas = linea.split(",")
                listaIngredientes+= Ingrediente(
                    nombre = columnas[0],
                    id = columnas[1],
                    precio = columnas[2],
                    fechaCreacion = columnas[3]
                )
            }
        }
        println("TXT guardado en ${file.absolutePath}")
        return IngredienteList(listaIngredientes = listaIngredientes)
    }
    /***
     * Funcion que lee un archivo Binario y devuelve una IngredienteList
     * @return IngredienteList
     */
    fun readBinario(): IngredienteList {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.bin")
        // Leemos los bytes del archivo
        val bytes = file.readBytes()
        // Creamos un String a partir de los bytes
        val contenido = String(bytes, Charsets.UTF_8)
        // Separamos las líneas del contenido
        val lineas = contenido.split("\n")
        // Creamos una lista de Ingredientes vacía
        val ingredientes:MutableList<Ingrediente> = mutableListOf()
        // Recorremos las líneas
        for (i in 0 until lineas.size-1) {
            // Dividimos la línea en sus partes usando el separador ","
            val lineaSplit = lineas[i].split(",")
            val nombre = lineaSplit[0]
            val id = lineaSplit[1]
            val precio = lineaSplit[2]
            val fecha = lineaSplit[3]
            // Creamos un objeto Ingrediente a partir de la información obtenida y lo añadimos a la lista
            val ingrediente = Ingrediente(nombre, id, precio,fecha)
            ingredientes.add(ingrediente)
        }
        return IngredienteList(ingredientes)
    }
    /***
     * Funcion que lee un archivo Serializado y devuelve una IngredienteList
     * @return IngredienteList
     */
    fun readSerializado(): IngredienteList {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.ser")
        val salida: IngredienteList
        val archivo = ObjectInputStream(file.inputStream())
        archivo.use {
            salida= it.readObject() as IngredienteList
        }
        return salida
    }
    /***
     * Funcion que lee un CSV y devuelve una IngredienteList
     * @return IngredienteList
     */
    private fun readCSV(): IngredienteList {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.csv"
        val fichero = File(path)
        val salidalist:MutableList<Ingrediente> =
        fichero.useLines { lines ->
         lines
            // ignoro la primera porque es el encabezado
            .drop(1)
            // separo por comas
            .map { linea -> linea.split(",") }
            // convierto a Ingrediente
            .map { columnas ->
                Ingrediente(
                    columnas[0],            // nombre
                    columnas[1],    // id
                    columnas[2]    // precio
                )
            }.toMutableList()
    }
        return IngredienteList(salidalist)
    }
    /***
     * Funcion que lee un JSON y devuelve una IngredienteList
     * @return IngredienteList
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun readJSON(): IngredienteList {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<IngredienteList> = moshi.adapter<IngredienteList>()
        val archivo= jsonAdapter.fromJson(
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.json")
                .inputStream().source().buffer())!!
        return  archivo
    }
    /***
     * Funcion que Lee un XML y devuelve una IngredienteList
     * @return IngredienteList
     */
    fun readXML(): IngredienteList {
        val serializer = Persister()
        val archivo = serializer.read(IngredienteList::class.java,
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.xml"))
        return archivo
    }
    /***
     * Funcion que escribe en TXT
     */
    fun whriteText(){
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        //
        val salidaPrologo = "Nombre,id,precio,fechaCreacion \n"
        var salida = ""
        listaRepositorio.listaIngredientes.forEach{
            salida += it.nombre+","+it.id+","+it.precio+","+it.fechaCreacion+"\n"}
        file.bufferedWriter().use { it.write(salidaPrologo + salida) }
        println("TXT guardado en ${file.absolutePath}")
    }
    /***
     * Funcion que escribe en BIN
     */
    fun whriteBinario() {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.bin")
        if (!file.exists()) {
            file.createNewFile()
        }
        // Creamos un StringBuilder para almacenar la información en formato CSV
        val sb = StringBuilder()
        // Recorremos la lista de hamburguesas del repositorio
         listaRepositorio.listaIngredientes.forEach { ingrediente ->
            // Añadimos el nombre y el ID de la hamburguesa separados por una coma
            sb.append("${ingrediente.nombre},${ingrediente.id},${ingrediente.precio},${ingrediente.fechaCreacion}")
            sb.append("\n")
        }
        // Convertimos el StringBuilder a String y lo almacenamos en la variable "salida"
        val salida = sb.toString()
        //println(salida)
        // Convertimos la cadena "salida" a bytes usando el formato UTF-8
        val salidaBytes = salida.toByteArray(Charsets.UTF_8)
        // Escribimos los bytes en el archivo
        file.writeBytes(salidaBytes)
        println("Archivo binario guardado en ${file.absolutePath}")
    }
    /***
     * Funcion que escribe en SER
     */
    fun whiteSerializado(){
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.ser")
        if (!file.exists()) {
            file.createNewFile()
        }
        val salida = ObjectOutputStream(file.outputStream())
        salida.use {
            it.writeObject(listaRepositorio)
        }
    }
    /***
     * Funcion que escribe en CSV
     */
    fun whriteCSV() {
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.csv"
        val fichero = File(path)
        // si
        // no existe lo creamos
        if (!fichero.exists()) {
            fichero.createNewFile()
        }
        // Escribimos el encabezado, separados por comas
        fichero.writeText("Nombre,id,precio,fechaCreacion \n")
        listaRepositorio.listaIngredientes.forEach {
            fichero.appendText("${it.nombre},${it.id},${it.precio},${it.fechaCreacion}\n")
        }
        println("CSV guardado en  ${fichero.absolutePath}")
    }
    /***
     * Funcion que escribe el JSON
     */
    fun whriteJSON(){
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(IngredienteList::class.java)
        val json = jsonAdapter.indent("   ").toJson(listaRepositorio)

        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.json")
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
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}ingredientes.xml")
        if (!file.exists()) {
            file.createNewFile()
        }
        val serializer = Persister()
        serializer.write(listaRepositorio,file)
        println("XML guardado en ${file.absolutePath}")



    }
}
