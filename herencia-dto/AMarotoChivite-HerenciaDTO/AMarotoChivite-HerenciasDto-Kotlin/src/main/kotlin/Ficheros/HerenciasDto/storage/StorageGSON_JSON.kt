package Ficheros.HerenciasDto.storage

import Ficheros.HerenciasDto.config.ConfigApp
import Ficheros.HerenciasDto.models.dto.PersonDTO
import Ficheros.HerenciasDto.models.dto.PersonListDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mu.KotlinLogging
import java.io.File

@ExperimentalStdlibApi
class StorageGSON_JSON : IStorageGeneral<PersonDTO> {

    private val logger = KotlinLogging.logger {}

    private val localFile = "${ConfigApp.APP_DATA}${File.separator}personGSON.json"
    val file = File(localFile)

    override fun saveInFile(repository: List<PersonDTO>) {
        logger.debug { "Storage: Escribiendo (sobreescribiendo) en JSON con GSON" }

        val gson = GsonBuilder().setPrettyPrinting().create()
        val listAlumno = repository.filter { it.type == "Alumno" }.map { it as PersonDTO.Alumno }
        val listProfesor = repository.filter { it.type == "Profesor" }.map { it as PersonDTO.Profesor }

        val jsonString = gson.toJson(PersonListDTO(listAlumno, listProfesor)) // Ya es DTO
        file.writeText(jsonString)
    }

    override fun readAllModelsInFile(): List<PersonDTO> {
        logger.debug { "Storage: Leyendo desde fichero JSON con GSON" }

        val gson = Gson()
        val jsonString = file.readText()
        val listAlumno = gson.fromJson(jsonString, PersonListDTO::class.java).myListAlumno
        val listProfesor = gson.fromJson(jsonString, PersonListDTO::class.java).myListProfesor

        val list = mutableListOf<PersonDTO>()
        listAlumno!!.forEach {
            list.add(it)
        }
        listProfesor!!.forEach {
            list.add(it)
        }

        return list
    }
}