package models

import java.io.Serializable

data class Ingrediente(
//    @Json(name = "code") // CUIDADO SI CAMBIAMOS EL NOMBRE NO PODREMOS LEERLO
    val id: Int,
    val name: String,
    val price: Double
) : Serializable

// IMPORTANTE no podremos escribir una clase serializable a no ser que esta sea una data class

/*{

    private val logger = KotlinLogging.logger {}


    *    El ID para que sea autonumerico no puede ser estático (companion object).
    *    Debemos crearlo en un factory porque iremos creando varios objetos a lo largo del programa al ser autonumerico el
    *    contador ira aumentando y no podremos controlar el ID del objeto. Por ello debe ir en el constructor y el factory
    *    se encargue de generar el ID.
    *    companion object {
    *        private var counter = 0
    *    }
    *    var id = ++counter


    override fun toString(): String {
        return "Ingrediente(id=$id, name='$name', price=${price.toLocalMoney()})"
    }
}*/