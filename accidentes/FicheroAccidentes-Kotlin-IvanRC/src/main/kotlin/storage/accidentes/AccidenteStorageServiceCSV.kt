package storage.accidentes

import model.Accidente
import model.Distrito
import model.Lesividad
import mu.KotlinLogging
import utils.`toLocalDateDD-MM-YYYY`
import utils.toLocalTime
import java.io.File
import java.io.IOException

class AccidenteStorageServiceCSV : AccidenteStorageService {

    val file = ClassLoader.getSystemResource("accidentes.csv").file ?: throw IOException("Fichero no encontrado.")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Accidente>) {
        TODO("Esta funcion no la requiero, ya que en este caso no voy a escribir sobre el csv.")
    }

    override fun loadAll(): List<Accidente> {
        logger.debug { "Se cargan todos los accidentes del fichero CSV." }
        val fichero = File(file)
        return fichero.readLines()
            .drop(1)
            .map { it.split(";") }
            .map { it.map { it.trim() } }
            .map {campos ->
                transformarEnAccidente(campos)
            }
    }

    //Esta función existe debido a un unico caso en el que, en la localización había dos valores separados por: ';'
    private fun transformarEnAccidente(campos: List<String>): Accidente{
        val accidente: Accidente;
        if(campos.size == 19){
            accidente = Accidente(
                numeroExpediente = campos[0],
                fecha = `toLocalDateDD-MM-YYYY`(campos[1]),
                hora = toLocalTime(campos[2]),
                localizacion = listOf(campos[3]),
                numeroCalle = if(campos[4].matches(Regex("[0-9]+"))) campos[4].toInt() else null,
                distrito = Distrito(
                    id = if(campos[5].matches(Regex("[0-9]+"))) campos[5].toInt() else null,
                    nombre = campos[6]
                ),
                tipoAccidente = campos[7],
                estadoMeteorologico = campos[8],
                tipoDeVehiculo = campos[9],
                tipoDePersona = campos[10],
                rangoEdad = campos[11],
                sexo = campos[12],
                lesividad = Lesividad(
                    id = if(campos[13].matches(Regex("[0-9]+"))) campos[13].toInt() else null,
                    lesividad = if(campos[14] != "NULL" ) campos[14] else null
                ),
                longitud = if(campos[15].matches(Regex("[0-9]+.[0-9]+")))campos[15].replace(",", ".").replace("\"", "").toDouble() else null,
                altitud = if(campos[16].matches(Regex("[0-9]+.[0-9]+")))campos[16].replace(",", ".").replace("\"", "").toDouble() else null,
                esPositivaEnAlchol = campos[17] == "S",
                esPositivaEnDrogas = campos[18] == "1"
            )
        }else{
            accidente = Accidente(
                numeroExpediente = campos[0],
                fecha = `toLocalDateDD-MM-YYYY`(campos[1]),
                hora = toLocalTime(campos[2]),
                localizacion = listOf(campos[3], campos[4]),
                numeroCalle = if(campos[5].matches(Regex("[0-9]+"))) campos[5].toInt() else null,
                distrito = Distrito(
                    id = if(campos[6].matches(Regex("[0-9]+"))) campos[6].toInt() else null,
                    nombre = campos[7]
                ),
                tipoAccidente = campos[8],
                estadoMeteorologico = campos[9],
                tipoDeVehiculo = campos[10],
                tipoDePersona = campos[11],
                rangoEdad = campos[12],
                sexo = campos[13],
                lesividad = Lesividad(
                    id = if(campos[14].matches(Regex("[0-9]+"))) campos[14].toInt() else null,
                    lesividad = if(campos[15] != "NULL" ) campos[15] else null
                ),
                longitud = if(campos[16].matches(Regex("[0-9]+.[0-9]+")))campos[16].replace(",", ".").replace("\"", "").toDouble() else null,
                altitud = if(campos[17].matches(Regex("[0-9]+.[0-9]+")))campos[17].replace(",", ".").replace("\"", "").toDouble() else null,
                esPositivaEnAlchol = campos[18] == "S",
                esPositivaEnDrogas = campos[19] == "1"
            )
        }
        return accidente
    }
}