package validators

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import errors.ProductoError
import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import validotors.validate

class ProductoValidatorTest {
    private fun getIngredientesOk() = listOf(
        Ingrediente(1, "tomate", 2.2f),
        Ingrediente(2, "lechuga", 3.2f),
        Ingrediente(3, "queso", 1.2f),
    )

    @Test
    fun validateProductosOk(){
        val bebidaOk = Bebida(1, "Fanta", 2.0f, 250)
        val resultBebida = bebidaOk.validate()

        val hamburguesaOk = Hamburguesa(2, "Grande", getIngredientesOk())
        val resultHamburguesa = hamburguesaOk.validate()

        assertAll(
            { assert(resultBebida.getError() == null) },
            { assert(resultBebida.get() == bebidaOk) },

            { assert(resultHamburguesa.getError() == null) },
            { assert(resultHamburguesa.get() == hamburguesaOk) },
        )
    }

    @Test
    fun validateBebidaIdFail(){
        val bebidaFail = Bebida(-1, "Fanta", 2.0f, 250)
        val resultBebida = bebidaFail.validate()
        assertAll(
            { assert(resultBebida.getError() != null) },
            { assert(resultBebida.getError() is ProductoError.IdError) }
        )
    }

    @Test
    fun validateBebidaNombreFail(){
        val bebidaFail = Bebida(1, "", 2.0f, 250)
        val resultBebida = bebidaFail.validate()
        assertAll(
            { assert(resultBebida.getError() != null) },
            { assert(resultBebida.getError() is ProductoError.NombreError) }
        )
    }

    @Test
    fun validateBebidaPrecioFail(){
        val bebidaFail = Bebida(1, "Fanta", -1f, 250)
        val resultBebida = bebidaFail.validate()
        assertAll(
            { assert(resultBebida.getError() != null) },
            { assert(resultBebida.getError() is ProductoError.PrecioError) }
        )
    }

    @Test
    fun validateBebidaCapacidadFail(){
        val bebidaFail = Bebida(1, "Fanta", 2.2f, -1)
        val resultBebida = bebidaFail.validate()
        assertAll(
            { assert(resultBebida.getError() != null) },
            { assert(resultBebida.getError() is ProductoError.CapacidadError) }
        )
    }

    @Test
    fun validateHamburguesaIdFail(){
        val hamburguesaFail = Hamburguesa(-1, "Grande", getIngredientesOk())
        val resultHamburguesa = hamburguesaFail.validate()
        assertAll(
            { assert(resultHamburguesa.getError() != null) },
            { assert(resultHamburguesa.getError() is ProductoError.IdError) }
        )
    }

    @Test
    fun validateHamburguesaNombreFail(){
        val hamburguesaFail = Hamburguesa(2, "", getIngredientesOk())
        val resultHamburguesa = hamburguesaFail.validate()
        assertAll(
            { assert(resultHamburguesa.getError() != null) },
            { assert(resultHamburguesa.getError() is ProductoError.NombreError) }
        )
    }

    @Test
    fun validateIngredienteIdFail(){
        val ingredienteFail = Ingrediente(-1, "tomate", 2.2f)
        val resultIngrediente = ingredienteFail.validate()
        assertAll(
            { assert(resultIngrediente.getError() != null) },
            { assert(resultIngrediente.getError() is ProductoError.IngredienteError) }
        )
    }

    @Test
    fun validateIngredienteNombreFail(){
        val ingredienteFail = Ingrediente(1, "", 2.2f)
        val resultIngrediente = ingredienteFail.validate()
        assertAll(
            { assert(resultIngrediente.getError() != null) },
            { assert(resultIngrediente.getError() is ProductoError.IngredienteError) }
        )
    }

    @Test
    fun validateIngredientePrecioFail(){
        val ingredienteFail = Ingrediente(1, "tomate", -1f)
        val resultIngrediente = ingredienteFail.validate()
        assertAll(
            { assert(resultIngrediente.getError() != null) },
            { assert(resultIngrediente.getError() is ProductoError.IngredienteError) }
        )
    }

    @Test
    fun validateIngredientesPrecioInHamburguesaFail(){
        val ingredienteFail = Ingrediente(1, "tomate", -1f)
        val hamburguesaFail = Hamburguesa(1, "Grande", listOf(ingredienteFail))
        val resultHamburguesa = hamburguesaFail.validate()
        assertAll(
            { assert(resultHamburguesa.getError() != null) },
            { assert(resultHamburguesa.getError() is ProductoError.PrecioError) }
        )
    }

    @Test
    fun validateIngredientesNombreInHamburguesaFail(){
        val ingredienteFail = Ingrediente(1, "", 1.2f)
        val hamburguesaFail = Hamburguesa(1, "Grande", listOf(ingredienteFail))
        val resultHamburguesa = hamburguesaFail.validate()
        assertAll(
            { assert(resultHamburguesa.getError() != null) },
            { assert(resultHamburguesa.getError() is ProductoError.IngredienteError) }
        )
    }
}