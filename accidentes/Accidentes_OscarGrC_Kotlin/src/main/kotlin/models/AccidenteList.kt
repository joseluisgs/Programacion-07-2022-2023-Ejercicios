package models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable
@Root(name = "AccidenteList")
    data class AccidenteList (
    @field:ElementList(name = "listaAccidentes", inline = true)
    @param:ElementList(name = "listaAccidentes", inline = true)
    var listaAccidentes: List<Accidente>
            ):Serializable{

    val logger = mu.KotlinLogging.logger {}
    init {
        logger.debug { "Inicializando Listado" }
    }



}