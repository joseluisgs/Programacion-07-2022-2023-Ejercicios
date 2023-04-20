package services.storage.productos

import config.AppConfig
import dto.ProductosDto
import exceptions.ProductoFileCantRead
import exceptions.ProductoFileCantWrite
import mappers.toClass
import mappers.toDto
import models.Producto
import mu.KotlinLogging
import org.simpleframework.xml.core.Persister
import services.storage.puedoEscribir
import services.storage.puedoLeer
import java.io.File

private val logger = KotlinLogging.logger{}
object ProductoFileXml: ProductosStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}productos.xml"

    override fun saveAll(elements: List<Producto>): List<Producto> {
        logger.debug { "ProductoFileXml ->\tsaveAll" }
        val file = File(localFile)
        file.puedoEscribir("XML")
        val persister = Persister()
        persister.write(ProductosDto(elements.map { it.toDto() }), file)
        return elements
    }

    override fun loadAll(): List<Producto> {
        logger.debug { "ProductoFileXml ->\tloadAll" }
        val file = File(localFile)
        file.puedoLeer("XML")
        val persister = Persister()
        return persister.read(ProductosDto::class.java, file).productos.map { it.toClass() }
    }
}