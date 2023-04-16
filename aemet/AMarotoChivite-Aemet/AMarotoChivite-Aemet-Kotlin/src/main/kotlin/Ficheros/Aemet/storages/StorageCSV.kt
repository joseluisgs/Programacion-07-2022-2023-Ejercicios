package Ficheros.Aemet.storages

import Ficheros.Aemet.config.ConfigApp
import Ficheros.Aemet.models.Aemet
import mu.KotlinLogging
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class StorageCSV : IStorageToImportExport<Aemet> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}AemetConjuntoCSV.csv"

    // Puede ser una opción List<List<Aemet>> o Map <Aemet> para poder diferenciar los días, en vez de añadir el atributo date
    override fun saveInFileWithFilter(repository: List<Aemet>) {
        logger.debug { "Storage: Escribiendo en CSV" }
        val file = File(localFile)
        file.writeText("nombre_poblacion,nombre_provincia,temp_max,hora_temp_max,temp_min,hora_temp_min,precipitacion,fecha" + "\n")
        repository.forEach {
            file.appendText("${it.nombrePoblacion},${it.nombreProvincia},${it.temperaturaMaxima},${it.horaTemperaturaMaxima},${it.temperaturaMinima},${it.horaTemperaturaMinima},${it.precipitacion},${it.date}" + "\n")
        }
    }

    override fun readAllModelsInFile(): List<Aemet> {
        logger.debug { "Storage: Leyendo desde ficheros CSV" }

        // En vez de seleccionar manualmente los ficheros mediante las funciones de File() puedo recorrer toda la carpeta
        /* private val localFile_1 = "${ConfigApp.APP_DATA}${File.separator}Aemet20171029.csv"
         private val localFile_2 = "${ConfigApp.APP_DATA}${File.separator}Aemet20171030.csv"
         private val localFile_3 = "${ConfigApp.APP_DATA}${File.separator}Aemet20171031.csv"*/

        val folderName = "lecture"
        val folderPath = "${ConfigApp.APP_DATA}${File.separator}$folderName"
        val directory = File(folderPath)

        val files = directory.listFiles { file ->
            // Filtrar los archivos que tengan el patrón AemetYYYYMMDD.csv
            file.name.matches(Regex("Aemet\\d{8}\\.csv"))
        }

        val listAemet = mutableListOf<Aemet>()

        files.forEach { file ->
            val lines = file.readLines()

            // Extraemos la fecha del nombre del archivo
            val fileNameDate = file.name.substring(5, 13)
            val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val date = LocalDate.parse(fileNameDate, dateFormatter)

            lines.forEach { line ->
                val fields = line.split(';')
                val nombrePoblacion = fields[0]
                val nombreProvincia = fields[1]
                val tempMax = fields[2].toDouble()
                // No lo puede parsear! Por ello de manera opcional acepta "h:mm" como "HH:mm"
                val flexibleFormatter = DateTimeFormatter.ofPattern("[H:]mm[:ss][ a]")
                val horaTempMax = LocalTime.parse(fields[3], flexibleFormatter)
                val tempMin = fields[4].toDouble()
                val horaTempMin = LocalTime.parse(fields[5], flexibleFormatter)
                val precipitacion = fields[6].toDouble()
                listAemet.add(
                    Aemet(
                        nombrePoblacion,
                        nombreProvincia,
                        tempMax,
                        horaTempMax,
                        tempMin,
                        horaTempMin,
                        precipitacion,
                        date
                    )
                )
            }
        }
        return listAemet
    }

}

