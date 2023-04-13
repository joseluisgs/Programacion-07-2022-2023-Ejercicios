package repositories.accidente

import models.Accidente
import repositories.IExternalStore

interface AccidenteRepository: AccidenteExtension, IExternalStore<Accidente>