package services.storage.binario

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteBinarioService : IngredienteStorageService {
    private val localFile = "${AppConfig.BINARY_PATH}${File.separator}ingredientes.bin"

    override fun saveAll(items: List<Ingrediente>) {
        val file = File(localFile)
        file.writeText("")
        file.outputStream().buffered().use {
            items.forEach { ingrediente ->
                it.write(
                    ingrediente.id.toString().toByteArray() + "\n".toByteArray()
                            + ingrediente.nombre.toByteArray() + "\n".toByteArray()
                            + ingrediente.precio.toString().toByteArray()
                            + "\n".toByteArray()
                )
            }
        }
    }

    override fun loadAll(): List<Ingrediente> {
        val listaIngredientes = mutableListOf<Ingrediente>()
        val file = File(localFile)
        file.inputStream().buffered().use {
            while (it.available() > 0) {
                var char = it.read().toChar()
                val id = StringBuilder()
                while (char != '\n') {
                    id.append(char)
                    char = it.read().toChar()
                }
                val nombre = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    nombre.append(char)
                    char = it.read().toChar()
                }
                val precio = StringBuilder()
                char = it.read().toChar()
                while (char != '\n') {
                    precio.append(char)
                    char = it.read().toChar()
                }
                listaIngredientes.add(
                    Ingrediente(
                        id = id.toString().toInt(),
                        nombre = nombre.toString(),
                        precio = precio.toString().toDouble()
                    )
                )
            }
        }
        return listaIngredientes.toList()
    }
}