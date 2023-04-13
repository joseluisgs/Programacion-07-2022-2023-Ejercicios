package models

import models.enums.UserRol
import java.io.Serializable
import java.time.LocalDateTime

class Usuario(
    val id: Int = count++,
    val nombre: String,
    val apellido: String,
    val password: String,
    val rol: UserRol,
    val createAt: LocalDateTime = LocalDateTime.now()
): Serializable {
    companion object{
        var count = 0
    }

    override fun toString(): String {
        return "Usuario ($id) -> Nombre: $nombre ; Apellido: $apellido ; Password: $password ; Rol: $rol"
    }

    override fun hashCode(): Int {
        return nombre.hashCode() + apellido.hashCode() + password.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Usuario
                && other.nombre == this.nombre
                && other.apellido == this.apellido
                && other.password == this.password
    }
}