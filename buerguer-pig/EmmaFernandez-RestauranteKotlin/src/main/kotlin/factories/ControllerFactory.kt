package factories

import controllers.HamburguesaController
import controllers.IngredienteController
import repositories.HamburguesaRepository
import repositories.IngredienteRepository
import services.storage.binario.HamburguesaBinarioService
import services.storage.csv.HamburguesaCsvService
import services.storage.serializable.HamburguesaSerializableService
import services.storage.texto.HamburguesaTextoService
import services.storage.xml.HamburguesaXmlService
import services.storage.binario.IngredienteBinarioService
import services.storage.csv.IngredienteCsvService
import services.storage.json.HamburguesaJsonService
import services.storage.json.IngredienteJsonService
import services.storage.serializable.IngredienteSerializableService
import services.storage.texto.IngredienteTextoService
import services.storage.xml.IngredienteXmlService

object ControllerFactory {
    fun ingredienteSerializable() = IngredienteController(IngredienteRepository(IngredienteSerializableService))
    fun ingredienteCsv() = IngredienteController(IngredienteRepository(IngredienteCsvService))
    fun ingredienteTexto() = IngredienteController(IngredienteRepository(IngredienteTextoService))
    fun ingredienteBinario() = IngredienteController(IngredienteRepository(IngredienteBinarioService))
    fun ingredienteXml() = IngredienteController(IngredienteRepository(IngredienteXmlService))
    fun ingredienteJson() = IngredienteController(IngredienteRepository(IngredienteJsonService))

    fun hamburguesaSerializable() = HamburguesaController(HamburguesaRepository(HamburguesaSerializableService))
    fun hamburguesaCsv() = HamburguesaController(HamburguesaRepository(HamburguesaCsvService))
    fun hamburguesaTexto() = HamburguesaController(HamburguesaRepository(HamburguesaTextoService))
    fun hamburguesaBinario() = HamburguesaController(HamburguesaRepository(HamburguesaBinarioService))
    fun hamburguesaXml() = HamburguesaController(HamburguesaRepository(HamburguesaXmlService))
    fun hamburguesaJson() = HamburguesaController(HamburguesaRepository(HamburguesaJsonService))
}