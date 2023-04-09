package utils

import com.squareup.moshi.*
import java.util.*


class UuidAdapter : JsonAdapter<UUID>() {
    @FromJson
    override fun fromJson(reader: JsonReader): UUID? = UUID.fromString(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: UUID?) {
        writer.jsonValue(value.toString())
    }
}

fun <T>JsonAdapter<T>.toPrettyJson(value:T) = this.indent("  ").toJson(value)