package services.storage.csv

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.*

object HamburguesaCsvService : HamburguesaStorageService {
    val path = AppConfig.csvPath + File.separator + "Hamburguesas.csv"

    override fun cargar(): List<Hamburguesa> {
        return File(path).useLines {
            it.drop(1).map { line ->
                val (id, nombre, ingredientes) = line.split(",")
                Hamburguesa(
                    id = UUID.fromString(id),
                    nombre = nombre,
                    ingredientes = ingredientes.split(":").map {
                        val (idIng, nombreIngr, precioIngr) = it.split(";")
                        Ingrediente(
                            id = idIng.toInt(),
                            nombre = nombreIngr,
                            precio = precioIngr.toDouble()
                        )
                    }
                )
            }.toList()
        }
    }

    override fun guardar(items: List<Hamburguesa>) {
        File(path).writeText(
            "id,nombre,ingredientes,precio\n" +
                    items.joinToString("\n") { it.toString() }
        )
    }
}
