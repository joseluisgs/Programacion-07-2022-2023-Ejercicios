package Models

import Interfaz.IngredienteRepository
import java.io.File

open class FileIngredienteRepository(private val file: File) : IngredienteRepository {
    override fun crearIngrediente(ingrediente: Ingrediente) {
        // Implementación para escribir en un fichero de texto, binario, serializado, CSV, JSON o XML
    }

    override fun obtenerIngredientePorId(id: Int): Ingrediente? {
        // Implementación para leer un ingrediente por su ID desde un fichero de texto, binario, serializado, CSV, JSON o XML
        return null
    }

    override fun obtenerIngredienteMasCaro(): Ingrediente? {
        // Implementación para obtener el ingrediente más caro desde un fichero de texto, binario, serializado, CSV, JSON o XML
        return null
    }
}
