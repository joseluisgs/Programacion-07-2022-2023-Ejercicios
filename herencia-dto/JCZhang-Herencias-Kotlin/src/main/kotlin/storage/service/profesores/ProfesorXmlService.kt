package storage.service.profesores

import mappers.profesorListToDto
import mappers.toProfesorList
import models.Profesor
import org.simpleframework.xml.core.Persister
import storage.service.ProfesorStorageService
import java.io.File
import java.nio.file.Files.createFile


object ProfesorXmlService: ProfesorStorageService {

    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}profesor.xml"
    private val serializer = Persister()

    override fun saveAll(items: List<Profesor>) {
        val file = File(filePath)
        if (!file.exists()){
            createFile(file.toPath())
        }
        serializer.write(items.profesorListToDto(), file)
    }

    override fun loadAll(): List<Profesor> {
        val file = File(filePath)
        if (!file.exists()){
            return emptyList()
        }

        val readedItems = serializer.read(dto.ProfesorListDto:: class.java, file).toProfesorList()
        readedItems.forEach { println(it) }
        return readedItems
    }
}