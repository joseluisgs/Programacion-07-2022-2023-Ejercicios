package repository.ingrediente;

import models.Ingrediente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import storage.ingrediente.IngredienteStorageService;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredienteRepositoryImplTest {
    @Mock
    IngredienteStorageService storage;

    @InjectMocks
    IngredienteRepositoryImpl repository;

    private ArrayList<Ingrediente> ingredientesBase = new ArrayList<Ingrediente>();

    @BeforeEach
    void setUp(){
        ingredientesBase.clear();
        ingredientesBase.add(new Ingrediente(1, "Cebolla", 25.6));
        ingredientesBase.add(new Ingrediente(2, "Carne", 10.9));
        ingredientesBase.add(new Ingrediente(3, "Patata", 13.4));
        ingredientesBase.add(new Ingrediente(4, "Pan", 3.4));
        ingredientesBase.add(new Ingrediente(5, "Lechuga", 1.99));
    }

    @Test
    void getAllTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(ingredientesBase);
        ArrayList<Ingrediente> lista = repository.getAll();
        assertEquals(5, lista.size());
        assertEquals(ingredientesBase.get(0), lista.get(0));
        assertEquals(ingredientesBase.get(1), lista.get(1));
        assertEquals(ingredientesBase.get(2), lista.get(2));
        assertEquals(ingredientesBase.get(3), lista.get(3));
        assertEquals(ingredientesBase.get(4), lista.get(4));
    }

    @Test
    void getByIdTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(ingredientesBase);
        Ingrediente ingrediente = repository.getById(1);
        assertEquals(ingrediente, ingredientesBase.get(0));
    }

    @Test
    void addTest() throws IOException {
        when ( storage.loadAll() ).thenReturn(ingredientesBase);
        Ingrediente ingrediente = new Ingrediente(6, "Pepino", 45.6);
        assertEquals(ingrediente, repository.add(ingrediente));
    }
}