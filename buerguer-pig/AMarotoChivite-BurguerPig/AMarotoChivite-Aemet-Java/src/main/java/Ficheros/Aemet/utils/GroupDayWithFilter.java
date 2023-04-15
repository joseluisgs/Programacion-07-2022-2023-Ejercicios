package Ficheros.Aemet.utils;

import Ficheros.Aemet.models.Aemet;
import Ficheros.Aemet.models.AemetDailyConsultToExport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDayWithFilter {
    public static Map<String, AemetDailyConsultToExport> create(List<Aemet> repository) {
        Map<String, AemetDailyConsultToExport> dailyWeatherMap = new HashMap<>();

        for (Aemet aemet : repository) {
            String date = aemet.getDate().getYear() + "-" + aemet.getDate().getMonthValue() + "-" + aemet.getDate().getDayOfMonth();

            if (dailyWeatherMap.containsKey(date)) {
                // Si el día está en el mapa, actualizamos valores
                AemetDailyConsultToExport dailyWeather = dailyWeatherMap.get(date);
                dailyWeather.addTemperature(
                        aemet.getTemperaturaMaxima(),
                        aemet.getHoraTemperaturaMaxima().toString(),
                        aemet.getTemperaturaMinima(),
                        aemet.getHoraTemperaturaMinima().toString(),
                        aemet.getNombrePoblacion() + " - " + aemet.getNombreProvincia()
                );
                dailyWeather.addPrecipitation(aemet.getPrecipitacion());
                dailyWeather.setMediaTemperature(dailyWeather.getAverageTemperature());
                if (dailyWeather.getPrecipitation() > 0.0) {
                    dailyWeather.setPrecipitacion(true);
                }
            } else {
                // Si el día no está en el mapa, añadimos nuevo día
                AemetDailyConsultToExport dailyWeather = new AemetDailyConsultToExport(date);
                dailyWeather.addTemperature(
                        aemet.getTemperaturaMaxima(),
                        aemet.getHoraTemperaturaMaxima().toString(),
                        aemet.getTemperaturaMinima(),
                        aemet.getHoraTemperaturaMinima().toString(),
                        aemet.getNombrePoblacion() + " - " + aemet.getNombreProvincia()
                );
                dailyWeather.addPrecipitation(aemet.getPrecipitacion());
                dailyWeather.setMediaTemperature(dailyWeather.getAverageTemperature());
                if (dailyWeather.getPrecipitation() > 0.0) {
                    dailyWeather.setPrecipitacion(true);
                }
                dailyWeatherMap.put(date, dailyWeather);
            }
        }
        return dailyWeatherMap;
    }
}
