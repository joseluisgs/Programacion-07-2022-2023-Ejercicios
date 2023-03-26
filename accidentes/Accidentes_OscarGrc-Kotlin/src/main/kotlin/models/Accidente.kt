package models

class Accidente (
    val num_expediente:String,
    val fecha:String,
    val hora:String,
    val localizacion:String,
    val numero:String,
    val cod_distrito:String,
    val distrito:String,
    val tipo_accidente:String,
    val estado_meteorológico:String,
    val tipo_vehiculo:String,
    val tipo_persona:String,
    val rango_edad:String,
    val sexo:String,
    val cod_lesividad:String,
    val lesividad:String,
    val coordenada_x_utm:String,
    val coordenada_y_utm:String,
    val positiva_alcohol:String,
    val positiva_droga:String
){
    override fun toString(): String {
        return  "num_expediente:$num_expediente, tipoAccidente:$tipo_accidente " +
                " fecha:$fecha, hora:$hora, localizacion:$localizacion, " +
                "numero:$numero,cod_distrito:$cod_distrito,distrito:$distrito, "+
                "estado_meteorológico:$estado_meteorológico, tipo_vehiculo:$tipo_vehiculo, tipo_persona:$tipo_persona" +
                "rango_edad:$rango_edad, sexo:$sexo, cod_lesividad:$cod_lesividad, lesividad:$lesividad, " +
                "coordenada_x: $coordenada_x_utm, coordenada_y:$coordenada_y_utm,positiva_alcohol:$positiva_alcohol, " +
                "positiva_droga:$positiva_droga \n"
    }
}