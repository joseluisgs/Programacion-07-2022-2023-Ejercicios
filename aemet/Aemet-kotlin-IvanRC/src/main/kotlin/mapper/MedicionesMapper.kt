package mapper

import dto.ListaMedicionesDto
import dto.MapaMedicionesDto
import dto.MedicionDto
import model.Medicion
import utils.toLocalDate
import utils.toLocalTime
import java.time.LocalDate

fun Medicion.toDto(): MedicionDto{
    return MedicionDto(
        poblacion = this.poblacion,
        provincia = this.provincia,
        temperaturaMax = this.temperaturaMax.toString(),
        horaTempMax = this.horaTempMax.toString(),
        temperaturaMin = this.temperaturaMin.toString(),
        horaTempMin = this.horaTempMin.toString(),
        precipitacion = this.precipitacion.toString(),
        dia = this.dia.toString()
    )
}

fun MedicionDto.toMedicion(): Medicion{
    return Medicion(
        poblacion = this.poblacion,
        provincia = this.provincia,
        temperaturaMax = this.temperaturaMax.toDouble(),
        horaTempMax = this.horaTempMax.toLocalTime(),
        temperaturaMin = this.temperaturaMin.toDouble(),
        horaTempMin = this.horaTempMin.toLocalTime(),
        precipitacion = this.precipitacion.toDouble(),
        dia = this.dia.toLocalDate()
    )
}

fun Map<LocalDate, List<Medicion>>.toMedicionesDto(): MapaMedicionesDto{
    return MapaMedicionesDto(
        this.mapKeys { it.key.toString() }.mapValues {medicion -> ListaMedicionesDto(medicion.value.map { it.toDto() }) }
    )
}

fun MapaMedicionesDto.toMediciones(): Map<LocalDate, List<Medicion>>{
    return this.medicionesDto
        .mapKeys { it.key.toLocalDate() }.mapValues { it.value.mediciones.map { it.toMedicion() } }
}