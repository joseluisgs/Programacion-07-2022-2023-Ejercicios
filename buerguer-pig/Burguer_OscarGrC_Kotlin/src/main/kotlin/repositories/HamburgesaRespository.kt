package repositories

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Hamburgesa
import models.HamburguesaList
import models.Ingrediente
import okio.buffer
import okio.source
import org.simpleframework.xml.core.Persister
import java.io.*

class HamburgesaRespository {
        var repositorioList: HamburguesaList = readCSV()
    val logger = mu.KotlinLogging.logger {}
    init {
        logger.info { "INICIANDO HamburgesaRespository " }
    }
    /***
     * Funcion que lee un de texto y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    fun readText(): HamburguesaList {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.txt")
        val listaHamburguesa =  mutableListOf<Hamburgesa>()
        file.bufferedReader().use { reader ->
            reader.readLine() // Se omite la primera línea
            reader.forEachLine { linea ->
                val columnas = linea.split(";")
                val ingredientes:MutableList<Ingrediente> = readIngredienteTXT(columnas[2]).toMutableList()
                listaHamburguesa += Hamburgesa(
                    nombre = columnas[0],
                    id = columnas[1],
                    ingredientes = ingredientes
                )
            }
        }
        println("TXT guardado en ${file.absolutePath}")
        return HamburguesaList(listaHamburguesas = listaHamburguesa)
    }
    /***
     * Funcion usada por readText para traducir los ingredientes
     * @return List<Ingredientes>
     */
    private fun readIngredienteTXT(ingrediente:String):List<Ingrediente>{
        val ingredienteListEntrada = ingrediente.split("$")
        val salida: MutableList<Ingrediente> = mutableListOf()
        for (i in 0 until ingredienteListEntrada.size-1){
            val ingredienteSalida = ingredienteListEntrada[i].split("|")
            salida+= Ingrediente(nombre = ingredienteSalida[0], id = ingredienteSalida[1],
                precio = ingredienteSalida[2], fechaCreacion = ingredienteSalida[3])
        }
        return salida
    }
    /***
     * Funcion que lee un archivo Binario y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    fun readBinario():HamburguesaList{
            val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.bin")
            // Leemos los bytes del archivo
            val bytes = file.readBytes()
            // Creamos un String a partir de los bytes
            val contenido = String(bytes, Charsets.UTF_8)
            // Separamos las líneas del contenido
            val lineas = contenido.split("\n")
            // Creamos una lista de hamburguesas vacía
            val hamburguesas:MutableList<Hamburgesa> = mutableListOf()
            // Recorremos las líneas
            for (i in 0 until lineas.size-1) {
                // Dividimos la línea en sus partes usando el separador ","
                val lineaSplit = lineas[i].split(",")
                // Obtenemos el nombre y el ID de la hamburguesa
                val nombre = lineaSplit[0]
                val id = lineaSplit[1]
                // Creamos una lista de ingredientes vacía
                val ingredientes: MutableList<Ingrediente> = mutableListOf()
                // Dividimos la línea en sus partes usando el separador "|"
                val ingrediente = lineaSplit[2].split("$")
                // Recorremos las partes
                for (i in 0 until  ingrediente.size-1) {
                    // Dividimos la parte actual en sus partes usando el separador ";"
                    val partesIngrediente = ingrediente[i].split(";")
                    // Obtenemos el nombre, el ID, el precio y la fecha de creación del ingrediente
                    val nombreIngrediente = partesIngrediente[0]
                    val idIngrediente = partesIngrediente[1]
                    val precioIngrediente = partesIngrediente[2]
                    val fechaCreacionIngrediente = partesIngrediente[3]
                    // Creamos un objeto Ingrediente a partir de la información obtenida y lo añadimos a la lista de ingredientes
                    val ingredientesalida = Ingrediente(nombreIngrediente, idIngrediente, precioIngrediente, fechaCreacionIngrediente)
                    ingredientes.add(ingredientesalida)
                }
                // Creamos un objeto Hamburguesa a partir de la información obtenida y lo añadimos a la lista de hamburguesas
                val hamburguesa = Hamburgesa(nombre, id, ingredientes)
                hamburguesas.add(hamburguesa)
            }
         return HamburguesaList(hamburguesas)
        }
    /***
     * Funcion que lee un archivo Serializado y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    fun readSerializado():HamburguesaList{
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.ser")
        val salida:HamburguesaList
        val archivo = ObjectInputStream(file.inputStream())
        archivo.use {
            salida= it.readObject() as HamburguesaList
        }
        return salida
    }
    /***
     * Funcion que lee un CSV y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    fun readCSV(): HamburguesaList{
        val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}Hamburgesa.csv"
        val fichero = File(path)
        val listaHamburguesa= fichero.useLines { lines ->
            lines
                // ignoro la primera porque es el encabezado
                .drop(1)
                // separo por comas
                .map { linea -> linea.split(",") }
                // convierto a Ingrediente
                .map { columnas ->
                    Hamburgesa(
                        columnas[0],            // nombre
                        columnas[1],    // id
                        traduceCsv(columnas[2]) // ingredientesList
                    )
                }.toMutableList()
        }
        return  HamburguesaList(listaHamburguesas = listaHamburguesa)
    }
    /***
     * Funcion usada por readCSV para los Ingredientes
     * @return MutableList<Ingrediente>
     */
    private fun traduceCsv(listaenCsv: String): MutableList<Ingrediente> {
        val salida:MutableList<Ingrediente> = mutableListOf()
        val mutando = listaenCsv.split("|")
        for(i in 0 until mutando.size-1){
            val mutado2 = mutando[i].split(";")
            val ingredienteNuevo = Ingrediente(nombre = mutado2[0],id=mutado2[1], precio =mutado2[2])
            salida.add(ingredienteNuevo)
        }
        return salida
    }
    /***
     * Funcion que lee un JSON y devuelve una HamburguesaList
     * @return HamburguesaList
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun readJSON(): HamburguesaList {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<HamburguesaList> = moshi.adapter<HamburguesaList>()
        val archivo= jsonAdapter.fromJson(
            File("${System.getProperty("user.dir")}${File.separator}data${File.separator}Hamburguesa.json")
                .inputStream().source().buffer())!!
        return  archivo
    }
    /***
     * Funcion que Lee el XML
     * @return HamburguesaList
     */
    fun readXML(): HamburguesaList {
        val serializer = Persister()
        val archivo = serializer.read(HamburguesaList::class.java, File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.xml"))
        return archivo
    }
    /***
     * Funcion que escribe en TXT
     */
    fun whriteText(){
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        //
        val salidaPrologo = "Nombre;id;ingredientes[nombre:id:precio:fechaCreacion] \n"
        var salida = ""
        repositorioList.listaHamburguesas.forEach{
            salida += it.nombre+";"+it.id+";"+it.ingredientes.map{
                    ingrediente->
                ingrediente.nombre+"|"+ingrediente.id+"|"+ingrediente.precio+"|"+ingrediente.fechaCreacion+" $"}+"\n" }
        file.bufferedWriter().use { it.write(salidaPrologo + salida) }
        println("TXT guardado en ${file.absolutePath}")
    }
    /***
     * Funcion que escribe en BIN
     */
    fun whriteBinario() {
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.bin")
        if (!file.exists()) {
            file.createNewFile()
        }
        // Creamos un StringBuilder para almacenar la información en formato CSV
        val sb = StringBuilder()
        // Recorremos la lista de hamburguesas del repositorio
        repositorioList.listaHamburguesas.forEach { hamburguesa ->
            // Añadimos el nombre y el ID de la hamburguesa separados por una coma
            sb.append("${hamburguesa.nombre},${hamburguesa.id},")

            // Recorremos los ingredientes de la hamburguesa
            hamburguesa.ingredientes.forEach { ingrediente ->
                // Añadimos el nombre, el ID, el precio y la fecha de creación del ingrediente separados por comas
                sb.append("${ingrediente.nombre};${ingrediente.id};${ingrediente.precio};${ingrediente.fechaCreacion}$")
            }
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
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.ser")
        if (!file.exists()) {
            file.createNewFile()
        }
        val salida = ObjectOutputStream(file.outputStream())
        salida.use {
            it.writeObject(repositorioList)
        }
    }
    /***
     * Funcion que escribe en CSV
     */
    fun whriteCSV() {
            val path = "${System.getProperty("user.dir")}${File.separator}data${File.separator}Hamburgesa.csv"
            val fichero = File(path)
            // si
        // no existe lo creamos
        if (!fichero.exists()) {
            fichero.createNewFile()
        }
            // Escribimos el encabezado, separados por comas
            fichero.writeText("Nombre,id,Ingredientes\n")
        repositorioList.listaHamburguesas.forEach {
                fichero.appendText("${it.nombre},${it.id},${codificar(it.ingredientes)}\n")
            }
        println("CSV guardado en  ${fichero.absolutePath}")
        }
    /***
     * Funcion que usa ReadCSV para codificar los Ingredientes
     */
    private fun codificar(lista:List<Ingrediente>):String{
        var salida = ""
        for (i in lista.indices){
            salida +="${lista[i].nombre};${lista[i].id};${lista[i].precio}|"
        }
        return salida
    }
    /***
     * Funcion que escribe el JSON
     */
    fun whriteJSON(){
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(HamburguesaList::class.java)
        val json = jsonAdapter.indent("   ").toJson(repositorioList)

        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.json")
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
        val file = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}hamburguesa.xml")
        if (!file.exists()) {
            file.createNewFile()
        }
        val serializer = Persister()
        serializer.write(repositorioList,file)
        println("XML guardado en ${file.absolutePath}")



    }
}