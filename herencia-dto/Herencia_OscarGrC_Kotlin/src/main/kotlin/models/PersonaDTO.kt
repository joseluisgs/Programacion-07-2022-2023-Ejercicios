package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "PersonaDTO")
    class PersonaDTO (@field:Element(name="tipo")var tipo:String,
                      @field:Element(name="modulo", required = false)var modulo:String?,
                      @field:Element(name="edad",required = false)var edad:String?,
                      @field:Element(name="nombre") override var nombre:String):Persona(){
                          constructor():this("","","","")
}