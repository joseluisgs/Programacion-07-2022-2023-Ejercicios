package repository.hamburgesa;

import models.Hamburgesa;
import models.Ingrediente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import storage.hamburgesa.HamburgesaStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HamburgesaRepositoryImplTest {

    @Mock
    HamburgesaStorageService storage;

    @InjectMocks
    HamburgesaRepositoryImpl repository;

    private ArrayList<Ingrediente> ingredientesBase = new ArrayList<>();

    private ArrayList<Hamburgesa> hamburgesasBase = new ArrayList<>();

    @BeforeEach
    void setUp(){
        ingredientesBase.clear();
        ingredientesBase.add(new Ingrediente(1, "Cebolla", 25.6));
        ingredientesBase.add(new Ingrediente(2, "Carne", 10.9));
        ingredientesBase.add(new Ingrediente(3, "Patata", 13.4));
        ingredientesBase.add(new Ingrediente(4, "Pan", 3.4));
        ingredientesBase.add(new Ingrediente(5, "Lechuga", 1.99));

        ArrayList<Ingrediente> ingredientes1 = new ArrayList<>();
        ingredientes1.add(new Ingrediente(1, "Cebolla", 25.6));
        ingredientes1.add(new Ingrediente(2, "Carne", 10.9));
        hamburgesasBase.add(new Hamburgesa(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e"), "Hamburges de pollo", ingredientes1));
        ArrayList<Ingrediente> ingredientes2 = new ArrayList<>();
        ingredientes2.add(new Ingrediente(2, "Carne", 10.9));
        ingredientes2.add(new Ingrediente(3, "Patata", 13.4));
        hamburgesasBase.add(new Hamburgesa(UUID.fromString("157e75c6-b966-426e-8c8d-7a6bfe0681fa"), "Hamburges de carne", ingredientes2));
        ArrayList<Ingrediente> ingredientes3 = new ArrayList<>();
        ingredientes3.add(new Ingrediente(4, "Pan", 3.4));
        ingredientes3.add(new Ingrediente(5, "Lechuga", 1.99));
        hamburgesasBase.add(new Hamburgesa(UUID.fromString("7c9fff49-ff67-446f-a4bd-bdcb7f72034f"), "Hamburges vegetariana", ingredientes3));
    }

    @Test
    void getAllTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(hamburgesasBase);
        ArrayList<Hamburgesa> lista = repository.getAll();
        assertEquals(3, lista.size());
        assertEquals(hamburgesasBase.get(0), lista.get(0));
        assertEquals(hamburgesasBase.get(1), lista.get(1));
        assertEquals(hamburgesasBase.get(2), lista.get(2));
    }

    @Test
    void getByIdTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(hamburgesasBase);
        Hamburgesa hamburgesa = repository.getById(UUID.fromString("420b4702-53ee-461a-9da1-66f676fd432e"));
        assertEquals(hamburgesa, hamburgesasBase.get(0));
    }

    @Test
    void addTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(hamburgesasBase);
        ArrayList<Ingrediente> ingredientes3 = new ArrayList<>();
        ingredientes3.add(new Ingrediente(4, "Pan", 3.4));
        ingredientes3.add(new Ingrediente(5, "Lechuga", 1.99));
        Hamburgesa hamburgesa = new Hamburgesa(UUID.randomUUID(), "Hamburgesa", ingredientes3);
        assertEquals(hamburgesa, repository.add(hamburgesa));
    }
}