package services.storage

import exceptions.ProductoFileCantRead
import exceptions.ProductoFileCantWrite
import java.io.File

fun File.puedoLeer(fileType: String){
    if (!this.exists() || !this.canRead()) throw ProductoFileCantRead(fileType)
}
fun File.puedoEscribir(fileType: String){
    if (this.exists() && !this.canWrite()) throw ProductoFileCantWrite(fileType)
}