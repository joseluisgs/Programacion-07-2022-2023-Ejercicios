package repositories

import models.Accidente
import mu.KotlinLogging
import storage.service.AccidenteXmlService
import storage.service.AccidentesService
import utils.toLocalDate
import utils.toLocalTime
import java.io.File


class AccidentesRepository(private val storageService: AccidentesService) {

    private val logger = KotlinLogging.logger {  }
    val filePath = "${AccidenteXmlService.programPath}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"

    val accidentes = leerAccidentes(File(filePath))


    fun load(){
        logger.debug { "Repository: load()" }
        storageService.loadAll()
    }

    fun save(){
        logger.debug { "Repository: save()" }
        storageService.saveAll(accidentes)
    }



    private fun leerAccidentes(file: File) =
        file.readLines().drop(1)
            .map { it.split(";") }
            .map { it -> it.map { it.trim() } }
            .map { campos ->
                Accidente(
                    numeroExpediente = campos[0],
                    fecha = toLocalDate(campos[1]),
                    hora = toLocalTime(campos[2]),
                    localizacion = campos[3],
                    numero = campos[4],
                    cod_distrito = campos[5],
                    distrito = campos[6],
                    tipoAccidente = campos[7],
                    estadoMeteorologico = campos[8],
                    tipoVehiculo = campos[9],
                    tipoPersona = campos[10],
                    rangoEdad = campos[11],
                    sexo = campos[12],
                    codLesividad = campos[13],
                    lesividad = campos[14],
                    coordenadaX = campos[15],
                    coordenadaY = campos[16],
                    positivoAlcohol = campos[17],
                    positividadDroga = campos[18]
                )
            }
}