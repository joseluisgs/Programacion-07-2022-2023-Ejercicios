package org.example.repositories.persona;

import org.example.models.Persona;
import org.example.repositories.CrudRepository;

// Por si se quiere extender las funcionalidades de la interfaz CrudRepository
public interface PersonaExtension extends CrudRepository<Persona, Integer>{}
