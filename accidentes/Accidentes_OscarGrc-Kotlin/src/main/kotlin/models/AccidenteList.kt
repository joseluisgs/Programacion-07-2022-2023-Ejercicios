package models

import java.io.File

@OptIn(ExperimentalStdlibApi::class)
class AccidenteList {
    // Fichero CSV de Resources
    val programPath = AccidenteList::class.java.getResource("/accidentes.csv").file
        ?: throw IllegalAccessError("Error al cargar el CSV o el fichero no existe")
    var listaAccidentes: MutableList<Accidente> = read()
    val logger = mu.KotlinLogging.logger {}
    init {
        logger.debug { "Inicializando Listado" }
    }

        private fun read(): MutableList<Accidente> {
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
                          estado_meteorológico =   columnas[8],
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

}