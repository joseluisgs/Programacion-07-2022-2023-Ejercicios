package Ficheros.Aemet.models

data class Temperature(
    val value: Double,
    val time: String,
    val lugar: String
)

class AemetDailyConsultToExport(
    val date: String
) {
    var maxTemperature: Temperature? = null
    var minTemperature: Temperature? = null
    var mediaTemperature: Double = 0.0
    var precipitation: Double = 0.0
    var isPrecipitacion: Boolean = false

    @Transient // Transient propio de la librería de kotlin, para que no me serialice el campo
    private var temperatureCount = 0

    @Transient
    private var temperatureSum = 0.0

    fun addTemperature(
        maxTemperatureValue: Double,
        maxTemperatureTime: String,
        minTemperatureValue: Double,
        minTemperatureTime: String,
        lugar: String
    ) {
        val maxTemp = Temperature(maxTemperatureValue, maxTemperatureTime, lugar)
        val minTemp = Temperature(minTemperatureValue, minTemperatureTime, lugar)

        if (maxTemperature == null || maxTemp.value > maxTemperature!!.value) {
            maxTemperature = maxTemp
        }

        if (minTemperature == null || minTemp.value < minTemperature!!.value) {
            minTemperature = minTemp
        }

        temperatureCount++
        temperatureSum += maxTemperatureValue + minTemperatureValue
    }

    fun addPrecipitation(value: Double) {
        precipitation += value
    }

    fun getAverageTemperature(): Double {
        return if (temperatureCount > 0) {
            temperatureSum / (temperatureCount * 2)
        } else {
            0.0
        }
    }
}