package services.storage.hamburguesa

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import validator.canReed
import validator.canWrite
import java.io.File
import java.io.RandomAccessFile

private val logger = KotlinLogging.logger {}

object HamburguesaFileAleatorio: HamburguesaStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}hamburguesa.dat"
    private var lastRecordPosition: Long = 0L

    override fun saveAll(elements: List<Hamburguesa>): List<Hamburguesa> {
        logger.debug { "HamburguesaFileAleatorio ->\tsaveAll: ${elements.joinToString("\t")}" }

        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        val fileRdn = RandomAccessFile(localFile, "rw")

        elements.forEach { element ->
            fileRdn.writeInt(element.id)
            fileRdn.writeUTF(element.nombre)
            fileRdn.writeInt(element.ingredientes.size)
            element.ingredientes.forEach{
                fileRdn.writeInt(it.id)
                fileRdn.writeUTF(it.nombre)
                fileRdn.writeFloat(it.precio)
            }
        }

        lastRecordPosition = fileRdn.filePointer

        return elements
    }

    override fun loadAll(): List<Hamburguesa> {
        logger.debug { "HamburguesaFileAleatorio ->\tloadAll" }

        val file = File(localFile)
        if (!canWrite(file) || file.length() == 0L) return emptyList()

        val hamburguesas = mutableListOf<Hamburguesa>()
        val fileRdn = RandomAccessFile(localFile, "r")
        fileRdn.seek(0)

        while (fileRdn.filePointer < lastRecordPosition) {
            val id = fileRdn.readInt()
            val nombre = fileRdn.readUTF()
            val size = fileRdn.readInt()
            val ingredientes = mutableListOf<Ingrediente>()
            repeat(size){
                val idIngre = fileRdn.readInt()
                val nombreIngre = fileRdn.readUTF()
                val precio = fileRdn.readFloat()
                ingredientes.add(
                    Ingrediente(
                        idIngre,
                        nombreIngre,
                        precio
                    )
                )
            }
            hamburguesas.add(Hamburguesa(id, nombre, ingredientes))
        }

        fileRdn.close()
        return hamburguesas.toList()
    }
}