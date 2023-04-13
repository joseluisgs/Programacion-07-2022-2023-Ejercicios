package repositories.informe

import models.Informe
import repositories.IExternalStore

interface InformeRepository: InformeExtension, IExternalStore<Informe>