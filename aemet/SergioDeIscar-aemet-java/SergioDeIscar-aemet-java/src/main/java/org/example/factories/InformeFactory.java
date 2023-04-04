package org.example.factories;

import org.example.controllers.DuplaController;
import org.example.models.Dupla;
import org.example.models.Informe;
import org.example.models.PrecipitacionInforme;
import org.example.models.TemperaturaInforme;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class InformeFactory {
    //Singleton
    private static InformeFactory instance = null;
    public static InformeFactory getInstance() {
        if (instance == null) {
            instance = new InformeFactory();
        }
        return instance;
    }

    public Informe createInformeMadrid(DuplaController controller, LocalDate day){
        Dupla max = controller.maxTemMadrid(day);
        Dupla min = controller.minTemMadrid(day);
        Double mediaPrecipitacion = controller.mediaPrecipitacionMadrid(day);
        return new Informe(
                day,
                controller.mediaTemMadrid(),
                new TemperaturaInforme(
                        max.getPoblacion(), max.getTemMax(), max.getTimeMax()
                ),
                new TemperaturaInforme(
                        min.getPoblacion(), min.getTemMax(), min.getTimeMax()
                ),
                new PrecipitacionInforme(
                        mediaPrecipitacion > 0, mediaPrecipitacion
                )
        );
    }

    public List<Informe> createInformesMadrid(DuplaController controller){
        List<Informe> informes = new LinkedList<>();
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 29)));
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 30)));
        informes.add(createInformeMadrid(controller, LocalDate.of(2017, 10, 31)));
        return informes;
    }
}
