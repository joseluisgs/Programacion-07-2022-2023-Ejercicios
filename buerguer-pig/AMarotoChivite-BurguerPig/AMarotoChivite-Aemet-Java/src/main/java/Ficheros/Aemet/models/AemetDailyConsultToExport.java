package Ficheros.Aemet.models;

import com.google.gson.annotations.Expose;
import org.simpleframework.xml.Transient;

public class AemetDailyConsultToExport {

    private final Boolean isPrecipitacion = false;
    private final String date;
    private Double mediaTemperature = 0.0;
    private Temperature maxTemperature;
    private Temperature minTemperature;
    private double precipitation = 0.0;

    @Transient // Para que no me serialice el campo en XML
    @Expose(serialize = false) // Para que no me serialice el campo en GSON
    private transient Integer temperatureCount = 0; // transient delante del tipo para no SERIALIZARLO en general
    @Transient
    @Expose(serialize = false)
    private transient Double temperatureSum = 0.0;
    private boolean precipitacion;

    public AemetDailyConsultToExport(String date) {
        this.date = date;
    }

    public void addTemperature(
            Double maxTemperatureValue,
            String maxTemperatureTime,
            Double minTemperatureValue,
            String minTemperatureTime,
            String lugar
    ) {
        Temperature maxTemp = new Temperature(maxTemperatureValue, maxTemperatureTime, lugar);
        Temperature minTemp = new Temperature(minTemperatureValue, minTemperatureTime, lugar);

        if (maxTemperature == null || maxTemp.getValue() > maxTemperature.getValue()) {
            maxTemperature = maxTemp;
        }

        if (minTemperature == null || minTemp.getValue() < minTemperature.getValue()) {
            minTemperature = minTemp;
        }

        temperatureCount++;
        temperatureSum += maxTemperatureValue + minTemperatureValue;
    }

    public void addPrecipitation(double value) {
        precipitation += value;
    }

    public Double getAverageTemperature() {
        if (temperatureCount > 0) {
            return temperatureSum / (temperatureCount * 2);
        } else {
            return 0.0;
        }
    }

    public void setMediaTemperature(Double mediaTemperature) {
        this.mediaTemperature = mediaTemperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitacion(boolean precipitacion) {
        this.precipitacion = precipitacion;
    }
}
