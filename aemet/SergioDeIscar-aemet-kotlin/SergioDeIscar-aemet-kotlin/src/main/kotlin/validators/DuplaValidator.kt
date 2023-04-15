package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import errors.DuplaError
import models.Dupla
import java.time.LocalDate
import java.time.LocalTime

fun Dupla.validate(): Result<Dupla, DuplaError>{
    return when {
        poblacion.isBlank() -> Err(DuplaError.PoblacionError())
        provincia.isBlank() -> Err(DuplaError.ProvinciaError())
        temMax < -50 || temMax > 75 -> Err(DuplaError.TemMaxError()) // Por poner un ejemplo
        timeMax.isBefore(LocalTime.now()) -> Err(DuplaError.TimeMaxError())
        temMin < -50 || temMin > 75 -> Err(DuplaError.TemMinError())
        timeMin.isBefore(LocalTime.now()) -> Err(DuplaError.TimeMinError())
        precipitacion < 0 || precipitacion > 100 -> Err(DuplaError.PrecipitacionError())
        day.isBefore(LocalDate.now()) -> Err(DuplaError.DayError())
        else -> Ok(this)
    }
}