package models


import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
@Root(name= "Ingrediente")
data class Ingrediente(
            @field:Element(name = "nombre")var nombre:String,
            @field:Element(name = "id")var id:String ,
            @field:Element(name = "precio")var precio:String,
            @field:Element(name = "fechaCreacion") var fechaCreacion:String = LocalTime.now().toString()
                .substring(startIndex = 0, endIndex = 8)
    ):Serializable {
    constructor() : this("", "", "", "")
override fun toString():String{
    return "Soy $nombre tengo id $id y valgo mucho pero estos dicen que $precio"
}
}