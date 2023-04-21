package storage

import config.AppConfig
import services.storage.productos.ProductoFileJson
import java.io.File

@ExperimentalStdlibApi
class ProductoStorageJson: ProductoStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}productos.json"

    override fun getStorage() = ProductoFileJson
}