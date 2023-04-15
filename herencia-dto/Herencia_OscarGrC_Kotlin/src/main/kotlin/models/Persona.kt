package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable


abstract class Persona(open var nombre:String=""):Serializable {

}