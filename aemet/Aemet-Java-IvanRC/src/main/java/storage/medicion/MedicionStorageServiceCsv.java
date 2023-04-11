package storage.medicion;

import config.ConfigApp;
import dto.MedicionDto;
import jdk.jshell.execution.Util;
import mapper.MedicionesMapper;
import models.Medicion;
import utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicionStorageServiceCsv implements MedicionStorageService {

    ConfigApp configApp = ConfigApp.getInstance();

    File file;

    String name;

    public MedicionStorageServiceCsv(String name) throws IOException {
        this.name = name;
        this.file = new File(ClassLoader.getSystemResource(name).getFile());
    }

    @Override
    public void saveAll(List<Medicion> entities) {
        // Para este caso, como no vamos a escribir en el csv, no requerimos de esta función.
    }

    @Override
    public List<Medicion> loadAll() throws FileNotFoundException {
        List<Medicion> ingredientes = new ArrayList<>();
        if(!file.exists()) return ingredientes;
        ArrayList<Integer> fecha = Utils.toFormatoFechaValido(name);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(";");
                ingredientes.add(
                        MedicionesMapper.toMedicion(
                                new MedicionDto(
                                        campos[0],
                                        campos[1],
                                        campos[2],
                                        campos[3],
                                        campos[4],
                                        campos[5],
                                        campos[6],
                                        fecha.get(0)+"-"+fecha.get(1)+"-"+fecha.get(2)
                                ))
                );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
}
