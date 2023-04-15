package Ficheros.Aemet.models.dto;

import Ficheros.Aemet.models.AemetDailyConsultToExport;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.Map;

@Root(name = "informe")
public class AemetDailyMapToExportXML {
    @ElementMap(name = "map", key = "key", entry = "dia_value", attribute = true, inline = true)
    private Map<String, AemetDailyConsultToExport> myMap;

    public Map<String, AemetDailyConsultToExport> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, AemetDailyConsultToExport> myMap) {
        this.myMap = myMap;
    }
}
