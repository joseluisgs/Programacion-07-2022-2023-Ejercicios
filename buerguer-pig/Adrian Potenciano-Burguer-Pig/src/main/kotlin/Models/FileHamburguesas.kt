package Models

import Interfaz.HamburguesaRepository
import java.io.File
import java.util.*


class FileHamburguesaRepository(private val file: File) : HamburguesaRepository {
    override fun crearHamburguesa(hamburguesa: Hamburguesa) {
        //Consulta para escribir en un fichero de texto
    }

    override fun obtenerHamburguesaPorId(id: UUID): Hamburguesa? {
        //Consulta para leer una hamburguesa por su ID
        return null
    }

    override fun obtenerHamburguesaMasCara(): Hamburguesa? {
        // Consulta para obtener la hamburguesa más cara
        return null
    }

    override fun obtenerHamburguesaConMasIngredientes(): Hamburguesa? {
        // Consulta para obtener la hamburguesa con más ingredientes
        return null
    }

    override fun obtenerNumeroDeHamburguesasPorIngrediente(): Map<String, Int> {
        // Consulta para obtener el número de hamburguesas por ingrediente
        return emptyMap()
    }

    override fun obtenerHamburguesasAgrupadasPorTotalDeIngredientes(): Map<Int, List<Hamburguesa>> {
        // Consulta para obtener las hamburguesas agrupadas por total de ingredientes
        return emptyMap()
    }

    override fun obtenerPrecioPromedioDeHamburguesas(): Double {
        // Consulta para obtener el precio promedio de las hamburguesas
        return 0.0
    }
    override fun obtenerPrecioPromedioDeIngredientes(): Double {
        // Consulta para obtener el precio promedio de los ingredientes
        return 0.0
    }
}
