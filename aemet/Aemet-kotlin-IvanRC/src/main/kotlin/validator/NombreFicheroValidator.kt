package validator

import java.io.FileNotFoundException

fun String.validate(): Boolean{
    val regexNombreFicheroCsv = Regex("Aemet[0-9]{8}.csv")
    require(this.matches(regexNombreFicheroCsv)){
        throw FileNotFoundException("Ese nombre de fichero no es valido, debe ser, como: Aemet20170101.csv")
    }
    return true
}