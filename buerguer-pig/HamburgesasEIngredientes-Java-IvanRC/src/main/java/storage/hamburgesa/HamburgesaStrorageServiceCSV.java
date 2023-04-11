package storage.hamburgesa;

import config.ConfigApp;
import models.Hamburgesa;
import models.Ingrediente;

import java.io.*;
import java.util.*;
import java.util.List;

public class HamburgesaStrorageServiceCSV implements HamburgesaStorageService{

    ConfigApp configApp = ConfigApp.getInstance();

    File file = new File(configApp.getAPP_DATA()+File.separator+"hamburgesa.csv");

    public HamburgesaStrorageServiceCSV() throws IOException {
    }

    @Override
    public void saveAll(List<Hamburgesa> entities) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file.getPath()))) {
            br.write("id,nombre,hamburgesas,precio\n");
            entities.forEach(hamburgesa ->
                    {
                        try {
                            br.append(
                                    hamburgesa.getId()+","+hamburgesa.getNombre()+","+fromListToCSV(hamburgesa.getIngredientes())+","+ hamburgesa.getPrecio()+"\n"
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder fromListToCSV(List<Ingrediente> hamburgesas) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("[");
        for(int i=0;i<hamburgesas.size();i++) {
            Ingrediente linea = hamburgesas.get(i);
            if(i!=0) {
                mensaje.append("//" + linea.getId() + ";" + linea.getNombre() + ";" + linea.getPrecio());
            }else{
                mensaje.append(linea.getId() + ";" + linea.getNombre() + ";" + linea.getPrecio());
            }
        }
        mensaje.append("]");
        return mensaje;
    }

    @Override
    public List<Hamburgesa> loadAll() {
        List<Hamburgesa> hamburgesas = new ArrayList<>();
        if(!file.exists()) return hamburgesas;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                hamburgesas.add(new Hamburgesa(
                        UUID.fromString(campos[0]),
                        campos[1],
                        fromCSVToList(campos[2])
                ));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hamburgesas;
    }

    private List<Ingrediente> fromCSVToList(String hamburgesasBase) {
        String[] hamburgesasB = hamburgesasBase.substring(1,hamburgesasBase.length()-1).split("//");
        List<Ingrediente> hamburgesas = new ArrayList<>();
        Arrays.stream(hamburgesasB).map(ingrediente -> ingrediente.split(";")).forEach(ingrediente ->
                hamburgesas.add(new Ingrediente(
                        Integer.parseInt(ingrediente[0]), ingrediente[1], Double.parseDouble(ingrediente[2])
                ))
        );
        return hamburgesas;
    }
}
