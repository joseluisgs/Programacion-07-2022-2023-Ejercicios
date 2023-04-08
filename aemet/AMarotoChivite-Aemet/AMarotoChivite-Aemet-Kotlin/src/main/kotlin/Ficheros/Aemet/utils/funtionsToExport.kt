package Ficheros.Aemet.utils

import Ficheros.Aemet.models.Aemet
import Ficheros.Aemet.models.AemetDailyConsultToExport

fun groupDayWithFilter(repository: List<Aemet>): MutableMap<String, AemetDailyConsultToExport> {
    val dailyWeatherMap = mutableMapOf<String, AemetDailyConsultToExport>()

    for (aemet in repository) {
        val date = "${aemet.date.year}-${aemet.date.monthValue}-${aemet.date.dayOfMonth}"

        if (dailyWeatherMap.containsKey(date)) {
            // Si el día está en el mapa, actualizamos los valores
            val dailyWeather = dailyWeatherMap[date]!!
            dailyWeather.addTemperature(
                aemet.temperaturaMaxima,
                aemet.horaTemperaturaMaxima.toString(),
                aemet.temperaturaMinima,
                aemet.horaTemperaturaMinima.toString(),
                "${aemet.nombrePoblacion} - ${aemet.nombreProvincia}"
            )
            dailyWeather.addPrecipitation(aemet.precipitacion)
            dailyWeather.mediaTemperature = dailyWeather.getAverageTemperature()
            if (dailyWeather.precipitation > 0.0) {
                dailyWeather.isPrecipitacion = true
            }
        } else {
            // Si el día no está en el mapa, añadimos un nuevo día
            val dailyWeather = AemetDailyConsultToExport(date)
            dailyWeather.addTemperature(
                aemet.temperaturaMaxima,
                aemet.horaTemperaturaMaxima.toString(),
                aemet.temperaturaMinima,
                aemet.horaTemperaturaMinima.toString(),
                "${aemet.nombrePoblacion} - ${aemet.nombreProvincia}"
            )
            dailyWeather.addPrecipitation(aemet.precipitacion)
            dailyWeather.mediaTemperature = dailyWeather.getAverageTemperature()
            if (dailyWeather.precipitation > 0.0) {
                dailyWeather.isPrecipitacion = true
            }
            dailyWeatherMap[date] = dailyWeather
        }
    }
    return dailyWeatherMap
}