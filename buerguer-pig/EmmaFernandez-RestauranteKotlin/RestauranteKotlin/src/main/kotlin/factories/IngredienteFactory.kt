package factories

object IngredienteFactory {
    fun crearIngrediente(nombre: String, precio: Double) =
        models.Ingrediente(getID(), nombre, precio)

    private var id = 0
    private fun getID() = ++id
}