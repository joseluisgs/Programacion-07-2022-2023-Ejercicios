package repositories.dupla

import models.Dupla
import repositories.IExternalStore

interface DuplaRepository: DuplaExtension, IExternalStore<Dupla>