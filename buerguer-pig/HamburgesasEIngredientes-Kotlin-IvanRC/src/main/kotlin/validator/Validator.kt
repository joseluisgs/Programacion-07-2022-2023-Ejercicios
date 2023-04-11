package validator

import exception.hamburgesaException.HamburgesaBadRequestException
import exception.ingredienteException.IngredienteBadRequestException
import model.Hamburgesa
import model.Ingrediente
import repository.Ingrediente.IngredienteRepository

fun String.validate(exception: Exception): Boolean{
    require(this.isNotEmpty()){
        throw exception
    }
    return true
}

fun String.validateNumber(exception: Exception, min: Number): Boolean{
    if(min is Int) {
        val regex = Regex("-?[0-9]*")
        require(this.matches(regex)){
            throw exception
        }
        require(this.toInt() >= min) {
            throw exception
        }
    }else{
        if(min is Double) {
            val regex = Regex("-?[0-9]*.[0-9]*")
            require(this.matches(regex)){
                throw exception
            }
            require(this.toDouble() >= min) {
                throw exception
            }
        }
    }
    return true
}

fun Ingrediente.validate(): Boolean{
    this.id.toString().validateNumber(IngredienteBadRequestException("id = "+this.id.toString()), 1)
    this.nombre.validate(IngredienteBadRequestException("nombre = ${this.nombre}"))
    this.precio.toString().validateNumber(IngredienteBadRequestException("precio = ${this.precio}"), 0.0)
    return true
}

fun Hamburgesa.validate(): Boolean{
    this.nombre.validate(HamburgesaBadRequestException("nombre = ${this.nombre}"))
    this.ingredientes.forEach {
        it.validate()
    }
    return true
}

fun validarListaDeIngredientes(ingredientes: List<Int>, ingredienteRepo: IngredienteRepository): Boolean {
    ingredientes.forEach{
        val ingrediente = ingredienteRepo.findById(it)
        require(ingrediente != null){
            throw HamburgesaBadRequestException("ingrediente de id = $it")
        }
    }
    return true
}

fun String.validateFecha(exception: Exception): Boolean{
    val regex = Regex("[0-9]{4}-[0-9]{2}-[0-9]{2}")
    require(this.matches(regex)){
        throw exception
    }
    return true
}