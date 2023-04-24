package services.storage.binario

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.*

object HamburguesaBinarioService : HamburguesaStorageService {
    private val path = AppConfig.binPath + File.separator + "Hamburguesas.bin"
    override fun cargar(): List<Hamburguesa> {
        val lista = mutableListOf<Hamburguesa>()
        File(path).inputStream().buffered().use {
            while (it.available() > 0) {
                var char = it.read().toChar()
                val id = StringBuilder()
                while (char != '\n') {
                    id.append(char)
                    char = it.read().toChar()
                }
                char = it.read().toChar()
                val nombre = StringBuilder()
                while (char != '\n') {
                    nombre.append(char)
                    char = it.read().toChar()
                }
                char = it.read().toChar()
                val ingredientes = StringBuilder()
                while (char != '\n') {
                    ingredientes.append(char)
                    char = it.read().toChar()
                }
                lista.add(
                    Hamburguesa(
                        id = UUID.fromString(id.toString()),
                        nombre = nombre.toString(),
                        ingredientes = ingredientes.toString().split(":")
                            .map { ingrediente ->
                                val ingredi = ingrediente.split(";")
                                Ingrediente(ingredi[0].toInt(), ingredi[1], ingredi[2].toDouble())
                            }
                    )
                )
            }
        }
        return lista
    }

    override fun guardar(items: List<Hamburguesa>) {
        File(path).writeText("")
        File(path).outputStream().buffered().use {
            items.forEach { item ->
                it.write(
                    item.id.toString().toByteArray() + "\n".toByteArray()
                            + item.nombre.toByteArray() + "\n".toByteArray()
                            + item.ingredientes.joinToString(":") { it.toString() }
                        .toByteArray() + "\n".toByteArray()
                )
            }
        }
    }
}
