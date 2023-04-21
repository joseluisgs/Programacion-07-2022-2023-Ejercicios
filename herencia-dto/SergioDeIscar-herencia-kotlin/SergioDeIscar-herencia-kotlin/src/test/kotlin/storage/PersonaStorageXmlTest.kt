package storage

import config.AppConfig
import service.storage.persona.PersonaFileBinario
import service.storage.persona.PersonaFileXml
import java.io.File

class PersonaStorageXmlTest: PersonaStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}personas.xml"

    override fun getStorage() = PersonaFileXml
}