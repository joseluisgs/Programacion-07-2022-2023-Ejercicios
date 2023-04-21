package storage

import config.AppConfig
import service.storage.persona.PersonaFileBinario
import service.storage.persona.PersonaStorageService
import java.io.File

class PersonaStorageBinarioTest: PersonaStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}persona.bin"

    override fun getStorage() = PersonaFileBinario
}