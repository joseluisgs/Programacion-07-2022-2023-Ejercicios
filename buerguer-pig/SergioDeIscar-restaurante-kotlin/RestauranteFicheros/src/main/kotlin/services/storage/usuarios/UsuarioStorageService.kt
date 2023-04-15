package services.storage.usuarios

import models.Usuario
import services.storage.StorageService
import java.time.LocalDateTime

interface UsuarioStorageService: StorageService<Usuario> {
    fun getUsuariosInDateRange(fechaInicio: LocalDateTime, fechaFin: LocalDateTime): List<Usuario>
}