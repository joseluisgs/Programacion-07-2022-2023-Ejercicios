package Ficheros.Aemet.models.dto

import Ficheros.Aemet.models.AemetDailyConsultToExport
import org.simpleframework.xml.ElementMap
import org.simpleframework.xml.Root

@Root(name = "informe")
class AemetDailyMapToExportXML {
    @field:ElementMap(name = "map", key = "key", entry = "dia_value", attribute = true, inline = true)
    var myMap: Map<String, AemetDailyConsultToExport>? = null
}