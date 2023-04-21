package storage

import config.AppConfig
import services.storage.productos.ProductoFileXml
import services.storage.productos.ProductosStorageService
import java.io.File

class ProductoStorageXml: ProductoStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}productos.xml"

    override fun getStorage() = ProductoFileXml
}