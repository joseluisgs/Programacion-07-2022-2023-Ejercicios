package storage

import config.AppConfig
import services.storage.productos.ProductoFileCsv
import java.io.File

class ProductoStorageCsv: ProductoStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}productos.csv"

    override fun getStorage() = ProductoFileCsv
}