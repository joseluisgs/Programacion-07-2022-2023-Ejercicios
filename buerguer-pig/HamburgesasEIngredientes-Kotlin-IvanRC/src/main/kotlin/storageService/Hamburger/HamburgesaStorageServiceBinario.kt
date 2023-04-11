package storageService.Hamburger

import config.ConfigApp
import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.util.*

class HamburgesaStorageServiceBinario : HamburgesaStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesa.bin")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todos las hamburgesas en el fichero binario." }
        file.writeBytes("".toByteArray())
        entities.forEach { hamburgesa ->
            file.appendBytes((hamburgesa.id.toString()+"\n").toByteArray())
            file.appendBytes((hamburgesa.nombre+"\n").toByteArray())
            hamburgesa.ingredientes.forEach { ingrediente ->
                file.appendBytes((ingrediente.id.toString()+"\n").toByteArray())
                file.appendBytes((ingrediente.nombre+"\n").toByteArray())
                file.appendBytes((ingrediente.precio.toString()+"\n").toByteArray())
            }
        }
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargán todos las hamburgesas del fichero binario." }
        var hamburgesas = mutableListOf<Hamburgesa>()
        if(!file.exists()) return hamburgesas.toList()
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

            val ingredientes = mutableListOf<Ingrediente>()
            // Como se supone que se cuantos ingredientes hay???

            hamburgesas.add(
                Hamburgesa(
                    id = UUID.fromString(id.toString()),
                    nombre = nombre.toString(),
                    ingredientes = ingredientes //Como se supone que recojo los ingredientes si no se cuantos hay???
                )
            )
        }
        return hamburgesas.toList()
    }
}