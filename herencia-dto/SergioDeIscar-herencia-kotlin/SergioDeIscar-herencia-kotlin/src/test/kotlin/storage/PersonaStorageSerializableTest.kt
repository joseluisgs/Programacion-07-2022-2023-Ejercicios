package storage

import config.AppConfig
import service.storage.persona.PersonaFileBinario
import service.storage.persona.PersonaFileSerializable
import java.io.File

class PersonaStorageSerializableTest: PersonaStorageGenericTest() {
    override fun filePath() = "${AppConfig.APP_DATA}${File.separator}persona.ser"

    override fun getStorage() = PersonaFileSerializable
}