/*
 Crear repositorios y almacenamiento para:

Hamburguesa (id: UUID, nombre: String, List>, precio: calculado desde ingredientes))
Ingrediente (id: Autonumerico, nombre: String, precio: Double)
Crear almacenamiento y repositorio para leer y escribir Ingredientes y Hambuguesas:

Ficheros de texto
Ficheros binarios
Ficheros serializados
Ficheros CSV
Ficheros JSON
Ficheros XML
Adems obtener:

Hambuguesa más cara
Hambuguesa con más ingredientes
Número de Hambuguesas por ingrediente
Hambuguesaas agrupadas por total de ingredientes
Precio medio de las hambuguesas
Precio medio de los ingredientes.
 */

import repositories.HamburgesaRespository
import repositories.IngredienteRespository

fun main(){
    val logger = mu.KotlinLogging.logger {}
        logger.info { "Hola  Hamburguesa Pig " }
    fun Double.toDosDecimales():Double{
        var entrada = (this *100).toInt()
        return entrada.toDouble()/100
    }
    var repoIng = IngredienteRespository()
    var repoHam = HamburgesaRespository()

    println("       CONSULTAS")
    println("       ---------")
    logger.debug {"PRIMERA CONSULTA"}
    println("Hambuguesa más cara")
    var hambuguesasMasCara = repoHam.repositorioList.listaHamburguesas.maxBy { it.precio}
    println(hambuguesasMasCara.toString())
    logger.debug {"SEGUNDA CONSULTA"}
    println("Hambuguesa con más ingredientes")
    var hambuguesasMasIngredientes = repoHam.repositorioList.listaHamburguesas.maxBy { it.ingredientes.size}
    println(hambuguesasMasIngredientes.toString())
    logger.debug {"TERCERA CONSULTA"}
    println("Número de Hambuguesas por ingrediente")
     var mapaNumeroIngresHamburguesa = repoHam.repositorioList.listaHamburguesas
         .groupBy { it.ingredientes.size }.map { " NumeroHamburguesas:${it.value.size},NumeroIngredientes:${it.key}"+"\n"  }
    println(mapaNumeroIngresHamburguesa)
    logger.debug {"CUARTA CONSULTA"}
    println("Hambuguesas agrupadas por total de ingredientes")
    var mapHamburguesaNumeroIngredientes = repoHam.repositorioList.listaHamburguesas
        .groupBy { it.ingredientes.size }
    println(mapHamburguesaNumeroIngredientes)
    logger.debug {"QUINTA CONSULTA"}
    println("Precio medio de las hambuguesas")
    var precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio }.average()
    println(precioMedioHamburguesa)
    logger.debug {"SEXTA CONSULTA"}
    println("Precio medio de los ingredientes")
    var precioMedioIngredientes:Double = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()

    println(precioMedioIngredientes.toDosDecimales())

    logger.info {"Guardar en diferentes formatos"}
           repoHam.whriteText()
           repoHam.whriteBinario()
           repoHam.whiteSerializado()
           repoHam.whriteCSV()
           repoHam.whriteJSON()
           repoHam.whriteXML()
    logger.info {"Leer en diferentes formatos  REPOSITORIO HAMBURGUESA"}
    println("COMO TXT")
        repoHam.repositorioList= repoHam.readText()
    println("Precio medio de las hambuguesas")
     precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO BINARIO")
    repoHam.repositorioList= repoHam.readBinario()
    println("Precio medio de las hambuguesas")
    precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO SERIALIZABLE")
    repoHam.repositorioList= repoHam.readSerializado()
    println("Precio medio de las hambuguesas")
    precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO JSON")
    repoHam.repositorioList= repoHam.readJSON()
    println("Precio medio de las hambuguesas")
    precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO XML")
    repoHam.repositorioList= repoHam.readXML()
    println("Precio medio de las hambuguesas")
    precioMedioHamburguesa = repoHam.repositorioList.listaHamburguesas.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)

    logger.info {"Leer en diferentes formatos  REPOSITORIO INGREDIENTES"}
    //primero los creamos si no existen
    repoIng.whriteText()
    repoIng.whriteBinario()
    repoIng.whiteSerializado()
    repoIng.whriteCSV()
    repoIng.whriteJSON()
    repoIng.whriteXML()
    println("COMO TXT")
    repoIng.listaRepositorio= repoIng.readText()
    println("Precio medio de los ingredientes")
    precioMedioHamburguesa = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO BINARIO")
    repoIng.listaRepositorio= repoIng.readBinario()
    println("Precio medio de los ingredientes")
    precioMedioHamburguesa = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO SERIALIZABLE")
    repoIng.listaRepositorio= repoIng.readSerializado()
    println("Precio medio de los ingredientes")
    precioMedioHamburguesa = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO JSON")
    repoIng.listaRepositorio= repoIng.readJSON()
    println("Precio medio de los ingredientes")
    precioMedioHamburguesa = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    println("COMO XML")
    repoIng.listaRepositorio= repoIng.readXML()
    println("Precio medio de los ingredientes")
    precioMedioHamburguesa = repoIng.listaRepositorio.listaIngredientes.map { it.precio.toDouble() }.average()
    println(precioMedioHamburguesa)
    /*
    Codigo original para agregar los ingredientes lo dejo como nota

    repoIng.listaIngredientes.add(Ingrediente("Queso",7,50))
    repoIng.save()
    repoHam.listaHamburguesa.add(Hamburgesa("Alfa",1,
        listOf(
            repoIng.listaIngredientes[0],
            repoIng.listaIngredientes[1],
            repoIng.listaIngredientes[2],
            repoIng.listaIngredientes[3],
            repoIng.listaIngredientes[4],
            repoIng.listaIngredientes[5],
            repoIng.listaIngredientes[6])))
    repoHam.listaHamburguesa.add(Hamburgesa("Beta",2,
        listOf(
            repoIng.listaIngredientes[0],
            repoIng.listaIngredientes[3],
            repoIng.listaIngredientes[4],
            repoIng.listaIngredientes[5],
            repoIng.listaIngredientes[6],)))
    repoHam.listaHamburguesa.add(Hamburgesa("Delta",3,
        listOf(
            repoIng.listaIngredientes[0],
            repoIng.listaIngredientes[1],
            repoIng.listaIngredientes[2],
            repoIng.listaIngredientes[3],
            repoIng.listaIngredientes[3],
            repoIng.listaIngredientes[4],
            repoIng.listaIngredientes[5],
            repoIng.listaIngredientes[6])))
repoHam.saveAll()
*/
}