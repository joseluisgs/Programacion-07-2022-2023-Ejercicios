package factories

import controllers.HamburguesaController
import controllers.IngredienteController
import repositories.HamburguesaRepository
import repositories.IngredienteRepository
import services.storage.hamburguesa.HamburguesaCsvService
import services.storage.hamburguesa.HamburguesaSerializableService
import services.storage.ingrediente.IngredienteSerializableService

object ControllerFactory {
    fun ingredienteSerializable() = IngredienteController(IngredienteRepository(IngredienteSerializableService))
    fun hamburguesaSerializable() = HamburguesaController(HamburguesaRepository(HamburguesaSerializableService))
    fun hamburguesaCsv() = HamburguesaController(HamburguesaRepository(HamburguesaCsvService))
}