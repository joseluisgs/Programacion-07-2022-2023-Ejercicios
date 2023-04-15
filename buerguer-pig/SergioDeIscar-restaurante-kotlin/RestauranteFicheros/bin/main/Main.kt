import config.AppConfig
import controller.HamburguesaController
import factories.HamburguesaFactory
import models.Hamburguesa
import repository.hamburguesa.HamburguesaRepositoryMap
import services.storage.hamburguesa.*

fun main(args: Array<String>){
    println("APP_NAME: ${AppConfig.APP_NAME}")

    val hamburguesaControllerSeria = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileSerializable
        )
    )
    val hamburguesaControllerCsv = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileCSV
        )
    )
    val hamburguesaControllerBin = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileBinario
        )
    )
    val hamburguesaControllerAleatorio = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileAleatorio
        )
    )
    val hamburguesaControllerJson = HamburguesaController(
        HamburguesaRepositoryMap(
            HamburguesaFileJson
        )
    )

    val hamburgesasRdn = mutableListOf<Hamburguesa>()

    repeat((5..10).random()){
        hamburgesasRdn.add(HamburguesaFactory.getRdnHamburguesa())
    }

    hamburguesaControllerSeria.saveAll(hamburgesasRdn)
    hamburguesaControllerCsv.saveAll(hamburgesasRdn)
    hamburguesaControllerBin.saveAll(hamburgesasRdn)
    hamburguesaControllerAleatorio.saveAll(hamburgesasRdn)
    hamburguesaControllerJson.saveAll(hamburgesasRdn)

    println("Hamburguesas guardadas en el controlador serializable:")
    hamburguesaControllerSeria.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador csv:")
    hamburguesaControllerCsv.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador binario:")
    hamburguesaControllerBin.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador aleatorio:")
    hamburguesaControllerAleatorio.getAll().forEach { println(it) }
    println("Hamburguesas guardadas en el controlador json:")
    hamburguesaControllerJson.getAll().forEach { println(it) }
    println("Los controladores tienen la misma info: " +
        (
            hamburguesaControllerSeria.getAll() == hamburguesaControllerCsv.getAll() &&
            hamburguesaControllerCsv.getAll() == hamburguesaControllerBin.getAll() &&
            hamburguesaControllerBin.getAll() == hamburguesaControllerAleatorio.getAll() &&
            hamburguesaControllerAleatorio.getAll() == hamburguesaControllerJson.getAll()
        )
    )
}