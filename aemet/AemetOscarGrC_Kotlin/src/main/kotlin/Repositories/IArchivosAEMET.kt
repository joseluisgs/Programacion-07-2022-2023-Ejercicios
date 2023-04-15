package Repositories

import models.RegistroDiarioRM
import models.RegistroGeneralRDRM
import models.RegistroMeteo
import models.RegistroTemperatura
import java.io.File
import java.lang.IllegalArgumentException

interface IArchivosAEMET {
    var archivo:RegistroGeneralRDRM
    fun leer():RegistroGeneralRDRM?
    fun escribir()
    fun leerArchivoOriginalCSV(ficheroEntrada: File): RegistroDiarioRM
    fun addRegistro(RegistroDiario:RegistroDiarioRM )
    fun informeMadrid( ficheroEntrada: File)
}
