package Ficheros.BurguerPig.storages;

import Ficheros.BurguerPig.config.ConfigApp;
import Ficheros.BurguerPig.mappers.BurguerListMapper;
import Ficheros.BurguerPig.mappers.BurguerMapper;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.dto.BurguerDTO;
import Ficheros.BurguerPig.models.dto.BurguerListDto;
import ch.qos.logback.classic.Logger;
import org.simpleframework.xml.core.Persister;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class StorageXml implements IStorageToWriteRead<Burguer> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(StorageXml.class);

    private final String localFile = ConfigApp.APP_DATA + File.separator + "burguerXML.xml";
    private final File file = new File(localFile);

    private final Persister serializer = new Persister();

    @Override
    public void saveFile(List<Burguer> listItems) {
        logger.debug("Storage: Escribiendo en XML");
        var listBurguers = new BurguerListMapper(new BurguerMapper()).toDtoList(listItems);
        try {
            serializer.write(listBurguers, file);
        } catch (Exception e) {
            logger.error("Error al escribir en XML", e);
        }
    }

    @Override
    public List<Burguer> readFile() {

        BurguerListDto listOfBurguersDTO = null;
        try {
            listOfBurguersDTO = serializer.read(BurguerListDto.class, file);
        } catch (Exception e) {
            logger.error("Error al leer desde XML", e);
        }

        if (listOfBurguersDTO != null) {
            return new BurguerListMapper(new BurguerMapper()).toModelList(listOfBurguersDTO);
        } else {
            return null;
        }
    }
}
