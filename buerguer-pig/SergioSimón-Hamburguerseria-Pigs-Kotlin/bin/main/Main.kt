import controller.HamburguesasController
import controller.IngredientesController
import factories.createHamburguesaRandom
import factories.createIngredienteRandom
import locale.toLocalMoney
import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import repositories.hamburguesas.hamburguesasRepositoryList
import repositories.ingredientes.IngredientesRepositoryList
import service.storage.hamburguesas.binario.hamburguesasFicheroBinarioService
import service.storage.hamburguesas.csv.hamburguesasFicheroCsvService
import service.storage.hamburguesas.json.hamburguesasFicheroJson
import service.storage.hamburguesas.serializable.hamburguesasFicheroSerializableService
import service.storage.hamburguesas.text.hamburguesasFicheroTextoService
import service.storage.hamburguesas.xml.hamburguesasFicheroXmlService
import service.storage.ingredientes.binario.IngredientesFicheroBinarioService
import service.storage.ingredientes.csv.IngredientesFicheroCsvService
import service.storage.ingredientes.json.IngredientesFicheroJsonService
import service.storage.ingredientes.serializable.IngredientesFicheroSerializableService
import service.storage.ingredientes.text.IngredientesFicheroTextoService
import service.storage.ingredientes.xml.IngredientesFicheroXmlService
import java.util.*
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    consultas()
    menuPrincipal()
}

fun menuPrincipal() {
    var opcion: Int
    val menuFicheros = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                     MENÚ PRINCIPAL                       |
       |                                                          |
       | 1. Trabajar con ficheros Binarios                        |
       | 2. Trabajar con ficheros Csv                             |
       | 3. Trabajar con ficheros JSon                            |
       | 4. Trabajar con ficheros Serializables                   |
       | 5. Trabajar con ficheros de Texto                        |
       | 6. Trabajar con ficheros de Xml                          |
       | 7. Salir                                                 |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/
   
    """.trimIndent()

    do {
        println(menuFicheros)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> menuBinario()
            2 -> menuCSV()
            3 -> menuJson()
            4 -> menuSerializable()
            5 -> menuTexto()
            6 -> menuXML()
            7 -> println("Tenga un buen día")
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 7)
    exitProcess(0)
}

fun menuBinario() {
    var opcion: Int
    val menuCSV = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                      MENÚ BINARIO                        |
       |                                                          |
       | 1. Crear ingrediente en un fichero binario               |
       | 2. Leer ingrediente en un fichero binario                |
       | 3. Crear hamburguesas en un fichero binario              |
       | 4. Leer hamburguesas en un fichero binario               |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuCSV)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesBinario()
            2 -> leerIngredientesBinario()
            3 -> crearHamburguesasBinario()
            4 -> leerHamburguesasBinario()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

fun menuCSV() {
    var opcion: Int
    val menuCSV = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                      MENÚ CSV                            |
       |                                                          |
       | 1. Crear ingrediente en un fichero CSV                   |
       | 2. Leer ingrediente en un fichero CSV                    |
       | 3. Crear hamburguesas en un fichero CSV                  |
       | 4. Leer hamburguesas en un fichero CSV                   |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuCSV)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesCSV()
            2 -> leerIngredientesCSV()
            3 -> crearHamburguesasCSV()
            4 -> leerHamburguesasCSV()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

@OptIn(ExperimentalStdlibApi::class)
fun menuJson() {
    var opcion: Int
    val menuJson = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                      MENÚ Json                           |
       |                                                          |
       | 1. Crear ingrediente en un fichero Json                  |
       | 2. Leer ingrediente en un fichero Json                   |
       | 3. Crear hamburguesas en un fichero Json                 |
       | 4. Leer hamburguesas en un fichero Json                  |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuJson)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesJson()
            2 -> leerIngredientesJson()
            3 -> crearHamburguesasJson()
            4 -> leerHamburguesasJson()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

fun menuSerializable() {
    var opcion: Int
    val menuSerializable = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                  MENÚ serializable                       |
       |                                                          |
       | 1. Crear ingredientes en un fichero serializable         |
       | 2. Leer  ingredientes en un fichero serializable         |
       | 3. Crear hamburguesas en un fichero serializable         |
       | 4. Leer  hamburguesas en un fichero serializable         |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuSerializable)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesSerializable()
            2 -> leerIngredientesSerializable()
            3 -> crearHamburguesasSerializable()
            4 -> leerHamburguesaSerializable()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

fun menuTexto() {
    var opcion: Int
    val menuText = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                  MENÚ de Texto                           |
       |                                                          |
       | 1. Crear ingredientes en un fichero de texto             |
       | 2. Leer  ingredientes en un fichero de texto             |
       | 3. Crear hamburguesas en un fichero de texto             |
       | 4. Leer  hamburguesas en un fichero de texto             |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuText)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesText()
            2 -> leerIngredientesText()
            3 -> crearHamburguesasText()
            4 -> leerHamburguesasText()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

fun menuXML() {
    var opcion: Int
    val menuXML = """
                
                    /\~"~/\~""~~____
                    ("`-'/"").       "'"`~-._     .
                 __~6 _ 6    }       {      }.-.__.)
                (oo) |\     }        {       }"--,-'
                 "--.~~~ __/ {   }    \     }
                     '"ii"/ \_ \  | __~ "; >"
                        / /  ""\ |"    / /
                       ^^"      ^^    ^^"
         ______________________________________________________
       /                                                         \
       |                      MENÚ XML                            |
       |                                                          |
       | 1. Crear ingrediente en un fichero XML                   |
       | 2. Leer ingrediente en un fichero XML                    |
       | 3. Crear hamburguesas en un fichero XML                  |
       | 4. Leer hamburguesas en un fichero XML                   |
       | 5. Volver al menu principal                              |
       |                                                          |
        \________________________________________________________/
            _____ ____                                ____ _____
            `----,\    )                            (    /,----'
             `--==\\  /                              \  //==--'
              `--==\\/                                \//==--'
            .-~~~~-.Y|\\_                          _//|Y.-~~~~-,
         @_/        /  66\_                      _/66  \        \_@
           |    \   \   _(")                    (")_   /   /    |
            \   /-| ||'--'                         '--'|| |-\   /
             \_\  \_\\                                 //_/  /_/

       
    """.trimIndent()

    do {
        println(menuXML)

        println("Selecciona una opción: ")
        opcion = readln()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> crearIngredientesXML()
            2 -> leerIngredientesXML()
            3 -> crearHamburguesasXML()
            4 -> leerHamburguesasXML()
            5 -> menuPrincipal()
            else -> println("Opción inválida, por favor seleccione una opción del menú")
        }
        println()
    } while (opcion != 5)
}

fun crearIngredientesBinario(): MutableList<Ingrediente> {
    val ingredientesRepositoryBinario = IngredientesRepositoryList(IngredientesFicheroBinarioService)
    val ingredientesControllerBinario = IngredientesController(ingredientesRepositoryBinario)

    logger.debug { "Creando/Guardando ingredientes en un fichero binario" }
    repeat(7) {
        ingredientesRepositoryBinario.save(createIngredienteRandom())
    }

    val leerIngredientesBinario = leerIngredientesBinario()

    logger.debug { "Guardando ingredientes de un fichero binario en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesBinario.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero binario" }
    ingredientesControllerBinario.clear()

    return almacén.toMutableList()
}

fun leerIngredientesBinario(): List<Ingrediente> {
    val ingredientesRepositoryBinario = IngredientesRepositoryList(IngredientesFicheroBinarioService)
    val ingredientesControllerBinario = IngredientesController(ingredientesRepositoryBinario)

    logger.debug { "Leyendo ingredientes en un fichero binario" }
    val leerIngredientesBinario = ingredientesControllerBinario.findAll()
    leerIngredientesBinario.forEach { println(it) }

    return leerIngredientesBinario
}

fun crearIngredientesCSV(): MutableList<Ingrediente> {
    val ingredientesRepositoryCSV = IngredientesRepositoryList(IngredientesFicheroCsvService)
    val ingredientesControllerCSV = IngredientesController(ingredientesRepositoryCSV)

    logger.debug { "Creando/Guardando ingredientes en un fichero csv" }
    repeat(7) {
        ingredientesRepositoryCSV.save(createIngredienteRandom())
    }


    val leerIngredientesCSV = leerIngredientesCSV()

    logger.debug { "Guardando ingredientes de un fichero csv en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesCSV.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero csv" }
    ingredientesControllerCSV.clear()

    return almacén.toMutableList()
}

fun leerIngredientesCSV(): List<Ingrediente> {
    val ingredientesRepositoryCSV = IngredientesRepositoryList(IngredientesFicheroCsvService)
    val ingredientesControllerCSV = IngredientesController(ingredientesRepositoryCSV)

    logger.debug { "Leyendo ingredientes en un fichero csv" }
    val leerIngredientesCSV = ingredientesControllerCSV.findAll()
    leerIngredientesCSV.forEach { println(it) }

    return leerIngredientesCSV
}

@ExperimentalStdlibApi
fun crearIngredientesJson(): MutableList<Ingrediente> {
    val ingredientesRepositoryJson = IngredientesRepositoryList(IngredientesFicheroJsonService)
    val ingredientesControllerJson = IngredientesController(ingredientesRepositoryJson)

    logger.debug { "Creando/Guardando ingredientes en un fichero Json" }
    repeat(7) {
        ingredientesRepositoryJson.save(createIngredienteRandom())
    }

    val leerIngredientesJson = leerIngredientesJson()

    logger.debug { "Guardando ingredientes de un fichero Json en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesJson.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero Json" }
    ingredientesControllerJson.clear()

    return leerIngredientesJson.toMutableList()
}

@ExperimentalStdlibApi
fun leerIngredientesJson(): List<Ingrediente> {
    val ingredientesRepositoryJson = IngredientesRepositoryList(IngredientesFicheroJsonService)
    val ingredientesControllerJson = IngredientesController(ingredientesRepositoryJson)

    logger.debug { "Leyendo ingredientes en un fichero Json" }
    val leerIngredientesJson = ingredientesControllerJson.findAll()
    leerIngredientesJson.forEach { println(it) }

    return leerIngredientesJson
}

fun crearIngredientesSerializable(): MutableList<Ingrediente> {
    val ingredienteRepositorySerializable = IngredientesRepositoryList(IngredientesFicheroSerializableService)
    val ingredientesControllerSerializable = IngredientesController(ingredienteRepositorySerializable)

    logger.debug { "Creando/Guardando ingredientes en un fichero csv" }
    repeat(7) {
        ingredienteRepositorySerializable.save(createIngredienteRandom())
    }

    val leerIngredientesSerializable = leerIngredientesSerializable()

    logger.debug { "Guardando ingredientes de un fichero serializable en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesSerializable.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero serializable" }
    ingredientesControllerSerializable.clear()

    logger.debug { "Devolviendo el almacén temporal" }
    return almacén
}

fun leerIngredientesSerializable(): List<Ingrediente> {
    val ingredienteRepositorySerializable = IngredientesRepositoryList(IngredientesFicheroSerializableService)
    val ingredientesControllerSerializable = IngredientesController(ingredienteRepositorySerializable)

    logger.debug { "Leyendo ingredientes en un fichero serializable" }
    val leerIngredientesSerializable = ingredientesControllerSerializable.findAll()
    leerIngredientesSerializable.forEach { println(it) }

    return leerIngredientesSerializable
}

fun crearIngredientesText(): MutableList<Ingrediente> {
    val ingredientesRepositoryText = IngredientesRepositoryList(IngredientesFicheroTextoService)
    val ingredientesControllerText = IngredientesController(ingredientesRepositoryText)

    logger.debug { "Creando/Guardando ingredientes en un fichero de texto" }
    repeat(7) {
        ingredientesRepositoryText.save(createIngredienteRandom())
    }

    val leerIngredientesText = leerIngredientesText()

    logger.debug { "Guardando ingredientes de un fichero de texto en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesText.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero de texto" }
    ingredientesControllerText.clear()

    return almacén.toMutableList()
}

fun leerIngredientesText(): List<Ingrediente> {
    val ingredientesRepositoryText = IngredientesRepositoryList(IngredientesFicheroTextoService)
    val ingredientesControllerText = IngredientesController(ingredientesRepositoryText)

    logger.debug { "Leyendo ingredientes en un fichero de texto" }
    val leerIngredientesText = ingredientesControllerText.findAll()
    leerIngredientesText.forEach { println(it) }

    return leerIngredientesText
}

fun crearIngredientesXML(): MutableList<Ingrediente> {
    val ingredientesRepositoryXml = IngredientesRepositoryList(IngredientesFicheroXmlService)
    val ingredientesControllerXml = IngredientesController(ingredientesRepositoryXml)

    logger.debug { "Creando/Guardando ingredientes en un fichero de texto" }
    repeat(7) {
        ingredientesRepositoryXml.save(createIngredienteRandom())
    }

    val leerIngredientesXml = leerIngredientesXML()

    logger.debug { "Guardando ingredientes de un fichero xml en el almacén temporal" }
    val almacén: MutableList<Ingrediente> = mutableListOf()
    leerIngredientesXml.forEach { almacén.add(it) }

    logger.debug { "Limpiando repository de ingredientes en un fichero xml" }
    ingredientesControllerXml.clear()

    return almacén.toMutableList()
}

fun leerIngredientesXML(): List<Ingrediente> {
    val ingredientesRepositoryXml = IngredientesRepositoryList(IngredientesFicheroXmlService)
    val ingredientesControllerXml = IngredientesController(ingredientesRepositoryXml)

    logger.debug { "Leyendo ingredientes en un fichero de xml" }
    val leerIngredientesXml = ingredientesControllerXml.findAll()
    leerIngredientesXml.forEach { println(it) }

    return leerIngredientesXml
}

fun crearHamburguesasBinario() {
    val hamburguesasRepositoryBinario = hamburguesasRepositoryList(hamburguesasFicheroBinarioService)
    val hamburguesaControllerBinario = HamburguesasController(hamburguesasRepositoryBinario)
    lateinit var almacenIngredientesBinario: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero de binario" }
        almacenIngredientesBinario = crearIngredientesBinario()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero de binario" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositoryBinario.save(createHamburguesaRandom(almacenIngredientesBinario.toMutableList()))
    }

    val leerHamburguesasBinario = leerHamburguesasBinario()

    logger.debug { "Limpiando repository de hamburguesas en un fichero de binario" }
    hamburguesaControllerBinario.clear()
}

fun leerHamburguesasBinario(): List<Hamburguesa> {
    val hamburguesasRepositoryBinario = hamburguesasRepositoryList(hamburguesasFicheroBinarioService)
    val hamburguesaControllerBinario = HamburguesasController(hamburguesasRepositoryBinario)

    logger.debug { "Leyendo hamburguesas de un fichero de binario" }
    val leerHamburguesasBinario = hamburguesaControllerBinario.findAll()
    leerHamburguesasBinario.forEach { println(it) }

    return leerHamburguesasBinario
}

fun crearHamburguesasCSV() {
    val hamburguesasRepositoryCSV = hamburguesasRepositoryList(hamburguesasFicheroCsvService)
    val hamburguesaControllerCSV = HamburguesasController(hamburguesasRepositoryCSV)
    lateinit var almacénIngredientesCSV: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero csv" }
        almacénIngredientesCSV = crearIngredientesCSV()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero csv" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositoryCSV.save(createHamburguesaRandom(almacénIngredientesCSV))
    }

    val leerHamburguesasCSV = leerHamburguesasCSV()

    logger.debug { "Limpiando repository de hamburguesas en un fichero csv" }
    hamburguesaControllerCSV.clear()
}

fun leerHamburguesasCSV(): List<Hamburguesa> {
    val hamburguesasRepositoryCSV = hamburguesasRepositoryList(hamburguesasFicheroCsvService)
    val hamburguesaControllerCSV = HamburguesasController(hamburguesasRepositoryCSV)

    logger.debug { "Leyendo hamburguesas de un fichero csv" }
    val leerHamburguesasCSV = hamburguesaControllerCSV.findAll()
    leerHamburguesasCSV.forEach { println(it) }

    return leerHamburguesasCSV
}

@ExperimentalStdlibApi
fun crearHamburguesasJson(): List<Hamburguesa> {
    val hamburguesasRepositoryJson = hamburguesasRepositoryList(hamburguesasFicheroJson)
    val hamburguesaControllerJson = HamburguesasController(hamburguesasRepositoryJson)

    lateinit var almacenIngredientesJson: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero json" }
        almacenIngredientesJson = crearIngredientesJson()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero json" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositoryJson.save(createHamburguesaRandom(almacenIngredientesJson))
    }
    val muchosIngredientes = crearIngredientesJson()
    muchosIngredientes.add(Ingrediente(id = 8, name = "Bacon", price = 5.0))
    hamburguesasRepositoryJson.save(Hamburguesa(name = "Hamburguesa Tocha", receta = muchosIngredientes))
    val leerHamburguesasJson = leerHamburguesasJson()

    logger.debug { "Limpiando repository de hamburguesas en un fichero json" }
    hamburguesaControllerJson.clear()

    return leerHamburguesasJson
}

@ExperimentalStdlibApi
fun leerHamburguesasJson(): List<Hamburguesa> {
    val hamburguesasRepositoryJson = hamburguesasRepositoryList(hamburguesasFicheroJson)
    val hamburguesaControllerJson = HamburguesasController(hamburguesasRepositoryJson)

    logger.debug { "Leyendo hamburguesas de un fichero Json" }
    val leerHamburguesasJson = hamburguesaControllerJson.findAll()
    leerHamburguesasJson.forEach { println(it) }

    return leerHamburguesasJson
}

fun crearHamburguesasSerializable() {
    val hamburguesasRepositorySerializable = hamburguesasRepositoryList(hamburguesasFicheroSerializableService)
    val hamburgesaControllerSerializable = HamburguesasController(hamburguesasRepositorySerializable)
    lateinit var almacenIngredientesSerializable: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero serializable" }
        almacenIngredientesSerializable = crearIngredientesSerializable()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero serializable" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositorySerializable.save(createHamburguesaRandom(almacenIngredientesSerializable.toMutableList()))
    }

    val leerHamburguesasSerializable = leerHamburguesaSerializable()

    logger.debug { "Limpiando repository de hamburguesas en un fichero serializable" }
    hamburgesaControllerSerializable.clear()
}

fun leerHamburguesaSerializable(): List<Hamburguesa> {
    val hamburguesasRepositorySerializable = hamburguesasRepositoryList(hamburguesasFicheroSerializableService)
    val hamburgesaControllerSerializable = HamburguesasController(hamburguesasRepositorySerializable)

    logger.debug { "Leyendo hamburguesas de un fichero serializable" }
    val leerHamburguesasSerializable = hamburgesaControllerSerializable.findAll()
    leerHamburguesasSerializable.forEach { println(it) }

    return leerHamburguesasSerializable
}

fun crearHamburguesasText() {
    val hamburguesasRepositoryText = hamburguesasRepositoryList(hamburguesasFicheroTextoService)
    val hamburgesaControllerText = HamburguesasController(hamburguesasRepositoryText)
    lateinit var almacenIngredientesText: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero de texto" }
        almacenIngredientesText = crearIngredientesText()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero de texto" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositoryText.save(createHamburguesaRandom(almacenIngredientesText.toMutableList()))
    }

    val leerHamburguesasText = leerHamburguesasText()

    logger.debug { "Limpiando repository de hamburguesas en un fichero de texto" }
    hamburgesaControllerText.clear()
}

fun leerHamburguesasText(): List<Hamburguesa> {
    val hamburguesasRepositoryText = hamburguesasRepositoryList(hamburguesasFicheroTextoService)
    val hamburgesaControllerText = HamburguesasController(hamburguesasRepositoryText)

    logger.debug { "Leyendo hamburguesas de un fichero de texto" }
    val leerHamburguesasText = hamburgesaControllerText.findAll()
    leerHamburguesasText.forEach { println(it) }

    return leerHamburguesasText
}

fun crearHamburguesasXML() {
    val hamburguesasRepositoryXml = hamburguesasRepositoryList(hamburguesasFicheroXmlService)
    val hamburgesaControllerXml = HamburguesasController(hamburguesasRepositoryXml)
    lateinit var almacenIngredientesXml: MutableList<Ingrediente>

    fun createIngredientes() {
        logger.debug { "Creando ingredientes para las hamburguesas almacenadas en un fichero xml" }
        almacenIngredientesXml = crearIngredientesXML()
    }

    logger.debug { "Creando/Guardando hamburguesas en un fichero de xml" }
    repeat(3) {
        createIngredientes()
        hamburguesasRepositoryXml.save(createHamburguesaRandom(almacenIngredientesXml.toMutableList()))
    }

    val leerHamburguesasXML = leerHamburguesasXML()

    logger.debug { "Limpiando repository de hamburguesas en un fichero xml" }
    hamburgesaControllerXml.clear()
}

fun leerHamburguesasXML(): List<Hamburguesa> {
    val hamburguesasRepositoryXml = hamburguesasRepositoryList(hamburguesasFicheroXmlService)
    val hamburguesaControllerXml = HamburguesasController(hamburguesasRepositoryXml)

    logger.debug { "Leyendo hamburguesas de un fichero de texto" }
    val leerHamburguesasXml = hamburguesaControllerXml.findAll()
    leerHamburguesasXml.forEach { println(it) }

    return leerHamburguesasXml
}

@ExperimentalStdlibApi
fun consultas() {
    consultasIngredientes()
    consultashamburguesas()
}

@ExperimentalStdlibApi
private fun consultasIngredientes() {
    val ingredientesRepositoryJson = IngredientesRepositoryList(IngredientesFicheroJsonService)
    val ingredientesControllerJson = IngredientesController(ingredientesRepositoryJson)

    // Necesitamos crear ingredientes
    crearIngredientesJson()
    // Carga los ingredientes en el almacén al ser otro controller distinto al de las consultas el que lo crea
    ingredientesControllerJson.findAll()

    println(
        "El precio medio de los ingredientes es de: ${
            ingredientesControllerJson.precioMedioIngredientes().toLocalMoney()
        }"
    )
}

@ExperimentalStdlibApi
private fun consultashamburguesas() {
    val hamburguesasRepositoryJson = hamburguesasRepositoryList(hamburguesasFicheroJson)
    val hamburgesaControllerJson = HamburguesasController(hamburguesasRepositoryJson)
    val hamburguesaMuchosIngredientes = Hamburguesa(name = "Hamburguesa Tocha", receta = crearIngredientesJson())

    // Necesitamos crear hamburguesas
    crearHamburguesasJson()
    // Carga las hamburguesas en el almacén al ser otro controller distinto al de las consultas el que lo crea
    hamburgesaControllerJson.findAll()

    println("La hamburguesa con mayor precio es el siguiente: ${hamburgesaControllerJson.hamburguesaMasCara()}")
    println("La hamburguesa con más ingredientes es: ${hamburgesaControllerJson.hamburguesaMasIngredientes()}")
    println(
        "El precio medio de las hamburguesas es de: ${
            hamburgesaControllerJson.precioMedioHamburguesa().toLocalMoney()
        }"
    )
    hamburgesaControllerJson.hamburguesasAgrupadoPorNumIngredientes()
        .forEach { println("Hamburguesas con ${it.key} ingredientes: ${it.value}") }
}
