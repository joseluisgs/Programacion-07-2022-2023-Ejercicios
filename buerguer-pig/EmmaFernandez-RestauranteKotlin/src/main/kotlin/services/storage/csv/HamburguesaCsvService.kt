package services.storage.csv

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.UUID

object HamburguesaCsvService : HamburguesaStorageService {
    private val localFile = "${AppConfig.CSV_PATH}${File.separator}hamburguesas.csv"

    override fun saveAll(items: List<Hamburguesa>) {
        File(localFile).writeText(
            "id,nombre,ingredientes,precio\n" +
                    items.joinToString("\n") { it.toString() })
    }

    override fun loadAll(): List<Hamburguesa> {
        return File(localFile).useLines {
            it.drop(1).map { line ->
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