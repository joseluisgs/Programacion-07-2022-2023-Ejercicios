package service.storage.persona.csv

import config.ConfigApp
import models.Alumno
import models.Persona
import models.Profesor
import service.storage.persona.PersonaStorageService
import java.io.File

object PersonaFicheroCsvService: PersonaStorageService {

    val localFile = "${ConfigApp.PersonaCsv}${File.separator}" + "personas.csv"

    override fun saveAll(items: List<Persona>) {
        val file = File(localFile)
        file.writeText("nombre, edad, modulo, tipo\n")
        items.forEach {
            file.appendText(it.toStringCsv() + "\n")
        }
    }

    override fun loadAll(): List<Persona> {
        val file = File(localFile)
        if (!file.exists() && !file.canRead()) return emptyList()

        return file.readLines()
            .drop(1)
            .map { linea -> linea.split(",") } // Separa los atributos los cuales tengan ","
            .map { campos -> campos.map { it.trim() } } // trim() elimina espacios en blanco. Entre los atributos no debemos escribir con espacios
            .map { campos ->
                when(campos[3]){
                    "Alumno" -> Alumno(
                        name = campos[0],
                        edad = campos[1].toInt(),
                    )
                    "Profesor" -> Profesor(
                        name = campos[0],
                        modulo = campos[2].toString()
                    )

                    else -> {Persona(name = campos[0])}
                }
            }
    }

}