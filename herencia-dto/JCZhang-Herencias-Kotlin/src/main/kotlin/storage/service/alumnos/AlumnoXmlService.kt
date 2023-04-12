package storage.service.alumnos

import mappers.alumnoListToDto
import mappers.profesorListToDto
import mappers.toAlumnoList
import models.Alumno
import org.simpleframework.xml.core.Persister
import storage.service.AlumnoStorageService
import storage.service.profesores.ProfesorXmlService
import java.io.File
import java.nio.file.Files.createFile

object AlumnoXmlService: AlumnoStorageService {
    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}alumno.xml"

    private val serializer = Persister()

    override fun saveAll(items: List<Alumno>) {
        val file = File(filePath)
        if (!file.exists()){
            createFile(file.toPath())
        }
        serializer.write(items.alumnoListToDto(), file)

    }

    override fun loadAll(): List<Alumno> {
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }
        val readedAlumnos = serializer.read(dto.AlumnoListDto::class.java,file).toAlumnoList()
        readedAlumnos.forEach { println(it) }
        return readedAlumnos
    }
}