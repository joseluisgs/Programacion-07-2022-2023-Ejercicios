package storage.medicion

import model.Medicion
import mu.KotlinLogging
import utils.toFormatoFechaValido
import utils.toLocalTime
import java.io.File
import java.io.IOException
import java.time.LocalDate

class MedicionStorageServiceCsv(val nombreFicheroDeResources: String): MedicionStorageService {

    val path = ClassLoader.getSystemResource(nombreFicheroDeResources).file ?: throw IOException("Fichero no encontrado.")
    private val logger = KotlinLogging.logger {}

    private lateinit var fecha: Triple<Int, Int, Int>;

    init{
        // La lista fecha, es un triple de enteros, cuyos valores son Año, Mes, Dia
        fecha = nombreFicheroDeResources.toFormatoFechaValido()
    }

    override fun saveAll(entities: List<Medicion>) {
        // Para este caso, como no vamos a escribir en el csv, no requerimos de esta función.
        TODO("Not yet implemented")
    }

    override fun loadAll(): List<Medicion> {
        logger.debug { "Se cargan todas las mediciones del archivo $nombreFicheroDeResources" }
        //El csv no tiene cabecera
        val file = File(path)
        if(!file.exists()) return emptyList()
        return file.readLines()
            .map { it.split(";").map { it.trim() } }
            .map { campos ->
                Medicion(
                    poblacion = campos[0],
                    provincia = campos[1],
                    temperaturaMax = campos[2].toDouble(),
                    // Es la función que tengo en mi Utils
                    horaTempMax = campos[3].toLocalTime(),
                    temperaturaMin = campos[4].toDouble(),
                    horaTempMin = campos[5].toLocalTime(),
                    precipitacion = campos[6].toDouble(),
                    dia = LocalDate.of(fecha.first, fecha.second, fecha.third)
                )
            }
    }
}