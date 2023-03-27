package repositories.informe

import models.Informe
import repositories.CrudRepository
import java.time.LocalDate

interface InformeExtension: CrudRepository<Informe, LocalDate>{
    //fun generateInforme(): Informe
}