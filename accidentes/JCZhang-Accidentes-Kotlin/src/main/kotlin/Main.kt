import controllers.AccidentesController
import models.Accidente
import repositories.AccidentesRepository
import storage.service.AccidenteJsonService
import storage.service.AccidenteXmlService
import utils.toLocalDate
import utils.toLocalTime
import java.io.File
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.Month

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val repositorio = AccidentesRepository(AccidenteJsonService)
    val controller = AccidentesController(repositorio)

    //controller.save()
    //controller.load()
    val filePath =
        "${AccidenteXmlService.programPath}${File.separator}src${File.separator}main${File.separator}resources${File.separator}accidentes.csv"
    val accidentes = leerAccidentes(File(filePath))

    //Consultas


    //drugsAndAlcoholImplicatedAccidentes(accidentes)  //Accidentes en los que estan implicados alcohol o drogas.

    //numberOfDrugsAndAlcoholImplicatedAccidentes(accidentes)    //Numero de accidentes que han dado positivo en alcohol y drogas.

    //accidentsGroupedBySex(accidentes)    //Accidentes agrupados por sexo.

    //accidentsGroupedByMonth(accidentes)    //Accidentes agrupados por meses.

    //accidentsGroupedByVehicleType(accidentes)    //Accidentes agrupados por tipos de vehiculos.

    //accidentsOcurredInCalleLeganes(accidentes)    //Accidentes ocurridos en la calle de leganes.

    //numberOfAccidentsPerDistrit(accidentes)    //Numero de accidentes por distrito.

    //accidentsGroupedByDistritOrderedDescending(accidentes)    //Accidentes por distrito ordenadas de manera descendente.

    //listOfAccidentsOcurredAtWeekendAtNigh(accidentes)     //Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana)

    //listOfAccidentsOcurredAtWeekendAndDrugsImplicated(accidentes)   //Listado de accidentes que se produzcan en fin de semana y de noche donde se haya dado positivo en alcohol

    //accidentesWhereMoreThanOnePersonDied(accidentes)    //Accidentes donde haya mas de un fallecido.

    //checkIfDistritWithMoreAccidentsIsEqualToDistritWithMoreAccidentsAtWeekend(accidentes)       //Comprobar si el distrito con mas accidentes coincide con el distrito donde hay más accidentes en fin de semana.

    //numberOfAccidentsWhereImplicatedDrugsAlcocholAndPeopleDied(accidentes)      //Numero de accidentes donde ha habido (alcohol o drogas) y victimas mortales.

    //numberOfAccidentsWhereThereWherePeopleRunningOverOtherPeople(accidentes)      //Numero de accidentes donde ha habido atropellos

    //accidentsGroupedByMeteorologicalState(accidentes) //Accidentes agrupados por estado metereológico.

    listOfAccidentsWhereAnimalsWereRanOver(accidentes)        // Lista de accidentes ddonde ha habido un atropello animal
}

fun listOfAccidentsWhereAnimalsWereRanOver(accidentes: List<Accidente>): List<Accidente> {
    val listOfAccidentsWhereAnimalsWereRanOver = accidentes.filter { it.tipoAccidente.contains("Atropello a animal") }
    println(listOfAccidentsWhereAnimalsWereRanOver)
    return listOfAccidentsWhereAnimalsWereRanOver
}

fun accidentsGroupedByMeteorologicalState(accidentes: List<Accidente>): Map<String, List<Accidente>> {
    val accidentsGroupedByMeteorologicalState = accidentes.groupBy { it.estadoMeteorologico }
    println(accidentsGroupedByMeteorologicalState)
    return accidentsGroupedByMeteorologicalState
}

fun numberOfAccidentsWhereThereWherePeopleRunningOverOtherPeople(accidentes: List<Accidente>): Int {
    val numberOfAccidentsWhereThereWherePeopleRunningOverOtherPeople = accidentes.filter { it.tipoAccidente.contains("Atropello") }.count()
    println(numberOfAccidentsWhereThereWherePeopleRunningOverOtherPeople)
    return numberOfAccidentsWhereThereWherePeopleRunningOverOtherPeople
}

fun numberOfAccidentsWhereImplicatedDrugsAlcocholAndPeopleDied(accidentes: List<Accidente>): Int {
    val numberOfAccidentsWhereImplicatedDrugsAlcocholAndPeopleDied = accidentes.filter { it.lesividad.contains("Fallecido") && it.positivoAlcohol == "S" || it.positividadDroga=="1"}.count()
    println(numberOfAccidentsWhereImplicatedDrugsAlcocholAndPeopleDied)
    return numberOfAccidentsWhereImplicatedDrugsAlcocholAndPeopleDied
}

fun checkIfDistritWithMoreAccidentsIsEqualToDistritWithMoreAccidentsAtWeekend(accidentes: List<Accidente>) {
    val checkIfDistritWithMoreAccidentsIsEqualToDistritWithMoreAccidentsAtWeekend =
        accidentes.groupBy { it.distrito }.mapValues { it.value.size }.maxBy { it.value }.key
}

fun accidentesWhereMoreThanOnePersonDied(accidentes: List<Accidente>): List<Accidente> {
    val accidentesWhereMoreThanOnePersonDied = accidentes.filter { it.lesividad.contains("Fallecido") }
    println(accidentesWhereMoreThanOnePersonDied)
    return accidentesWhereMoreThanOnePersonDied
}

fun listOfAccidentsOcurredAtWeekendAndDrugsImplicated(accidentes: List<Accidente>): List<Accidente> {
    val listOfAccidentsOcurredAtWeekendAndDrugsImplicated = accidentes.filter {
        it.fecha.dayOfWeek in setOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        ) && it.positividadDroga == "1"
    }
    println(listOfAccidentsOcurredAtWeekendAndDrugsImplicated)
    return listOfAccidentsOcurredAtWeekendAndDrugsImplicated
}

fun listOfAccidentsOcurredAtWeekendAtNigh(accidentes: List<Accidente>): List<Accidente> {
    val listOfAccidentsOcurredAtWeekendAtNigh = accidentes.filter {
        it.hora.isBefore(LocalTime.of(6, 0)) && it.hora.isAfter(
            LocalTime.of(
                20,
                0
            )
        ) && it.fecha.dayOfWeek in setOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    }
    println(listOfAccidentsOcurredAtWeekendAtNigh)
    return listOfAccidentsOcurredAtWeekendAtNigh
}

fun accidentsGroupedByDistritOrderedDescending(accidentes: List<Accidente>): Map<String, List<Accidente>> {
    val accidentsGroupedByDistritOrderedDescending =
        accidentes.sortedByDescending { it.distrito }.groupBy { it.distrito }
    println(accidentsGroupedByDistritOrderedDescending)
    return accidentsGroupedByDistritOrderedDescending
}

fun numberOfAccidentsPerDistrit(accidentes: List<Accidente>): MutableMap<String, Int> {
    var numberOfAccidentsPerDistrit = mutableMapOf<String, Int>()
    for (accidente in accidentes) {
        if (numberOfAccidentsPerDistrit.containsKey(accidente.distrito)) {
            numberOfAccidentsPerDistrit[accidente.distrito] =
                numberOfAccidentsPerDistrit[accidente.distrito]!! + 1
        } else {
            numberOfAccidentsPerDistrit[accidente.distrito] = 1
        }
    }
    println(numberOfAccidentsPerDistrit)
    return numberOfAccidentsPerDistrit
}

fun accidentsOcurredInCalleLeganes(accidentes: List<Accidente>): List<Accidente> {
    val accidentsOcurredInCalleLeganes = accidentes.filter { it.localizacion == "CALL. DE LEGANES" }
    println(accidentsOcurredInCalleLeganes)
    return accidentsOcurredInCalleLeganes
}

fun accidentsGroupedByVehicleType(accidentes: List<Accidente>): Map<String, List<Accidente>> {
    val accidentsGroupedByVehicleType = accidentes.groupBy { it.tipoVehiculo }
    println(accidentsGroupedByVehicleType)
    return accidentsGroupedByVehicleType
}

fun accidentsGroupedByMonth(accidentes: List<Accidente>): Map<Month, List<Accidente>> {
    val accidentsGroupedByMonth = accidentes.groupBy { it.fecha.month }
    println(accidentsGroupedByMonth)
    return accidentsGroupedByMonth
}

fun accidentsGroupedBySex(accidentes: List<Accidente>): Map<String, List<Accidente>> {
    val accidentsGroupedBySex = accidentes.groupBy { it.sexo }
    println(accidentsGroupedBySex)
    return accidentsGroupedBySex
}

fun numberOfDrugsAndAlcoholImplicatedAccidentes(accidentes: List<Accidente>): Int {
    val drugsAndAlcoholImplicatedAccidentes =
        accidentes.filter { it.positivoAlcohol == "S" || it.positividadDroga == "1" }.count()
    println(drugsAndAlcoholImplicatedAccidentes)
    return drugsAndAlcoholImplicatedAccidentes
}

fun drugsAndAlcoholImplicatedAccidentes(accidentes: List<Accidente>): List<Accidente> {
    val drugsAndAlcoholImplicatedAccidentes =
        accidentes.filter { it.positivoAlcohol == "S" || it.positividadDroga == "1" }
    println(drugsAndAlcoholImplicatedAccidentes)
    return drugsAndAlcoholImplicatedAccidentes
}


fun leerAccidentes(file: File) =
    file.readLines().drop(1)
        .map { it.split(";") }
        .map { it -> it.map { it.trim() } }
        .map { campos ->
            Accidente(
                numeroExpediente = campos[0],
                fecha = toLocalDate(campos[1]),
                hora = toLocalTime(campos[2]),
                localizacion = campos[3],
                numero = campos[4],
                cod_distrito = campos[5],
                distrito = campos[6],
                tipoAccidente = campos[7],
                estadoMeteorologico = campos[8],
                tipoVehiculo = campos[9],
                tipoPersona = campos[10],
                rangoEdad = campos[11],
                sexo = campos[12],
                codLesividad = campos[13],
                lesividad = campos[14],
                coordenadaX = campos[15],
                coordenadaY = campos[16],
                positivoAlcohol = campos[17],
                positividadDroga = campos[18]
            )
        }