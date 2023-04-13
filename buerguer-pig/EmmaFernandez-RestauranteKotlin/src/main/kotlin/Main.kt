import factories.ControllerFactory
import models.Ingrediente

fun main() {
    val ingreds = listOf(
        Ingrediente(1, "Tomate", 10.0),
        Ingrediente(2, "Lechuga", 3.5),
        Ingrediente(3, "Queso", 8.0),
    )

    val ic = ControllerFactory.ingredienteJson()
    ic.cargarTodo()
    ic.listarIngredientes().also { println(it) }
}