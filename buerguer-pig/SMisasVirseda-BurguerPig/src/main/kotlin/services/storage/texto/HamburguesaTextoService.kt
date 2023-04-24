package services.storage.texto

import config.AppConfig
import models.Hamburguesa
import models.Ingrediente
import services.storage.HamburguesaStorageService
import java.io.File
import java.util.*

object HamburguesaTextoService : HamburguesaStorageService {

    val path = AppConfig.textoPath + File.separator + "Hamburguesas.txt"

    override fun cargar(): List<Hamburguesa> {
        return File(path).useLines {
            it.map { line ->
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
            items.joinToString("\n") { it.toString() }
        )
    }
}
