package org.example.repositories.informe;

import org.example.models.Informe;
import org.example.repositories.CrudRepository;

import java.time.LocalDate;

public interface InformeExtension extends CrudRepository<Informe, LocalDate> {  }
