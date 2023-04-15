package storage.accidentes;

import mapper.distrito.DistritoMapper;
import mapper.lesividad.LesividadMapper;
import model.Accidente;
import model.Distrito;
import model.Lesividad;
import storage.base.StorageService;
import utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AccidenteStorageServiceCSV implements AccidenteStorageService {

    String file = ClassLoader.getSystemResource("accidentes.csv").getFile();
    File fichero = new File(file);

    @Override
    public void saveAll(List<Accidente> entities) throws Exception {
        // Esta funcion no es neesaria para este ejemplo, ya que no sobreescribiremos el fichero CSV
    }

    @Override
    public List<Accidente> loadAll() throws Exception {
        List<Accidente> accidentes = new ArrayList<>();
        if(!fichero.exists()) return accidentes;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(";");
                accidentes.add(
                        transformarEnAccidente(campos)
                );
            }
        }
        return accidentes;
    }

    private Accidente transformarEnAccidente(String[] campos) {
        Accidente accidente;
        if(campos.length == 19) {
            accidente = new Accidente(
                    campos[0],
                    Utils.toLocalDateDDMMYYYY(campos[1]),
                    Utils.toLocalTime(campos[2]),
                    Collections.singletonList(campos[3]),
                    Utils.valorNumericoToInt(campos[4]),
                    new Distrito(
                            Utils.valorNumericoToInt(campos[5]),
                            campos[6]
                    ),
                    campos[7],
                    campos[8],
                    campos[9],
                    campos[10],
                    campos[11],
                    campos[12],
                    new Lesividad(
                            Utils.valorNumericoToInt(campos[13]),
                            campos[14]
                    ),
                    Utils.valorNumericoToDouble(campos[15]),
                    Utils.valorNumericoToDouble(campos[16]),
                    campos[17].equals("S"),
                    campos[18].equals("1")
            );
        }else{
            String[] lista = new String[]{campos[3], campos[4]};
            accidente = new Accidente(
                    campos[0],
                    Utils.toLocalDateDDMMYYYY(campos[1]),
                    Utils.toLocalTime(campos[2]),
                    List.of(lista),
                    Utils.valorNumericoToInt(campos[5]),
                    new Distrito(
                            Utils.valorNumericoToInt(campos[6]),
                            campos[7]
                    ),
                    campos[8],
                    campos[9],
                    campos[10],
                    campos[11],
                    campos[12],
                    campos[13],
                    new Lesividad(
                            Utils.valorNumericoToInt(campos[14]),
                            campos[15]
                    ),
                    Utils.valorNumericoToDouble(campos[16]),
                    Utils.valorNumericoToDouble(campos[17]),
                    campos[18].equals("S"),
                    campos[19].equals("1")
            );
        }
        return accidente;
    }
}
