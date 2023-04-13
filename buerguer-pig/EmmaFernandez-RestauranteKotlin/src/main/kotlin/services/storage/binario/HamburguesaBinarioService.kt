package services.storage.binario

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.UUID

object HamburguesaBinarioService : HamburguesaStorageService {
    private val localFile = "${AppConfig.BINARY_PATH}${File.separator}hamburguesas.bin"

    override fun saveAll(items: List<Hamburguesa>) {
        val file = File(localFile)
        file.writeText("")
        file.outputStream().buffered().use {
            items.forEach { hamburguesa ->
                it.write(
                    hamburguesa.id.toString().toByteArray()
                            + "\n".toByteArray()
                            + hamburguesa.nombre.toByteArray()
                            + "\n".toByteArray()
                            + hamburguesa.ingredientes.joinToString(";") { it.toString() }.toByteArray()
                            + "\n".toByteArray()
                )
            }
        }
    }

    override fun loadAll(): List<Hamburguesa> {
        val listaHamburguesas = mutableListOf<Hamburguesa>()
        val file = File(localFile)
        file.inputStream().buffered().use {
            while (it.available() > 0) {
                var char = it.read().toChar()
                val id = StringBuilder()
                while (char != '\n') {
                    id.append(char)
                    char = it.read().toChar()
                }
                val nombre = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    nombre.append(char)
                    char = it.read().toChar()
                }
                val ingredientes = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    ingredientes.append(char)
                    char = it.read().toChar()
                }
                listaHamburguesas.add(
                    Hamburguesa(
                        id = UUID.fromString(id.toString()),
                        nombre = nombre.toString(),
                        ingredientes = ingredientes.toString().split(";")
                            .map { ingrediente -> ingrediente.split(",") }
                            .map { (id, nombre, precio) ->
                            Ingrediente(
                                id = id.toInt(),
                                nombre = nombre,
                                precio = precio.toDouble()
                            )
                        }
                    )
                )
            }
        }
        return listaHamburguesas.toList()
    }
}