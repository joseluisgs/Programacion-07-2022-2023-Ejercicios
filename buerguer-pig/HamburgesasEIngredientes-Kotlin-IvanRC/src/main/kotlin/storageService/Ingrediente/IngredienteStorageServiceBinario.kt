package storageService.Ingrediente

import config.ConfigApp
import model.Ingrediente
import mu.KotlinLogging
import java.io.File

class IngredienteStorageServiceBinario : IngredienteStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"ingredientes.bin")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Ingrediente>) {
        logger.debug { "Se guardan todos los ingredientes en el fichero binario." }
        file.writeBytes("".toByteArray())
        entities.forEach { ingrediente ->
            file.appendBytes((ingrediente.id.toString()+"\n").toByteArray())
            file.appendBytes((ingrediente.nombre+"\n").toByteArray())
            file.appendBytes((ingrediente.precio.toString()+"\n").toByteArray())
        }
    }

    override fun loadAll(): List<Ingrediente> {
        logger.debug { "Se cargán todos los ingredientes del fichero binario." }
        var ingredientes = mutableListOf<Ingrediente>()
        if(!file.exists()) return ingredientes.toList()
        file.inputStream().use {
            var char = it.read().toChar()

            val id = StringBuilder()
            while(char != '\n'){
                id.append(char)
                char = it.read().toChar()
            }

            char = it.read().toChar()

            val nombre = StringBuilder()
            while(char != '\n'){
                nombre.append(char)
                char = it.read().toChar()
            }

            char = it.read().toChar()

            val precio = StringBuilder()
            while(char != '\n'){
                precio.append(char)
                char = it.read().toChar()
            }

            ingredientes.add(
                Ingrediente(
                    id = id.toString().toInt(),
                    nombre = nombre.toString(),
                    precio = precio.toString().toDouble()
                )
            )
        }
        return ingredientes.toList()
    }

}