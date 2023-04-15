package mapper

import dto.InformesDto
import dto.MapaRegistrosDto
import dto.RegistroDto
import model.Registro
import utils.toLocalDate
import utils.toLocalTime
import java.time.LocalDate

fun Registro.toDto(): RegistroDto{
    return if(this.temperaturaMedia != null){
        RegistroDto(
            dia = this.dia.toString(),
            temperaturaMedia = this.temperaturaMedia.toString()
        )
    }else{
        if(this.temperaturaMax != null){
            RegistroDto(
                dia = this.dia.toString(),
                temperaturaMax = this.temperaturaMax.toString(),
                lugar = this.lugar,
                momento = this.momento.toString()
            )
        }else{
            if(this.temperaturaMin != null){
                RegistroDto(
                    dia = this.dia.toString(),
                    temperaturaMin = this.temperaturaMin.toString(),
                    lugar = this.lugar,
                    momento = this.momento.toString()
                )
            }else{
                RegistroDto(
                    dia = this.dia.toString(),
                    huboPrecipitacion = this.huboPrecipitacion,
                    precipitacion = this.precipitacion.toString()
                )
            }
        }
    }
}

fun RegistroDto.toRegistro(): Registro{
    return if(this.temperaturaMedia != null){
        Registro(
            dia = this.dia.toLocalDate(),
            temperaturaMedia = this.temperaturaMedia!!.toDouble()
        )
    }else{
        if(this.temperaturaMax != null){
            Registro(
                dia = this.dia.toLocalDate(),
                temperaturaMax = this.temperaturaMax.toDouble(),
                lugar = this.lugar,
                momento = this.momento!!.toLocalTime()
            )
        }else{
            if(this.temperaturaMin != null){
                Registro(
                    dia = this.dia.toLocalDate(),
                    temperaturaMin = this.temperaturaMin.toDouble(),
                    lugar = this.lugar,
                    momento = this.momento!!.toLocalTime()
                )
            }else{
                Registro(
                    dia = this.dia.toLocalDate(),
                    huboPrecipitacion = this.huboPrecipitacion,
                    precipitacion = this.precipitacion!!.toDouble()
                )
            }
        }
    }
}

fun List<Map<LocalDate, Registro>>.toDto(): InformesDto{
    return InformesDto(
        this.map { MapaRegistrosDto(it.mapKeys { it.key.toString() }.mapValues { it.value.toDto() }) }
    )
}

fun InformesDto.toRegistros(): List<Map<LocalDate, Registro>>{
    return this.conjuntoInformesDto.map { it.mapa_registros }.map { it.mapKeys { it.key.toLocalDate() }.mapValues { it.value.toRegistro() } }
}

