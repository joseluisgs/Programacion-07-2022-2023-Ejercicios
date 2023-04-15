package storageService.Ingrediente

import config.ConfigApp
import model.Ingrediente
import mu.KotlinLogging
import java.io.File

class IngredienteStorageServiceCSV: IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+File.separator+"ingredientes.csv")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el fichero CSV." }
        file.writeText("id,nombre,precio"+"\n")
        entities.forEach { ingrediente ->
            file.appendText("${ingrediente.id},${ingrediente.nombre},${ingrediente.precio}"+"\n")
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargán todos los ingredientes del fichero CSV." }
        if(!file.exists()) return emptyList()
        return file.readLines().drop(1).map { linea -> linea.split(",") }
            .map { campo -> campo.map { it.trim() } }
            .map { campo ->
                Ingrediente(
                    id = campo[0].toInt(),
                    nombre = campo[1],
                    precio = campo[2].toDouble()
                )
            }
    }
}