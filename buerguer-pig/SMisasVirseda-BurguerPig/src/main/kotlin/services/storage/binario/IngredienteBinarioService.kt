package services.storage.binario

import config.AppConfig
import models.Ingrediente
import services.storage.IngredienteStorageService
import java.io.File

object IngredienteBinarioService : IngredienteStorageService {
    private val path = AppConfig.binPath + File.separator + "Ingredientes.bin"

    override fun cargar(): List<Ingrediente> {
        val lista = mutableListOf<Ingrediente>()
        File(path).inputStream().buffered().use {
            while (it.available() > 0) {
                val id = StringBuilder()
                var char = it.read().toChar()
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
                lista.add(
                    Ingrediente(
                        id.toString().toInt(),
                        nombre.toString(),
                        precio.toString().toDouble()
                    )
                )
            }
        }
        return lista
    }

    override fun guardar(items: List<Ingrediente>) {
        File(path).writeText("")
        File(path).outputStream().buffered().use {
            items.forEach { ingrediente ->
                it.write(
                    ingrediente.id.toString().toByteArray()
                    + "\n".toByteArray()
                    + ingrediente.nombre.toByteArray()
                    + "\n".toByteArray()
                    + ingrediente.precio.toString().toByteArray()
                    + "\n".toByteArray()
                )
            }
        }

    }
}