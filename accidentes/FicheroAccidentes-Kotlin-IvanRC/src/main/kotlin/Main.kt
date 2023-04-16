import model.ConjuntoDeAccidentes
import storage.accidentes.AccidenteStorageServiceCSV
import storage.accidentes.AccidenteStorageServiceJSON
import storage.accidentes.AccidentesStorageServiceXML
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

fun main(args: Array<String>) {

    // Unas pruebas con respecto a un problema que tuve para poder trasformar un String a Doble, las dos son posibles soluciones al problema:
    // Exception in thread "main" java.lang.NumberFormatException: For input string: ""34.5""
    // Primera solución: println("\"34.5\"".removePrefix("\"").removeSuffix("\"").toDouble())
    // Segunda solución: println("\"34.5\"".replace("\"", "").toDouble())

    val storageCSV = AccidenteStorageServiceCSV()
    val accidentes = storageCSV.loadAll()

    accidentes.take(10).forEach { println(it) }

    // println()

    // val conjuntoDeAccidentes = ConjuntoDeAccidentes(accidentes)

    // almacenarEnUnFicheroJSON(conjuntoDeAccidentes)

    // println()

    // almacenarEnUnFicheroXML(conjuntoDeAccidentes)

    println("Empezamos con las diferentes consultas:")
    println()

    println("Accidentes en los que estan implicados alcohol o drogas:")
    val entero1 = accidentes.filter { it.esPositivaEnAlchol || it.esPositivaEnDrogas }.size
    println(entero1)
    println()

    println("Numero de accidentes que han dado positivo en alcohol y drogas:")
    val entero2 = accidentes.filter { it.esPositivaEnAlchol && it.esPositivaEnDrogas }.size
    println(entero2)
    println()

    println("Accidentes agrupados por sexo:")
    val mapa1 = accidentes.groupBy { it.sexo }.mapValues { it.value.map { it }.size }
    mapa1.keys.forEach{
        println("$it--${mapa1[it]}")
    }
    println()

    println("Accidentes agrupados por meses:")
    val mapa2 = accidentes.groupBy { it.fecha.month }.mapValues { it.value.map { it }.size }
    mapa2.keys.forEach{
        println("$it--${mapa2[it]}")
    }
    println()

    println("Accidentes agrupados por tipos de vehiculos:")
    val mapa3 = accidentes.groupBy { it.tipoDeVehiculo }.mapValues { it.value.map { it }.size }
    mapa3.keys.forEach{
        println("$it--${mapa3[it]}")
    }
    println()

    println("Accidentes ocurridos en la calle de leganes:")
    val entero3 = accidentes.filter { it.localizacion.filter { it.lowercase().contains("leganes") }.size >= 1 }.size
    println(entero3)
    println()

    println("Numero de accidentes por distrito:")
    val mapa4 = accidentes.groupBy { it.distrito.nombre }.mapValues { it.value.map { it }.size }
    mapa4.keys.forEach{
        println("$it--${mapa4[it]}")
    }
    println()

    println("Estadisticas por distrito:")
    val totalAccidentes = accidentes.size
    val mapa5 = accidentes.groupBy { it.distrito.nombre }.mapValues { (it.value.map { it }.size * 100).toDouble() / totalAccidentes }
    mapa5.keys.forEach{
        println("$it--${String.format("%.2f", mapa5[it])}%")
    }
    println()

    println("Accidentes por distrito ordenadas de manera descendente:")
    val mapa6 = accidentes.groupBy { it.distrito.nombre }.mapValues { it.value.map { it }.size }.toSortedMap(reverseOrder())
    mapa6.keys.forEach{
        println("$it--${mapa6[it]}")
    }
    println()

    println("Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana):")
    val lista1 = accidentes
        .filter {
            it.hora !in (LocalTime.of(6,0,0)..LocalTime.of(20,0,0)) &&
            it.fecha.dayOfWeek in (DayOfWeek.FRIDAY .. DayOfWeek.SUNDAY)
        }
    println(lista1.take(10).joinToString(separator = "\n"))
    println()

    println("Listado de accidentes que se produzcan en fin de semana y de noche donde se haya dado positivo en alchol:")
    val lista2 = accidentes
        .filter {
            it.hora !in (LocalTime.of(6,0,0)..LocalTime.of(20,0,0)) &&
                    it.fecha.dayOfWeek in (DayOfWeek.FRIDAY .. DayOfWeek.SUNDAY) &&
                    it.esPositivaEnAlchol
        }
    println(lista2.take(10).joinToString(separator = "\n"))
    println()

    println("Accidentes donde haya mas de un fallecido:")
    // Lo único que he conseguido es sacar la info de que hay fallecidos, pero no tengo idea de como sacar la info de cuantos fallecidos hay exactamente
    val lista3 = accidentes
        .filter {
            it.lesividad.lesividad?.lowercase()?.contains("fallecido") ?: false
        }
    println(lista3.take(10).joinToString(separator = "\n"))
    println()

    println("Comprobar si el distrito con mas accidentes coincide con el distrito donde hay más accidentes en fin de semana:")
    println("Distrito con mas accidentes en general:")
    val distrito1 = accidentes
        .groupBy { it.distrito }.mapValues { it.value.size }.maxBy { it.value }.key
    println(distrito1)
    println("Distrito con mas accidentes durante los fines de semana:")
    val distrito2 = accidentes
        .filter { it.fecha.dayOfWeek in (DayOfWeek.FRIDAY .. DayOfWeek.SUNDAY) }
        .groupBy { it.distrito }.mapValues { it.value.size }.maxBy { it.value }.key
    println(distrito2)
    if(distrito1 == distrito2){
        println("Como podemos ver, si son el mismo.")
    }else{
        println("Como podemos ver, no son el mismo.")
    }
    println()

    println("Numero de accidentes donde ha habido (alcohol o drogas) y victimas mortales:")
    val entero4 = accidentes
        .filter {
            (it.esPositivaEnDrogas || it.esPositivaEnAlchol) &&
            it.lesividad.lesividad?.lowercase()?.contains("fallecido") ?: false
        }.size
    println(entero4)
    println()

    println("Numero de accidentes donde ha habido atropellos a personas:")
    val entero5 = accidentes
        .filter {
            it.tipoAccidente.lowercase().contains("atropello") &&
                    it.tipoAccidente.lowercase().contains("persona")
        }.size
    println(entero5)
    println()

    println("Accidentes agrupados por estado metereológio:")
    val mapa7 = accidentes.groupBy { it.estadoMeteorologico }.mapValues { it.value.map { it }.size }
    mapa7.keys.forEach{
        println("$it--${mapa7[it]}")
    }
    println()

    println("Lista de accidentes donde haya habido un atropello animal:")
    val lista4 = accidentes
        .filter {
            it.tipoAccidente.lowercase().contains("atropello") &&
                    it.tipoAccidente.lowercase().contains("animal")
        }
    println(lista4.take(10).joinToString(separator = "\n"))
    println()
}


fun almacenarEnUnFicheroXML(conjuntoDeAccidentes: ConjuntoDeAccidentes) {
    val storageXML = AccidentesStorageServiceXML()

    storageXML.saveAll(conjuntoDeAccidentes.accidentes)

    val accidentes = storageXML.loadAll()

    accidentes.take(10).forEach { println(it) }
}

@OptIn(ExperimentalStdlibApi::class)
fun almacenarEnUnFicheroJSON(conjuntoDeAccidentes: ConjuntoDeAccidentes) {
    val storageJSON = AccidenteStorageServiceJSON()

    storageJSON.saveAll(conjuntoDeAccidentes.accidentes)

    val accidentes = storageJSON.loadAll()

    accidentes.take(10).forEach { println(it) }
}
