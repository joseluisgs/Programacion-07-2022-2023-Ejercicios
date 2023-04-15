package mapper.accidente

import dto.accidente.AccidenteDto
import dto.accidente.ListaAccidentesDto
import mapper.distrito.toDistrito
import mapper.distrito.toDto
import mapper.lesividad.toDto
import mapper.lesividad.toLesividad
import model.Accidente
import utils.`toLocalDateDD-MM-YYYY`
import utils.toLocalTime

fun Accidente.toDto(): AccidenteDto{
    return AccidenteDto(
        numeroExpediente = this.numeroExpediente,
        fecha = this.fecha.toString(),
        hora = this.hora.toString(),
        localizacion = this.localizacion,
        numeroCalle = if(this.numeroCalle != null) this.numeroCalle.toString() else "NULL",
        distrito = this.distrito.toDto(),
        tipoAccidente = this.tipoAccidente,
        estadoMeteorologico = this.estadoMeteorologico,
        tipoDeVehiculo = this.tipoDeVehiculo,
        tipoDePersona = this.tipoDePersona,
        rangoEdad = this.rangoEdad,
        sexo = this.sexo,
        lesividad = this.lesividad.toDto(),
        longitud = if(this.longitud != null) this.longitud.toString() else "NULL",
        altitud = if(this.altitud != null) this.altitud.toString() else "NULL",
        esPositivaEnAlchol = if(this.esPositivaEnAlchol) "S" else "N",
        esPositivaEnDrogas = if(this.esPositivaEnDrogas) "1" else "NULL"
    )
}

fun AccidenteDto.toAccidente(): Accidente{
    return Accidente(
        numeroExpediente = this.numeroExpediente,
        fecha = `toLocalDateDD-MM-YYYY`(this.fecha),
        hora = toLocalTime(this.hora),
        localizacion = this.localizacion,
        numeroCalle = if(this.numeroCalle.matches(Regex("[0-9]*"))) this.numeroCalle.toInt() else null,
        distrito = this.distrito.toDistrito(),
        tipoAccidente = this.tipoAccidente,
        estadoMeteorologico = this.estadoMeteorologico,
        tipoDeVehiculo = this.tipoDeVehiculo,
        tipoDePersona = this.tipoDePersona,
        rangoEdad = this.rangoEdad,
        sexo = this.sexo,
        lesividad = this.lesividad.toLesividad(),
        longitud = if(this.longitud.matches(Regex("[0-9]+.[0-9]+")))this.longitud.replace(",", ".").replace("\"", "").toDouble() else null,
        altitud = if(this.altitud.matches(Regex("[0-9]+.[0-9]+")))this.altitud.replace(",", ".").replace("\"", "").toDouble() else null,
        esPositivaEnAlchol = this.esPositivaEnAlchol == "S",
        esPositivaEnDrogas = this.esPositivaEnDrogas == "1"
    )
}

fun ListaAccidentesDto.toAccidentes(): List<Accidente>{
    return this.accidentesDto.map { it.toAccidente() }
}

fun List<Accidente>.toAccidentesDto(): ListaAccidentesDto{
    return ListaAccidentesDto(
        this.map { it.toDto() }
    )
}
