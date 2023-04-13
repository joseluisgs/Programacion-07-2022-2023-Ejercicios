package services.storage.texto

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.UUID

object HamburguesaTextoService : HamburguesaStorageService {
    private val localFile = "${AppConfig.TEXT_PATH}${File.separator}hamburguesas.txt"

    override fun saveAll(items: List<Hamburguesa>) {
        File(localFile).writeText(items.joinToString("\n") { it.toString() })
    }

    override fun loadAll(): List<Hamburguesa> {
        return File(localFile).useLines {
            it.map { line ->
                val (id, nombre, ingredientes) = line.split(",")
                Hamburguesa(
                    id = UUID.fromString(id),
                    nombre = nombre,
                    ingredientes = ingredientes.split(";").map { ingrediente ->
                        val (id, nombre, precio) = ingrediente.split(":")
                        Ingrediente(
                            id = id.toInt(),
                            nombre = nombre,
                            precio = precio.toDouble()
                        )
                    }
                )
            }.toList()
        }
    }
}