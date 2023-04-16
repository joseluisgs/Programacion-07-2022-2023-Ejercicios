package models

import java.time.LocalDate
import java.time.LocalTime

//si la clave es autonumerica
// RETURN.GENERATED.KEY

class Accidente(
    val numeroExpediente: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val localizacion: String,
    val numero: String,
    val cod_distrito: String,
    val distrito: String,
    val tipoAccidente: String,
    val estadoMeteorologico: String,
    val tipoVehiculo: String,
    val tipoPersona: String,
    val rangoEdad: String,
    val sexo: String,
    val codLesividad: String,
    val lesividad: String,
    val coordenadaX: String,
    val coordenadaY: String,
    val positivoAlcohol: String,
    val positividadDroga: String,
) {
    override fun toString(): String {
        return "Accidente(numeroExpediente=$numeroExpediente, fecha=$fecha, hora=$hora, localizacion='$localizacion', numero=$numero, cod_distrito=$cod_distrito, distrito='$distrito', tipoAccidente='$tipoAccidente', estadoMeteorologico='$estadoMeteorologico', tipoVehiculo='$tipoVehiculo', tipoPersona='$tipoPersona', randoEdad='$rangoEdad', sexo='$sexo', codLesividad='$codLesividad', lesividad='$lesividad', coordenadaX=$coordenadaX, coordenadaY=$coordenadaY, positivoAlcohol='$positivoAlcohol', positividadDroga='$positividadDroga')"
    }
}



