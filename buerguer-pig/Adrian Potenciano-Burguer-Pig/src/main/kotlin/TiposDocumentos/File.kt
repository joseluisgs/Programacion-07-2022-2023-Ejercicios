package TiposDocumentos

import Models.FileIngredienteRepository
import java.io.File

class TextFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    //ficheros de texto
}


class BinaryFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    // ficheros binarios
}

class SerializedFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    //ficheros serializados
}

class CsvFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    //ficheros CSV
}

class JsonFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    //ficheros JSON
}

class XmlFileIngredienteRepository(file: File) : FileIngredienteRepository(file) {
    //ficheros XML
}