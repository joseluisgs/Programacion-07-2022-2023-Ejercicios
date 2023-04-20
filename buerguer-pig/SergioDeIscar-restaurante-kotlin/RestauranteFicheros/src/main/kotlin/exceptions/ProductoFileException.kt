package exceptions

import java.io.IOException

sealed class ProductoFileException(message: String): IOException(message)
class ProductoFileCantRead(fileType: String): ProductoFileException("ERROR: No se ha podido leer el fichero del tipo $fileType")
class ProductoFileCantWrite(fileType: String): ProductoFileException("ERROR: No se ha podido escribir el fichero del tipo $fileType")