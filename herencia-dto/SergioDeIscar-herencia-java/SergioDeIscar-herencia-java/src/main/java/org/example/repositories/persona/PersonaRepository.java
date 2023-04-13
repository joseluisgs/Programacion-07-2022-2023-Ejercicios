package org.example.repositories.persona;

import org.example.models.Persona;
import org.example.repositories.IExternalStore;

public interface PersonaRepository extends PersonaExtension, IExternalStore<Persona> {}