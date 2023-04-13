package models

import com.squareup.moshi.JsonClass
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable
@Root(name = "registroGeneralRDRM")
class RegistroGeneralRDRM(): Serializable
{

    @field:ElementList(inline = true, required = false)
    var registro:MutableList<RegistroDiarioRM>  = mutableListOf()
}