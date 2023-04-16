package mappers

import dto.AccidenteDto
import dto.AccidenteListDto
import models.Accidente
import utils.toLocalDate
import utils.toLocalTime


fun Accidente.toDto() = AccidenteDto(
    numeroExpediente = numeroExpediente,
    fecha = fecha.toString(),
    hora = hora.toString(),
    localizacion = localizacion,
    numero = numero,
    cod_distrito = cod_distrito,
    distrito = distrito,
    tipoAccidente = tipoAccidente,
    estadoMeteorologico = estadoMeteorologico,
    tipoVehiculo = tipoVehiculo,
    tipoPersona = tipoPersona,
    rangoEdad = rangoEdad,
    sexo = sexo,
    codLesividad = codLesividad,
    lesividad = lesividad,
    coordenadaX = coordenadaX,
    coordenadaY = coordenadaY,
    positivoAlcohol = positivoAlcohol,
    positividadDroga = positividadDroga
)

fun AccidenteDto.toAccidente() = Accidente(
    numeroExpediente = this.numeroExpediente,
    fecha = toLocalDate(this.fecha),
    hora = toLocalTime(this.hora),
    localizacion = this.localizacion,
    numero = this.numero,
    cod_distrito = this.cod_distrito,
    distrito = this.distrito,
    tipoAccidente = this.tipoAccidente,
    estadoMeteorologico = this.estadoMeteorologico,
    tipoVehiculo = this.tipoVehiculo,
    tipoPersona = this.tipoPersona,
    rangoEdad = this.rangoEdad,
    sexo = this.sexo,
    codLesividad = this.codLesividad,
    lesividad = this.lesividad,
    coordenadaX = this.coordenadaX,
    coordenadaY = this.coordenadaY,
    positivoAlcohol = this.positivoAlcohol,
    positividadDroga = this.positividadDroga
)

fun AccidenteListDto.toAccidenteList() = accidentes.map {it.toAccidente()}

fun List<Accidente>.accidenteListToListOfDto() =AccidenteListDto(map { it.toDto() })