package factories

import controllers.DuplaController
import models.Informe
import java.time.LocalDate

object InformeFactory {
    fun createInformeMadrid(controller: DuplaController, day: LocalDate): Informe {
        val maxDupla = controller.maxTemMadrid(day)
        val minDupla = controller.minTemMadrid(day)
        val mediaPrecipitacion = controller.mediaPrecipitacionMadrid(day)
        return Informe(
            day,
            controller.mediaTemMadrid(),
            Triple(
                maxDupla.poblacion,
                maxDupla.timeMax,
                maxDupla.temMax
            ),
            Triple(
                minDupla.poblacion,
                minDupla.timeMin,
                minDupla.temMin
            ),
            Pair(
                mediaPrecipitacion != 0.0,
                mediaPrecipitacion
            )
        )
    }
    fun createInformesMadrid(controller: DuplaController): List<Informe> {
        val informes = mutableListOf<Informe>()
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 29)))
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 30)))
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 31)))
        return informes
    }
}