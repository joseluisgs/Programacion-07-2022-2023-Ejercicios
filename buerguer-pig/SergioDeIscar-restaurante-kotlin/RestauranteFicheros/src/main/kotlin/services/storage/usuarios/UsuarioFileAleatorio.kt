package services.storage.usuarios

import config.AppConfig
import models.Usuario
import models.enums.UserRol
import mu.KotlinLogging
import validator.canWrite
import java.io.File
import java.io.RandomAccessFile
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

object UsuarioFileAleatorio: UsuarioStorageService{

    private val localFile = "${AppConfig.APP_DATA}${File.separator}usuario.dat"
    private var lastRecordPosition = 0L

    override fun getUsuariosInDateRange(fechaInicio: LocalDateTime, fechaFin: LocalDateTime): List<Usuario> {
        logger.debug { "UsuarioFileAleatorio ->\tgetUsuariosInDateRange: $fechaInicio-$fechaFin" }
        return loadAll().filter { it.createAt.isAfter(fechaInicio) && it.createAt.isBefore(fechaFin) }
    }

    override fun saveAll(elements: List<Usuario>): List<Usuario> {
        logger.debug { "UsuarioFileAleatorio ->\tsaveAll: ${elements.joinToString("\t")}" }

        val file = File(localFile)
        if (!canWrite(file)) return emptyList()

        val fileRdn = RandomAccessFile(localFile, "rw")

        fileRdn.seek(0)

        elements.forEach{
            fileRdn.writeInt(it.id)
            fileRdn.writeUTF(it.nombre)
            fileRdn.writeUTF(it.apellido)
            fileRdn.writeUTF(it.password)
            fileRdn.writeUTF(it.rol.toString())
            fileRdn.writeUTF(it.createAt.toString())
        }

        lastRecordPosition = fileRdn.filePointer

        return elements
    }

    override fun loadAll(): List<Usuario> {
        logger.debug { "UsuarioFileAleatorio ->\tloadAll" }

        val file = File(localFile)
        if (!canWrite(file) || file.length() == 0L) return emptyList()

        val usuarios = mutableListOf<Usuario>()
        val fileRdn = RandomAccessFile(localFile, "r")
        fileRdn.seek(0)

        while (fileRdn.filePointer < lastRecordPosition){
            usuarios.add(
                Usuario(
                    id = fileRdn.readInt(),
                    nombre = fileRdn.readUTF(),
                    apellido = fileRdn.readUTF(),
                    password = fileRdn.readUTF(),
                    rol = UserRol.valueOf(fileRdn.readUTF()),
                    createAt = LocalDateTime.parse(fileRdn.readUTF())
                )
            )
        }

        return usuarios
    }
}