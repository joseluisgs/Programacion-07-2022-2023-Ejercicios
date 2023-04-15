package Ficheros.Accidentes.storages;

import Ficheros.Accidentes.config.ConfigApp;
import Ficheros.Accidentes.models.dto.AccidenteDto;
import Ficheros.Accidentes.models.dto.ListAccidentesDto;
import Ficheros.Accidentes.utils.ReadDataOfFile;
import ch.qos.logback.classic.Logger;
import org.simpleframework.xml.core.Persister;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class StorageXml implements IStorageToWriteRead<AccidenteDto> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageXml.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "AccidentesXML.xml";
    private final File file = new File(localFile);

    private final Persister serializer = new Persister();

    @Override
    public void saveInFileWithFilter() {
        logger.debug("Storage: Escribiendo en XML");

        List<AccidenteDto> listToExport = new ReadDataOfFile().readDataOfCSV();
        ListAccidentesDto objectListAccidentesDto = new ListAccidentesDto();
        objectListAccidentesDto.setMyList(listToExport);
        try {
            serializer.write(objectListAccidentesDto, file);
        } catch (Exception e) {
            logger.error("Error al escribir en XML", e);
        }
    }

    @Override
    public List<AccidenteDto> readAllModelsInFile() {
        logger.debug("Storage: Leyendo desde XML");

        ListAccidentesDto listOfAccidentesDTO = null;
        try {
            listOfAccidentesDTO = serializer.read(ListAccidentesDto.class, file);
        } catch (Exception e) {
            logger.error("Error al leer desde XML", e);
        }

        if (listOfAccidentesDTO != null) {
            return listOfAccidentesDTO.getMyList();
        } else {
            return null;
        }
    }
}
