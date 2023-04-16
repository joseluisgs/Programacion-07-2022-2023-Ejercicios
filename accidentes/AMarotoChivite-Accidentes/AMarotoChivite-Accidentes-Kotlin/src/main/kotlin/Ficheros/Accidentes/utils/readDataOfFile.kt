package Ficheros.Accidentes.utils

import Ficheros.Accidentes.config.ConfigApp
import Ficheros.Accidentes.models.dto.AccidenteDTO
import java.io.File

fun readDataOfCSV(): List<AccidenteDTO> {
    val localFile = "${ConfigApp.APP_DATA}${File.separator}accidentes.csv"
    val file = File(localFile)

    // Leer el fichero completo y eliminamos la primera fila
    val lines = file.readLines().drop(1)

    val listAccidenteDTO = lines.map { line ->

        val fields = line.split(';')
        val numExpediente = fields[0]
        val fecha = fields[1]
        val hora = fields[2]
        val localizacion = fields[3]
        val numero = fields[4]

        // Filtrar las filas que no tienen un valor para el campo "cod_distrito".
        // El índice 5 corresponde al campo "cod_distrito".
        var codDistrito = ""
        if (fields[5] == "") {
            codDistrito = "nulo"
        } else {
            codDistrito = fields[5]
        }

        val distrito = fields[6]
        val tipoAccidente = fields[7]
        val estadoMeteorologico = fields[8]
        val tipoVehiculo = fields[9]
        val tipoPersona = fields[10]
        val rangoEdad = fields[11]
        val sexo = fields[12]
        val codLesividad = fields[13]
        val lesividad = fields[14]
        val coordenadaX = fields[15]
        val coordenadaY = fields[16]
        val positivoAlcohol = fields[17]
        val positivoDroga = fields[18]


        AccidenteDTO(
            numExpediente,
            fecha,
            hora,
            localizacion,
            numero,
            codDistrito,
            distrito,
            tipoAccidente,
            estadoMeteorologico,
            tipoVehiculo,
            tipoPersona,
            rangoEdad,
            sexo,
            codLesividad,
            lesividad,
            coordenadaX,
            coordenadaY,
            positivoAlcohol,
            positivoDroga
        )
    }
    return listAccidenteDTO
}