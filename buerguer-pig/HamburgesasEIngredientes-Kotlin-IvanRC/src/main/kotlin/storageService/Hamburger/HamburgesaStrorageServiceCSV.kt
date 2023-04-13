package storageService.Hamburger

import config.ConfigApp
import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.util.*

class HamburgesaStrorageServiceCSV: HamburgesaStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesas.csv")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todas las hamburgesas en el fichero CSV." }
        file.writeText("id,nombre,ingredientes,precio"+"\n")
        entities.forEach {hamburger ->
            file.appendText(
                "${hamburger.id},${hamburger.nombre},${fromListToCSV(hamburger.ingredientes)},${hamburger.precio}"+"\n"
            )
        }
    }

    private fun fromListToCSV(ingredientes: List<Ingrediente>): String {
        return ingredientes.joinToString(separator = "|", prefix = "[", postfix = "]") {
            "${it.id};${it.nombre};${it.precio}"
        }
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargán todas las hamburgesas del fichero CSV." }
        if(!file.exists()) return emptyList()
        return file.readLines().drop(1)
            .map { linea -> linea.split(",") }
            .map { campo -> campo.map { it.trim() }}
            .map { campo ->
                Hamburgesa(
                    id = UUID.fromString(campo[0]),
                    nombre = campo[1],
                    ingredientes = fromCSVtoList(campo[2]).toMutableList()
                )
            }
    }

    fun fromCSVtoList(campos: String): List<Ingrediente>{
        return campos.substring(1, campos.length-1)
            .split("|")
            .map { objeto -> objeto.split(";") }
            .map { campos ->
                Ingrediente(
                    id = campos[0].toInt(),
                    nombre = campos[1],
                    precio = campos[2].toDouble()
                )
            }
    }
}