package storageService.Ingrediente

import config.ConfigApp
import model.Ingrediente
import mu.KotlinLogging
import java.io.*

class IngredienteStorageServiceTexto : IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"ingredientes.txt")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el fichero texto." }
        file.writeText("")
        entities.forEach { ingrediente ->
            file.appendText(ingrediente.id.toString()+"\n")
            file.appendText(ingrediente.nombre+"\n")
            file.appendText(ingrediente.precio.toString()+"\n")
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargán todos los ingredientes del fichero texto." }
        var ingredientes = mutableListOf<Ingrediente>()
        if(!file.exists()) return ingredientes.toList()
        file.bufferedReader().use {
            while(it.ready()){
                ingredientes.add(
                    Ingrediente(
                        id = it.readLine().toInt(),
                        nombre = it.readLine(),
                        precio = it.readLine().toDouble()
                    )
                )
            }
        }
        return ingredientes.toList()
    }

}