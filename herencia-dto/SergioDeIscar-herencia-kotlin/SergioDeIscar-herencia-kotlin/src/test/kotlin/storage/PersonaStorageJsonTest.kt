package storage

import config.AppConfig
import service.storage.persona.PersonaFileBinario
import service.storage.persona.PersonaFileJson
import java.io.File

@ExperimentalStdlibApi
class PersonaStorageJsonTest: PersonaStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}persona.json"

    override fun getStorage() = PersonaFileJson
}