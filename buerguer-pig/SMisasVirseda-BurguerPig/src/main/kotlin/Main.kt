import services.storage.json.HamburguesaJsonService
import services.storage.json.IngredienteJsonService

fun main() {

    val hamburguesas = HamburguesaJsonService.cargar()
    val ingredientes = IngredienteJsonService.cargar()

    hamburguesas.maxBy { it.precio }.also { println(it.nombre) }

    hamburguesas.maxBy { it.ingredientes.size }.also { println(it.nombre) }

    println(ingredientes.associateWith { ingrediente ->
        hamburguesas.filter { it.ingredientes.contains(ingrediente) }
    }.mapValues {
        it.value.size
    }.mapKeys {
        it.key.nombre
    }
    )

    println(hamburguesas.groupBy { hamburguesa ->
        hamburguesa.ingredientes.size
    }.mapValues {
        it.value.map { hamburguesa ->
            hamburguesa.nombre
        }
    }
    )

    println(
        hamburguesas.map {
            it.precio
        }.average()
    )

    println(
        ingredientes.map {
            it.precio
        }.average()
    )

}
