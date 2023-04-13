package services.storage.json_adapters

import com.squareup.moshi.*
import models.Pedido
import java.time.LocalDateTime

class LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDateTime? = LocalDateTime.parse(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        writer.jsonValue(value.toString())
    }
}

/*class PedidoAdapter : JsonAdapter<Pedido>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Pedido? = Pedido.parse(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: Pedido?) {
        writer.jsonValue(value.toString())
    }
}*/
