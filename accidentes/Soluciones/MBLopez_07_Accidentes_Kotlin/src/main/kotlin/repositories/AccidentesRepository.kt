package repositories

import com.opencsv.CSVReaderBuilder
import models.Accidente
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


object AccidentesRepository {
    private val formateadorFecha: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val formateadorHora: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    private val archivo = File("${System.getProperty("user.dir")}${File.separator}data${File.separator}accidentes.csv")
    private val csvReader = CSVReaderBuilder(archivo.reader()).withSkipLines(1).build()
    private val filas: MutableList<Array<String>> = csvReader.readAll()
    private val valores = mutableListOf<String>()
    val accidentes = mutableListOf<Accidente>()

    init {
        for (fila in filas) {
            if (fila.isNotEmpty()) {
                fila.contentToString().split(";").forEach{valores.add(it)}
            }
            //Hay un accidente con el número de expediente 2021S019592 que tiene un signo de ";" en la calle y lo separa mal, por eso he puesto este filtro
            if (valores.size > 19) {
                accidentes.add(Accidente(valores[0], LocalDate.parse(valores[1], formateadorFecha), LocalTime.parse(if(valores[2].length < 8) "0" + valores[2].dropLast(3) else valores[2].dropLast(3), formateadorHora), valores[3] + valores[4], valores[5], valores[6], valores[7], valores[8], valores[9], valores[10], valores[11], valores[12], valores[13], valores[14], valores[15], valores[16], valores[17], valores[18], valores[19]))
                valores.clear()
            }
            else
            {
                accidentes.add(Accidente(valores[0], LocalDate.parse(valores[1], formateadorFecha), LocalTime.parse(if(valores[2].length < 8) "0" + valores[2].dropLast(3) else valores[2].dropLast(3), formateadorHora), valores[3], valores[4], valores[5], valores[6], valores[7], valores[8], valores[9], valores[10], valores[11], valores[12], valores[13], valores[14], valores[15], valores[16], valores[17], valores[18]))
                valores.clear()
            }
        }
        csvReader.close()
    }
}
