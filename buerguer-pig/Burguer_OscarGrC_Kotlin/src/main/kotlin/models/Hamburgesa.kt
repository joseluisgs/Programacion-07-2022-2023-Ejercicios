package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable
import java.util.UUID
@Root(name = "HamburgesaRespository")
data class Hamburgesa (
    @field:Element(name = "nombre")                           var nombre:String,
    @field:Element(name = "id")                               var id:String,
    @field:ElementList(name = "ingredientes", inline = true)
    @param:ElementList(name = "ingredientes", inline = true)  var ingredientes:MutableList<Ingrediente>):Serializable{
    constructor() : this("", "", mutableListOf())
      val precio: Double
      // esto lo pongo asi por que me dio un fallo con XML (el precio aparecia como 0.0)
      get() = ingredientes.sumOf { it.precio.toDouble() }

    override fun toString():String{
        return "Soy $nombre con id:$id y tengo ${ingredientes.map { it.nombre }}"
    }
}