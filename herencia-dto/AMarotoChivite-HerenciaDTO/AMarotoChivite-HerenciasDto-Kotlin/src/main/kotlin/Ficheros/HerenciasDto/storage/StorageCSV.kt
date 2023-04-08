package Ficheros.HerenciasDto.storage

import Ficheros.HerenciasDto.config.ConfigApp
import Ficheros.HerenciasDto.models.dto.PersonDTO
import mu.KotlinLogging
import java.io.File


class StorageCSV : IStorageGeneral<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}personCSV.csv"

    override fun saveInFile(repository: List<PersonDTO>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en CSV" }
        val file = File(localFile)
        file.writeText("name,edad,modulo,type" + "\n")
        repository.forEach {
            if (it is PersonDTO.Alumno) {
                file.appendText("${it.name},${it.edad},-,alumno" + "\n")
            }
            if (it is PersonDTO.Profesor) {
                file.appendText("${it.name},${it.edad},${it.modulo},profesor" + "\n")
            }
        }
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Storage: Leyendo desde fichero CSV" }
        val file = File(localFile)

        // Filtro por si no existe el archivo
        if (!file.exists()) return emptyList()

        // Leer el fichero completo y eliminamos la primera fila
        val lines = file.readLines().drop(1)

        val alumnos = mutableListOf<PersonDTO.Alumno>()
        val profesores = mutableListOf<PersonDTO.Profesor>()

        lines.forEach { line ->
            val fields = line.split(',')
            if (fields[3] == "alumno") {
                val nombre = fields[0]
                val edad = fields[1].toInt()
                alumnos.add(PersonDTO.Alumno(nombre, edad.toString()))
            }
            if (fields[3] == "profesor") {
                val nombre = fields[0]
                val edad = fields[1].toInt()
                val modulo = fields[2]
                profesores.add(PersonDTO.Profesor(nombre, edad.toString(), modulo))
            }
        }
        return alumnos + profesores
    }
}