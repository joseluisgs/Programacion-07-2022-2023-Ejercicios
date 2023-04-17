package storage.service

import models.Accidente
import mu.KotlinLogging
import utils.toLocalDate
import utils.toLocalTime
import java.io.File

object AccidenteCsvService: AccidentesService {

    private val logger = KotlinLogging.logger {  }
    val programPath = System.getProperty("user.dir")
    val filePath = "$programPath${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"


    override fun loadAll(): List<Accidente> {
        val file = File(filePath)
        logger.debug { "Cargando accidentes de fichero CSV" }

        val accidenteLeido = file.readLines().drop(1)
            .map { it.split(";") }
            .map { it.map { it.trim() } }
            .map { campos -> Accidente(
                numeroExpediente = campos[0],
                fecha = toLocalDate(campos[1]),
                hora = toLocalTime(campos[2]),
                localizacion = campos[3],
                numero = campos[4],
                cod_distrito = campos[5],
                distrito = campos[5],
                tipoAccidente = campos[6],
                estadoMeteorologico = campos[7],
                tipoVehiculo = campos[8],
                tipoPersona = campos[9],
                rangoEdad = campos[10],
                sexo = campos[11],
                codLesividad = campos[12],
                lesividad = campos[13],
                coordenadaX = campos[14],
                coordenadaY = campos[15],
                positivoAlcohol = campos[16],
                positividadDroga = campos[17]
            )
            }
        accidenteLeido.forEach { println(it) }
        return accidenteLeido
    }

    override fun saveAll(items: List<Accidente>) {
        TODO("Not yet implemented")
    }


}