package repositories

import factories.PersonaFactory
import factories.PersonaFactory.getPersonasDefault
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import repositories.persona.PersonaRepository
import repositories.persona.PersonaRepositoryMap
import service.storage.persona.PersonaStorageService

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonaRepositoryMapTest: PersonaRepositoryGenericTest() {
    override fun getRepository() = PersonaRepositoryMap
}