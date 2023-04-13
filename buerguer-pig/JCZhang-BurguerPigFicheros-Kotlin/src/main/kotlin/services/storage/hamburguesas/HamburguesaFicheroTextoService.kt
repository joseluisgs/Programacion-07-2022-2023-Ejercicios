package services.storage.hamburguesas

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.util.*

object HamburguesaFicheroTextoService : HamburguesaStorageService {
    val logger = KotlinLogging.logger {}

    private val programPath = System.getProperty("user.dir")
    private val filePath = "$programPath${File.separator}data${File.separator}hamburguesas.txt"


    override fun saveAll(items: List<Hamburguesa>) {
        logger.debug("Service: Guardando hamburguesas en fichero de texto")
        val file = File(filePath)
        file.writeText("")
        items.forEach {
            file.appendText(it.id.toString() + "\n")
            file.appendText(it.nombre + "\n")
            file.appendText(it.ingredientes.map { it.toString() }.joinToString(", ") + "\n")
            file.appendText(it.precio.toString() + "\n")
        }
    }


    override fun loadAll(): List<Hamburguesa> {
        logger.debug("Service: Cargando hamburguesas del fichero de texto")
        val file = File(filePath)
        val hamburguesas = mutableListOf<Hamburguesa>()

        file.bufferedReader().use {
            while (it.ready()) {
                val id = it.readLine().toString().trim()
                val nombre = it.readLine().toString().trim()
                val ingredientes = it.readLine().split(", ").map {
                    Ingrediente.parseFromString(it)
                }
                val precio = it.readLine().toDouble()

                // No supe como leer los ingredientes del fichero
                hamburguesas.add(Hamburguesa(
                    id = castReadIdToUUID(id), nombre = nombre,
                    ingredientes = ingredientes, precio = precio))
            }
        }
        hamburguesas.forEach { println(it)}
        return hamburguesas
    }

    private fun castReadIdToUUID(id: String): UUID {
        return UUID.fromString(id)
    }
}