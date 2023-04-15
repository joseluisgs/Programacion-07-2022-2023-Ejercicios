package Controllers

import Repositories.IArchivosAEMET
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.*
import org.simpleframework.xml.core.Persister
import java.io.File

class MeteoControler(var repositorio:IArchivosAEMET) {
    val logger = mu.KotlinLogging.logger {}
    init{
        logger.debug { "INICIANDO CONTROLADOR " }
    }


    fun leer(): RegistroGeneralRDRM? {
        logger.debug { "CONTROLADOR FUN LEER" }
        var salida = repositorio.leer()
        if(salida==null) println("Error") //no quiero hacer una clase para errores
         return  salida
        }
     fun escribir() {
        logger.debug { "CONTROLADOR FUN ESCRIBIR " }
        var salida = repositorio.escribir()
    }
     fun leerArchivoOriginalCSV(ficheroEntrada: File): RegistroDiarioRM {
        logger.debug { "CONTROLADOR FUN leerArchivoOriginalCSV  " }
       return repositorio.leerArchivoOriginalCSV(ficheroEntrada = ficheroEntrada)
    }


     fun addRegistro(RegistroDiario: RegistroDiarioRM) {
        logger.debug { "CONTROLADOR FUN  addRegistro " }
       repositorio.archivo.registro.add(RegistroDiario)
    }

     fun informeMadrid(ficheroEntrada: File) {
         logger.debug { "CONTROLADOR FUN  informeMadrid " }
        repositorio.informeMadrid(ficheroEntrada = ficheroEntrada)
    }
}

