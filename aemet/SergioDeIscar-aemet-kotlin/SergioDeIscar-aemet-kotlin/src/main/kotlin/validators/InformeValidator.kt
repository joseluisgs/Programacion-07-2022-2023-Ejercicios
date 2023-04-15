package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import errors.InformeError
import models.Informe
import java.time.LocalDate
import java.time.LocalTime

fun Informe.validate(): Result<Informe, InformeError>{
    return when{
        day.isBefore(LocalDate.now()) -> Err(InformeError.DayError())
        temMedia < -50 || temMedia > 75 -> Err(InformeError.TemMediaError())

        temMax.third < -50 || temMax.third > 75 -> Err(InformeError.TemMaxError())
        temMin.third < -50 || temMin.third > 75 -> Err(InformeError.TemMinError())

        temMax.second.isBefore(LocalTime.now()) -> Err(InformeError.TemMaxTimeError())
        temMin.second.isBefore(LocalTime.now()) -> Err(InformeError.TemMinTimeError())

        temMax.first.isBlank() -> Err(InformeError.TemMaxPoblacionError())
        temMin.first.isBlank() -> Err(InformeError.TemMinPoblacionError())

        precipitacion.second < 0 || precipitacion.second > 100 -> Err(InformeError.PrecipitacionError())

        else -> Ok(this)
    }
}