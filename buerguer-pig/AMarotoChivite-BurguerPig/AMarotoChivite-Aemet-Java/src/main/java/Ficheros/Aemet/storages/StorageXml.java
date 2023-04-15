package Ficheros.Aemet.storages;

import Ficheros.Aemet.config.ConfigApp;
import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.models.AemetDailyConsultToExport;
import Ficheros.Aemet.models.dto.AemetDailyMapToExportXML;
import Ficheros.Aemet.utils.GroupDayWithFilter;
import ch.qos.logback.classic.Logger;
import org.simpleframework.xml.core.Persister;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class StorageXml implements IStorageToWrite<Aemet> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageXml.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "AemetDailyXML.xml";
    private final File file = new File(localFile);

    private final Persister serializer = new Persister();

    /**
     * Escribimos el XML el mapa POR CADA DIA con las consultas a continuación
     * - Temperatura media
     * - Temperatura máxima (Lugar y momento)
     * - Temperatura mínima (Lugar y momento)
     * - Si hubo precipitación (sí/no) y valor de la misma.
     */
    @Override
    public void saveFileWithFilter(List<Aemet> listItems) {
        logger.debug("Storage: Escribiendo en XML");

        Map<String, AemetDailyConsultToExport> dailyWeatherMap = new GroupDayWithFilter().create(listItems);
        AemetDailyMapToExportXML myObjectMap = new AemetDailyMapToExportXML();
        myObjectMap.setMyMap(dailyWeatherMap);
        try {
            serializer.write(myObjectMap, file);
        } catch (Exception e) {
            logger.error("Error al escribir en XML", e);
        }
    }
}
