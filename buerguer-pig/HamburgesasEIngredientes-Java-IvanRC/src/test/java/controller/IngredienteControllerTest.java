package controller;

import exception.ingredienteException.IngredienteException;
import models.Ingrediente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ingrediente.IngredienteRepository;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredienteControllerTest {

    @Mock
    IngredienteRepository repository;

    @InjectMocks
    IngredienteController controller;

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
    void getAll() throws IOException {
        when ( repository.getAll() ).thenReturn(ingredientesBase);
        ArrayList<Ingrediente> lista = controller.getAll();
        assertEquals(5, lista.size());
        assertEquals(ingredientesBase.get(0), lista.get(0));
        assertEquals(ingredientesBase.get(1), lista.get(1));
        assertEquals(ingredientesBase.get(2), lista.get(2));
        assertEquals(ingredientesBase.get(3), lista.get(3));
        assertEquals(ingredientesBase.get(4), lista.get(4));
    }

    @Test
    void getById() throws Exception {
        when ( repository.getById(1) ).thenReturn(ingredientesBase.get(0));
        Ingrediente ingrediente = controller.getById("1");
        assertEquals(ingrediente, ingredientesBase.get(0));
    }

    @Test
    void getByIdButNotFound() throws Exception {
        when ( repository.getById(32) ).thenReturn(null);
        var mes = assertThrows(IngredienteException.IngredienteNotFoundException.class,() -> controller.getById("32")).getMessage();
        assertEquals( "Error al buscar ingrediente, el ingrediente de id: 32, no se ha encontrado.", mes);
    }

    @Test
    void add() throws Exception {
        Ingrediente ingrediente = new Ingrediente(6, "Pepino", 45.6);
        when ( repository.add(ingrediente) ).thenReturn(ingrediente);
        assertEquals(ingrediente, controller.add(ingrediente));
    }

    @Test
    void addButAlreadyExisting() throws IOException {
        Ingrediente ingrediente = new Ingrediente(6, "Pepino", 45.6);
        when ( repository.add(ingrediente) ).thenReturn(null);
        var mes = assertThrows(IngredienteException.IngredienteAlreadyExistsException.class,() -> controller.add(ingrediente)).getMessage();
        assertEquals( "Error al añadir ingrediente, el ingrediente de id: 6, ya existe.", mes);
    }
}