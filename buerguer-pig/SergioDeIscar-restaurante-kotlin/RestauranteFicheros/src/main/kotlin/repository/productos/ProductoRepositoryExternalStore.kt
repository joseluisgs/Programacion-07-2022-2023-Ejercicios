package repository.productos

import models.Producto
import repository.IExternalStore

interface ProductoRepositoryExternalStore: ProductoRepository, IExternalStore<Producto>