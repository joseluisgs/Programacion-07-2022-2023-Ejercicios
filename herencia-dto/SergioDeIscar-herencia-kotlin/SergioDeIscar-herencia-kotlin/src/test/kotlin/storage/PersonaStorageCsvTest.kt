package storage

import config.AppConfig
import service.storage.persona.PersonaFileBinario
import service.storage.persona.PersonaFileCsv
import java.io.File

class PersonaStorageCsvTest: PersonaStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}persona.csv"

    override fun getStorage() = PersonaFileCsv
}