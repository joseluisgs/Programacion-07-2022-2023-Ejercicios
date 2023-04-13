package repository.pedido

import models.Pedido
import repository.IExternalStore

interface PedidoRepositoryExternalStore: PedidoRepository, IExternalStore<Pedido>