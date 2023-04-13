import factories.ControllerFactory
import models.Hamburguesa
import models.Ingrediente
import java.util.*

fun main() {
    // Se necesita un fichero ya creado para realizar las consultas

    // Controladores
    val ic = ControllerFactory.ingredienteJson()
    val hc = ControllerFactory.hamburguesaJson()

    val ingredientes = ic.listarIngredientes()
    val hamburguesas = hc.listarHamburguesas()

    // Consultas

    // Hamburguesa más cara
    val hamburguesaMasCara = hamburguesas
        .maxByOrNull { it.precio }

    // Hamburguesa con más ingredientes
    val hamburguesaMasIngredientes = hamburguesas
        .maxByOrNull { it.ingredientes.size }

    // Número de hamburguesas por ingrediente
    val hamburguesasPorIngrediente = ingredientes
        .associateWith {
            hamburguesas
                .count { hamburguesa -> hamburguesa.ingredientes.contains(it) }
        }.mapKeys { it.key.nombre }

    // Hamburguesas agrupadas por total de ingredientes
    val hamburguesasPorTotalIngredientes = hamburguesas
        .groupBy { it.ingredientes.size }

    // Precio medio de las hamburguesas
    val precioMedioHamburguesas = hamburguesas
        .map { it.precio }
        .average()

    // Precio medio de los ingredientes
    val precioMedioIngredientes = ingredientes
        .map { it.precio }
        .average()
}