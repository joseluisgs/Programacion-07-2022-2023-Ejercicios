package org.example.repositories.dupla;

import org.example.models.Dupla;
import org.example.repositories.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DuplaExtension extends CrudRepository<Dupla, String> {
    Map<LocalDate, Map<String, Dupla>> maxTemPorLugar();
    Map<LocalDate, Map<String, Dupla>> minTemPorLugar();
    Map<String, Dupla> maxTemPorProvincia();
    Map<String, Dupla> minTemPorProvincia();
    Map<String, Double> mediaPorProvincia();
    Map<LocalDate, Map<String, Double>> mediaPrecipitacionPorDiaYProvincia();
    Double mediaTemMadrid();
    Double mediaTemMax();
    Double mediaTemMin();

    /**
     * Lugares donde la máxima ha sido antes de las 15:00 por día
     */
    Map<LocalDate, List<String>> maxTemAntes();

    /**
     * Lugares donde la mínima ha sido después de las 17:30 por día
     */
    Map<LocalDate, List<String>> minTemDespues();

    Dupla maxTemMadrid(LocalDate day);
    Dupla minTemMadrid(LocalDate day);
    Double mediaPrecipitacionMadrid(LocalDate day);
}
