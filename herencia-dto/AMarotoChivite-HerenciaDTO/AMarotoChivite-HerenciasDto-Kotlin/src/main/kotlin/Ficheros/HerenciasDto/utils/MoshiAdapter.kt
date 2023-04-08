package Ficheros.HerenciasDto.utils

import Ficheros.HerenciasDto.models.dto.PersonDTO
import com.squareup.moshi.*

class PersonAdapterFinal : JsonAdapter<PersonDTO>() {

    @FromJson
    override fun fromJson(reader: JsonReader): PersonDTO? {
        val jsonObject = reader.readJsonValue() as Map<*, *>
        val name = jsonObject.get("name")?.toString() ?: return null
        val edad = jsonObject.get("edad")?.toString() ?: return null

        return when (jsonObject.get("type")?.toString()) {
            "Alumno" -> {
                PersonDTO.Alumno(name, edad)
            }

            "Profesor" -> {
                val profesorObject = jsonObject.get("profesor") as Map<*, *>
                val modulo = profesorObject.get("modulo")?.toString() ?: return null
                PersonDTO.Profesor(name, edad, modulo)
            }

            else -> null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: PersonDTO?) {
        if (value == null) {
            writer.nullValue()
            return
        }

        writer.beginObject()
        writer.name("name").value(value.name)
        writer.name("edad").value(value.edad)
        writer.name("type").value(value.type)

        when (value) {
            is PersonDTO.Alumno -> {
                writer.name("alumno").beginObject()
                writer.endObject()
            }

            is PersonDTO.Profesor -> {
                writer.name("profesor").beginObject()
                writer.name("modulo").value(value.modulo)
                writer.endObject()
            }
        }
        writer.endObject()
    }
}


