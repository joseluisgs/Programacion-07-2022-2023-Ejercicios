package repository.producto

import locate.round
import models.Bebida
import models.Hamburguesa
import models.Ingrediente
import models.Producto
import mu.KotlinLogging
import services.database.DataBaseManager
import java.sql.Statement

private val logger = KotlinLogging.logger {}

class ProductoRepositoryDataBase: ProductoRepository {
    override fun getOrderByPrecio(): List<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tgetAllOrderByPrecio" }
        return findAll().sortedBy { it.precio }
    }

    override fun getProductoMasCaro(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasCaro" }
        return findAll().maxByOrNull { it.precio }
    }

    override fun getProductoMasBarato(): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tgetProductoMasBarato" }
        return findAll().minByOrNull { it.precio }
    }

    override fun getBebidaConMenosCapacidad(): Bebida? {
        logger.debug { "ProductoRepositoryMap ->\tgetBebidaConMenosCapacidad" }
        return findAll().filterIsInstance<Bebida>().minByOrNull { it.capacidad }
    }

    override fun getHamburguesaMasCara(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaMasCara" }
        return findAll().filterIsInstance<Hamburguesa>().maxByOrNull { it.precio }
    }

    override fun getHamburguesaConMasIngredientes(): Hamburguesa? {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesaConMasIngredientes" }
        return findAll().filterIsInstance<Hamburguesa>().maxByOrNull { it.ingredientes.size }
    }

    override fun getHamburguesasPrecioMedio(): Double {
        logger.debug { "ProductoRepositoryMap ->\tgetHamburguesasPrecioMedio" }
        return findAll().filterIsInstance<Hamburguesa>().map { it.precio }.average()
    }

    override fun getPrecioMedioIngredientes(): Map<String, Double> {
        logger.debug { "ProductoRepositoryMap ->\tgetPrecioMedioIngredientes" }
        return findAll().filterIsInstance<Hamburguesa>().flatMap { it.ingredientes }
            .groupBy { it.nombre }
            .mapValues { it.value.map { it.precio }.average() }
    }

    override fun findAll(): Iterable<Producto> {
        logger.debug { "ProductoRepositoryMap ->\tgetAll" }
        return findBebidas() + findHamburguesas() // Cosas de Kotlin :D
    }

    private fun findBebidas(): List<Producto> {
        logger.info { "ProductoRepositoryMap ->\tfindBebidas" }
        val bebidas = mutableListOf<Producto>()
        val sql = """
            SELECT nIdProducto, cNombre, nPrecio, tB.nCapacidad FROM tProducto
            INNER JOIN tBebida tB on tProducto.nIdProducto = tB.nIdBebida""".trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use {
            val result = it.executeQuery()
            while(result.next()) {
                bebidas.add(
                    Bebida(
                        result.getLong("nIdProducto"),
                        result.getString("cNombre"),
                        result.getFloat("nPrecio"),
                        result.getInt("nCapacidad")
                    )
                )
            }
        }
        return bebidas.toList()
    }

    private fun findHamburguesas(): List<Producto> {
        logger.info { "ProductoRepositoryMap ->\tfindHamburguesas" }
        val hamburguesas = mutableListOf<Producto>()
        val sql = """
            SELECT nIdProducto, cNombre, nPrecio FROM tProducto
            INNER JOIN tHamburguesa tH on tProducto.nIdProducto = tH.nIdHamburguesa""".trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use {
        val result = it.executeQuery()
            while(result.next()) {
                val id = result.getLong("nIdProducto")
                hamburguesas.add(
                    Hamburguesa(
                        id,
                        result.getString("cNombre"),
                        findIngredientes(id)
                    )
                )
            }
        }
        return hamburguesas.toList()
    }

    private fun findIngredientes(id: Long): List<Ingrediente> {
        logger.info { "ProductoRepositoryMap ->\tfindIngredientes" }
        val ingredientes = mutableListOf<Ingrediente>()
        val sql = """
            SELECT nIdIngrediente, cNombre, nPrecio FROM tIngrediente
            INNER JOIN tHamburguesa_Ingrediente tHI on tIngrediente.nIdIngrediente = tHI.id_ingrediente
            WHERE tHI.id_hamburguesa = ?""".trimIndent()
        DataBaseManager.dataBase.prepareStatement(sql).use {
            it.setLong(1, id)
            val result = it.executeQuery()
            while(result.next()) {
                ingredientes.add(
                    Ingrediente(
                        result.getLong("nIdIngrediente"),
                        result.getString("cNombre"),
                        result.getFloat("nPrecio")
                    )
                )
            }
        }
        return ingredientes.toList()
    }

    override fun findById(id: Long): Producto? {
        logger.debug { "ProductoRepositoryMap ->\tfindById" }
        // Por ahora, luego hago la query
        return findAll().firstOrNull { it.id == id }
    }

    override fun save(element: Producto, storage: Boolean): Producto {
        logger.debug { "ProductoRepositoryMap ->\tsave" }
        return if(existsById(element.id)) {
            update(element)
        } else {
            create(element)
        }
    }

    private fun create(element: Producto): Producto {
        logger.info { "ProductoRepositoryMap ->\tcreate" }
        var newID: Long = -1
        val insertTProducto = """
            INSERT INTO tProducto (cNombre, nPrecio)
            VALUES (?, ?)
        """.trimIndent()
        DataBaseManager.dataBase.use {
            it.prepareStatement(insertTProducto, Statement.RETURN_GENERATED_KEYS).use { stm ->
                stm.setString(1, element.nombre)
                stm.setFloat(2, element.precio.round())

                stm.executeUpdate()

                val claves = stm.generatedKeys
                if (claves.next()) newID = claves.getLong(1)
            }
        }
        element.copy(id = newID)

        when(element){
            is Bebida -> {
                crearBebida(element)
            }

            is Hamburguesa -> {
                crearHamburguesa(element)
            }
        }
        return element
    }

    private fun crearHamburguesa(element: Hamburguesa) {
        logger.info { "ProductoRepositoryMap ->\tcrearHamburguesa" }
        val insertTHamburguesa = """
                        INSERT INTO tHamburguesa (nIdHamburguesa)
                        VALUES (?)
                    """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(insertTHamburguesa).use { stm ->
            stm.setLong(1, element.id)
            stm.executeUpdate()
        }
        crearIngredientes(element.id, element.ingredientes)
    }

    private fun crearIngredientes(idHamburguesa: Long, ingredientes: List<Ingrediente>){
        logger.info { "ProductoRepositoryMap ->\tcrearIngredientes" }
        val insertTIngrediente = """
                        INSERT INTO tIngrediente (cNombre, nPrecio)
                        VALUES (?, ?)
                    """.trimIndent()
        val insertTHamburguesa_Ingrediente = """
                        INSERT INTO tHamburguesa_Ingrediente (id_hamburguesa, id_ingrediente)
                        VALUES (?, ?)
                    """.trimIndent()
        ingredientes.forEach { ingre ->
            var newIngreId = 0L
            DataBaseManager.dataBase.use {
                it.prepareStatement(insertTIngrediente, Statement.RETURN_GENERATED_KEYS).use { stm ->
                    stm.setString(1, ingre.nombre)
                    stm.setFloat(2, ingre.precio)
                    stm.executeUpdate()
                    val claves = stm.generatedKeys
                    if (claves.next()) newIngreId = claves.getLong(1)
                }
            }
            ingre.copy(id = newIngreId)
        }
        DataBaseManager.dataBase.prepareStatement(insertTHamburguesa_Ingrediente).use { stm ->
            ingredientes.forEach {
                stm.setLong(1, idHamburguesa)
                stm.setLong(2, it.id)
                stm.executeUpdate()
            }
        }
    }

    private fun crearBebida(element: Bebida) {
        logger.info { "ProductoRepositoryMap ->\tcrearBebida" }
        val insertTBebida = """
                        INSERT INTO tBebida (nIdBebida, nCapacidad)
                        VALUES (?, ?)
                    """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(insertTBebida).use { stm ->
            stm.setLong(1, element.id)
            stm.setInt(2, element.capacidad)
            stm.executeUpdate()
        }
    }

    private fun update(element: Producto): Producto {
        logger.info { "ProductoRepositoryMap ->\tupdate" }
        val updateTProducto = """
            UPDATE tProducto
            SET cNombre = ?, nPrecio = ?
            WHERE nIdProducto = ?
        """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(updateTProducto).use { stm ->
            stm.setString(1, element.nombre)
            stm.setFloat(2, element.precio.round())
            stm.setLong(3, element.id)
            stm.executeUpdate()
        }
        when(element){
            is Bebida -> {
                updateBebida(element)
            }

            is Hamburguesa -> {
                updateHamburguesa(element)
            }
        }
        return element
    }

    private fun updateHamburguesa(element: Hamburguesa) {
        logger.info { "ProductoRepositoryMap ->\tupdateHamburguesa" }
        val deleteTHamburguesa_Ingrediente = """
            DELETE FROM tHamburguesa_Ingrediente
            WHERE id_hamburguesa = ?
        """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(deleteTHamburguesa_Ingrediente).use { stm ->
            stm.setLong(1, element.id)
            stm.executeUpdate()
        }
        crearIngredientes(element.id, element.ingredientes)
    }

    private fun updateBebida(element: Bebida) {
        logger.info { "ProductoRepositoryMap ->\tupdateBebida" }
        val updateTBebida = """
            UPDATE tBebida
            SET nCapacidad = ?
            WHERE nIdBebida = ?
        """.trimIndent()
        DataBaseManager.dataBase.prepareStatement(updateTBebida).use { stm ->
            stm.setInt(1, element.capacidad)
            stm.setLong(2, element.id)
            stm.executeUpdate()
        }
    }

    override fun saveAll(elements: Iterable<Producto>, storage: Boolean) {
        logger.debug { "ProductoRepositoryMap ->\tsaveAll" }
        elements.forEach { save(it, storage) }
    }

    override fun deleteById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(element: Producto): Boolean {
        logger.debug { "ProductoRepositoryMap ->\tdelete" }
        return deleteById(element.id)
    }

    override fun existsById(id: Long): Boolean {
        logger.debug { "ProductoRepositoryMap ->\texistsById" }
        return findById(id) != null
    }
}