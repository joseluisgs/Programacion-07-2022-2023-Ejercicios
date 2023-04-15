package services.storage.informe

import config.AppConfig
import dto.InformeDto
import mappers.toClass
import mappers.toCsvRow
import mappers.toDto
import models.Informe
import mu.KotlinLogging
import java.io.BufferedInputStream
import java.io.File

private val logger = KotlinLogging.logger {  }

object InformeFileBinario: InformeStorageService {
    private val localFile = "${AppConfig.APP_DATA}${File.separator}informe.bin"

    override fun saveAll(elements: List<Informe>): List<Informe> {
        logger.debug { "InformeFileBinario ->\tsaveAll" }
        val file = File(localFile)
        if (file.exists() && !file.canWrite()) return emptyList()
        file.outputStream().buffered().use {
            elements.map { it.toDto() }.forEach{ element ->
                it.write(
                    element.toCsvRow().toByteArray()
                )
            }
        }
        return elements
    }

    override fun loadAll(): List<Informe> {
        logger.debug { "InformeFileBinario ->\tloadAll" }
        val file = File(localFile)
        if (!file.exists() || !file.canRead()) return emptyList()
        val informes = mutableListOf<Informe>()
        file.inputStream().buffered().use {
            while (it.available() > 0){
                val day = readCampoBinario(it)
                val temMedia = readCampoBinario(it)

                val temMax = readCampoBinario(it)
                val poblacionMax = readCampoBinario(it)
                val timeMax = readCampoBinario(it)

                val temMin = readCampoBinario(it)
                val poblacionMin = readCampoBinario(it)
                val timeMin = readCampoBinario(it)

                val isPrecipitacion = readCampoBinario(it)
                val precipitacion = readCampoBinario(it)
                informes.add(
                    InformeDto(
                        day,temMedia,temMax,poblacionMax,timeMax,temMin,poblacionMin,timeMin,isPrecipitacion,precipitacion
                    ).toClass()
                )
            }
        }
        return informes.toList()
    }

    private fun readCampoBinario(it: BufferedInputStream, separator: Char = ','): String {
        val builder = StringBuilder()
        var char = it.read().toChar()
        while (char != separator){
            builder.append(char)
            char = it.read().toChar()
        }
        return builder.toString()
    }
}