package storageService.Hamburger

import config.ConfigApp
import model.Hamburgesa
import model.Ingrediente
import mu.KotlinLogging
import java.io.File
import java.util.*

class HamburgesaStorageServiceTexto : HamburgesaStorageService {

    private val config = ConfigApp
    private val file = File(config.APP_DATA+ File.separator+"hamburgesa.txt")
    private val logger = KotlinLogging.logger {}

    override fun saveAll(entities: List<Hamburgesa>) {
        logger.debug { "Se guardan todos las hamburgesas en el fichero texto." }
        file.writeText("")
        entities.forEach { hamburgesa ->
            file.appendText(hamburgesa.id.toString()+"\n")
            file.appendText(hamburgesa.nombre+"\n")
            hamburgesa.ingredientes.forEach{ingrediente ->
                file.appendText(ingrediente.id.toString()+"\n")
                file.appendText(ingrediente.nombre+"\n")
                file.appendText(ingrediente.precio.toString()+"\n")
            }
        }
    }

    override fun loadAll(): List<Hamburgesa> {
        logger.debug { "Se cargán todos las hamburgesas del fichero texto." }
        var hamburgesas = mutableListOf<Hamburgesa>()
        if(!file.exists()) return hamburgesas.toList()
        file.bufferedReader().use {
            while(it.ready()){
                hamburgesas.add(
                    Hamburgesa(
                        id = UUID.fromString(it.readLine()),
                        nombre = it.readLine(),
                        ingredientes = emptyList<Ingrediente>().toMutableList() //Como se supone que recojo los ingredientes si no se cuantos hay???
                    )
                )
            }
        }
        return hamburgesas.toList()
    }

}