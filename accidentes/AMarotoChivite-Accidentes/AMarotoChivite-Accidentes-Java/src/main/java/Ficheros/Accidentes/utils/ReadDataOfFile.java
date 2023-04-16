package Ficheros.Accidentes.utils;

import Ficheros.Accidentes.config.ConfigApp;
import Ficheros.Accidentes.models.dto.AccidenteDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReadDataOfFile {
    public List<AccidenteDto> readDataOfCSV() {
        String localFile = ConfigApp.APP_DATA + File.separator + "accidentes.csv";
        File file = new File(localFile);

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Eliminamos la primera línea
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if (lineNumber != 0) {
                    lines.add(line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<AccidenteDto> listAccidenteDto = lines.stream().map(line -> {
            String[] fields = line.split(";");

            String numExpediente = fields[0];
            String fecha = fields[1];
            String hora = fields[2];
            String localizacion = fields[3];
            String numero = fields[4];

            // Filtrar las filas que no tienen un valor para el campo "cod_distrito".
            // El índice 5 corresponde al campo "cod_distrito".
            String codDistrito = "";
            if (fields[5] == "") {
                codDistrito = "nulo";
            } else {
                codDistrito = fields[5];
            }

            String distrito = fields[6];
            String tipoAccidente = fields[7];
            String estadoMeteorologico = fields[8];
            String tipoVehiculo = fields[9];
            String tipoPersona = fields[10];
            String rangoEdad = fields[11];
            String sexo = fields[12];
            String codLesividad = fields[13];
            String lesividad = fields[14];
            String coordenadaX = fields[15];
            String coordenadaY = fields[16];
            String positivoAlcohol = fields[17];
            String positivoDroga = fields[18];

            return new AccidenteDto(numExpediente, fecha, hora, localizacion, numero, codDistrito, distrito, tipoAccidente, estadoMeteorologico, tipoVehiculo, tipoPersona, rangoEdad, sexo, codLesividad, lesividad, coordenadaX, coordenadaY, positivoAlcohol, positivoDroga);
        }).collect(toList());

        return listAccidenteDto;
    }
}
