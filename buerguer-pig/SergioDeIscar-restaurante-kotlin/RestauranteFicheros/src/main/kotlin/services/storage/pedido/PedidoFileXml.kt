package services.storage.pedido

import config.AppConfig
import dto.PedidosDto
import models.LineaPedido
import models.Pedido
import org.simpleframework.xml.core.Persister
import java.io.File
import java.time.LocalDateTime

object PedidoFileXml: PedidoStorageService {

    private val localFile = "${AppConfig.APP_DATA}${File.separator}pedido.xml"

    override fun saveAll(elements: List<Pedido>): List<Pedido> {
        val listDto = elements.map { it.toDto() }
        val pedidosDto = PedidosDto(listDto)

        val serializer = Persister()
        serializer.write(pedidosDto, File(localFile));

        return elements
    }

    override fun loadAll(): List<Pedido> {
        val serializer = Persister()
        val pacientes = serializer
            .read(PedidosDto::class.java, File(localFile))
        return pacientes.pedidos.map {
            Pedido(
                it.userId.toInt(),
                it.productos.map {
                    LineaPedido(
                        it.productoId.toInt(),
                        it.precioUnd.toFloat(),
                        it.cantidad.toInt()
                    )
                },
                LocalDateTime.parse(it.createAt),
                it.total.toFloat()
            )
        }
    }
}