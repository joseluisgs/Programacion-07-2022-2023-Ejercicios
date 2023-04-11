import model.Medicion
import model.ObjetoDeExposicion
import model.Registro
import storage.medicion.MedicionStorageServiceCsv
import storage.medicion.MedicionStorageServiceJson
import storage.medicion.MedicionStorageServiceXml
import storage.registros.RegistrosStorageServiceJson
import storage.registros.RegistrosStorageServiceXml
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@OptIn(ExperimentalStdlibApi::class)
fun main(args: Array<String>) {
    // Este es el método que use para sacar la fecha según el nombre del fichero:
    // val fecha = "Aemet20171029.csv".removePrefix("Aemet").removeSuffix(".csv")
    // println(Triple(fecha.substring(0,4).toInt(), fecha.substring(4,6).toInt(), fecha.substring(6,8).toInt()))

    // Consigo una lista con todos los nombres de los archivos .csv en la carpeta resources.
    val path = ClassLoader.getSystemResource("config.properties").path.removeSuffix("config.properties")
    val file = File(path)
    val listaFicherosResources = file.listFiles().filter { it.name.contains(".csv") }
    listaFicherosResources.forEach { println(it) }

    // Junto todas las colleciones en una sola
    val medicionesTotales = mutableListOf<Medicion>()

    listaFicherosResources.forEach {
        medicionesTotales.addAll(
            leerFicheroCsv(it.name)
        )
    }

    medicionesTotales.toList()

    val mapaMedicionesTotalesPorDia = medicionesTotales.groupBy { it.dia }

    // enFicheroJson(mapaMedicionesTotalesPorDia)

    // enFicheroXml(mapaMedicionesTotalesPorDia)

    consultas(mapaMedicionesTotalesPorDia, medicionesTotales)

    // registros(medicionesTotales)

}

@OptIn(ExperimentalStdlibApi::class)
private fun registros(medicionesTotales: MutableList<Medicion>) {
    println("Empezamos con los registros, sobre Madrid unicamente, para los ficheros JSON y XML:")
    val medicionesProvinciaMadrid =
        medicionesTotales.filter { it.provincia.trim().lowercase() == "madrid" }.groupBy { it.dia }

    println("Temperatura media:")
    val temperaturaMedia =
        medicionesProvinciaMadrid.mapValues { it.value.map { ((it.temperaturaMin + it.temperaturaMax) / 2) }.average() }
            .mapValues {
                Registro(
                    dia = it.key,
                    temperaturaMedia = it.value
                )
            }
    temperaturaMedia.keys.forEach { key ->
        println("[$key]--${temperaturaMedia[key]}")
    }

    println()

    println("Temperatura maxima:")
    val temperaturaMaxima = medicionesProvinciaMadrid.mapValues {
        Registro(
            dia = it.key,
            temperaturaMax = it.value.map { it.temperaturaMax }.max(),
            lugar = it.value.maxBy { it.temperaturaMax }.poblacion,
            momento = it.value.maxBy { it.temperaturaMax }.horaTempMax
        )
    }
    temperaturaMaxima.keys.forEach { key ->
        println("[$key]--${temperaturaMaxima[key]}")
    }

    println()

    println("Temperatura minima:")
    val temperaturaMinima = medicionesProvinciaMadrid.mapValues {
        Registro(
            dia = it.key,
            temperaturaMin = it.value.map { it.temperaturaMin }.min(),
            lugar = it.value.maxBy { it.temperaturaMin }.poblacion,
            momento = it.value.maxBy { it.temperaturaMin }.horaTempMax
        )
    }
    temperaturaMinima.keys.forEach { key ->
        println("[$key]--${temperaturaMinima[key]}")
    }

    println()

    println("Hubo o no precipitacion y el valor de la misma:")
    val precipitaciones = medicionesProvinciaMadrid.mapValues {
        Registro(
            dia = it.key,
            huboPrecipitacion = if (it.value.map { it.precipitacion }.max() > 0.0) "Si" else "No",
            precipitacion = it.value.map { it.precipitacion }.max()
        )
    }
    precipitaciones.keys.forEach { key ->
        println("[$key]--${precipitaciones[key]}")
    }

    println()

    val conjuntoDeRegistros = listOf<Map<LocalDate, Registro>>(temperaturaMedia, temperaturaMaxima, temperaturaMinima, precipitaciones)

    println("Los guardamos en el fichero JSON:")

    val storageJson = RegistrosStorageServiceJson()

    storageJson.saveAll(conjuntoDeRegistros)

    storageJson.loadAll().forEach { println(it) }

    println()

    println("Los guardamos en el fichero XML:")

    val storageXml = RegistrosStorageServiceXml()

    storageXml.saveAll(conjuntoDeRegistros)

    storageXml.loadAll().forEach { println(it) }
}

private fun consultas(
    mapaMedicionesTotalesPorDia: Map<LocalDate, List<Medicion>>,
    medicionesTotales: MutableList<Medicion>
) {
    println("Empezamos con las consultas:")
    println()

    println("Temperatura maxima por dia y lugar:")
    val mapa1 = mapaMedicionesTotalesPorDia
        .mapValues {
            it.value.groupBy { it.poblacion }
                .mapValues { it.value.map { it.temperaturaMax }.max() }
        }
    mapa1.keys.take(1).forEach { key ->
        mapa1[key]!!.keys.take(10).forEach { fecha ->
            println("[$key | $fecha]--${mapa1[key]!![fecha]}")
        }
    }

    println()

    println("Temperatura min por dia y lugar:")
    val mapa2 = mapaMedicionesTotalesPorDia
        .mapValues {
            it.value.groupBy { it.poblacion }
                .mapValues { it.value.map { it.temperaturaMin }.min() }
        }
    mapa2.keys.take(1).forEach { key ->
        mapa2[key]!!.keys.take(10).forEach { fecha ->
            println("[$key | $fecha]--${mapa2[key]!![fecha]}")
        }
    }

    println()

    val mapaMedicionesTotalesPorProvincia = medicionesTotales.groupBy { it.provincia }

    println("Temperatura max por provincia:")
    val mapa3 = mapaMedicionesTotalesPorProvincia
        .mapValues {
            it.value.maxOf { it.temperaturaMax }
        }
    mapa3.keys.take(10).forEach { key ->
        println("[$key]--${mapa3[key]}")
    }

    println()

    println("Temperatura min por provincia:")
    val mapa4 = mapaMedicionesTotalesPorProvincia
        .mapValues {
            it.value.minOf { it.temperaturaMin }
        }
    mapa4.keys.take(10).forEach { key ->
        println("[$key]--${mapa4[key]}")
    }

    println()

    println("Temperatura media por provincia:")
    val mapaConMediasDeTemperatura = mapaMedicionesTotalesPorProvincia
        .mapValues { it.value.map { (it.temperaturaMax+it.temperaturaMin)/2 }.average() }
    val mapa5 = mapaMedicionesTotalesPorProvincia
        .mapValues {
            it.value.map {
                ObjetoDeExposicion(
                    it.dia,
                    it.poblacion,
                    mapaConMediasDeTemperatura[it.provincia]!!
                )
            }.maxBy { it.valor }
        }
    mapa5.keys.take(10).forEach { key ->
        println("[$key]--${mapa5[key]}")
    }

    println()

    println("Lista de precipitacion media por dia y provincia:")
    val mapa11 = medicionesTotales
        .groupBy { it.dia }.mapValues { it.value.groupBy { it.provincia }.mapValues { it.value.map { it.precipitacion }.average() } }
    mapa11.keys.take(1).forEach { key ->
        mapa11[key]!!.keys.take(10).forEach { fecha ->
            println("[$key | $fecha]--${mapa11[key]!![fecha]}")
        }
    }

    println()

    println("Numero de lugares en el que llovio por dia y provincia:")
    val mapa6 = medicionesTotales
        .filter { it.precipitacion > 0.0 }
        .groupBy { it.dia }.mapValues { it.value.groupBy { it.provincia }.mapValues { it.value.size } }
    mapa6.keys.take(1).forEach { key ->
        mapa6[key]!!.keys.take(10).forEach { fecha ->
            println("[$key | $fecha]--${mapa6[key]!![fecha]}")
        }
    }

    println()

    println("Temperatura media de la provincia de Madrid:")
    val decimal1 = medicionesTotales.filter { it.provincia.trim().lowercase() == "madrid" }
        .map { ((it.temperaturaMax + it.temperaturaMin) / 2) }.average()
    println(decimal1)

    println()

    println("Media de temperatura maxima total:")
    val decimal2 = medicionesTotales.map { it.temperaturaMax }.average()
    println(decimal2)

    println()

    println("Media de temperatura minima total:")
    val decimal3 = medicionesTotales.map { it.temperaturaMin }.average()
    println(decimal3)

    println()

    println("Lugares donde la temperatura maxima a sido captada antes de las 15:00, por día:")
    val mapa7 = medicionesTotales.filter { it.horaTempMax.isBefore(LocalTime.of(15, 0, 0)) }
        .groupBy { it.dia }
        .mapValues { it.value.map { it.poblacion }.toSet() }
    mapa7.keys.forEach { key ->
        println("[$key]--${mapa7[key]}")
    }

    println()

    println("Lugares donde la temperatura minima a sido captada despues de las 17:30, por día:")
    val mapa8 = medicionesTotales.filter { it.horaTempMin.isAfter(LocalTime.of(17, 30, 0)) }
        .groupBy { it.dia }
        .mapValues { it.value.map { it}.toSet() }
    mapa8.keys.forEach { key ->
        println("[$key]--${mapa8[key]}")
    }

    println()
}

@OptIn(ExperimentalStdlibApi::class)
private fun enFicheroJson(mapaMedicionesTotalesPorDia: Map<LocalDate, List<Medicion>>) {
    println("Procedemos, a pasarlo todo a un fichero JSON:")
    val storageJson = MedicionStorageServiceJson()

    storageJson.saveAll(mapaMedicionesTotalesPorDia)

    val medicionesJson = storageJson.loadAll()
    medicionesJson.keys.forEach { key ->
        println("[$key]--${medicionesJson[key]}")
    }

    println()
}

private fun enFicheroXml(mapaMedicionesTotalesPorDia: Map<LocalDate, List<Medicion>>) {
    println("Procedemos, a pasarlo todo a un fichero XML:")
    val storageXml = MedicionStorageServiceXml()

    storageXml.saveAll(mapaMedicionesTotalesPorDia)

    val medicionesXml = storageXml.loadAll()
    medicionesXml.keys.forEach { key ->
        println("[$key]--${medicionesXml[key]}")
    }

    println()
}

fun leerFicheroCsv(name: String): List<Medicion> {
    val storageCsv = MedicionStorageServiceCsv(name)
    return storageCsv.loadAll()
}
