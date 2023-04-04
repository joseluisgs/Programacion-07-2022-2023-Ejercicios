package org.example;

import org.example.controllers.DuplaController;
import org.example.controllers.InformeController;
import org.example.factories.InformeFactory;
import org.example.models.Dupla;
import org.example.models.Informe;
import org.example.repositories.dupla.DuplaRepositoryMap;
import org.example.repositories.informe.InformeRepositoryMap;
import org.example.services.storage.dupla.DuplaFileCsv;
import org.example.services.storage.dupla.DuplaFileJson;
import org.example.services.storage.dupla.DuplaFileXml;
import org.example.services.storage.informe.InformeFileJson;
import org.example.services.storage.informe.InformeFileXml;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DuplaController controllerCsv = new DuplaController(
                new DuplaRepositoryMap(
                        DuplaFileCsv.getInstance()
                )
        );
        List<DuplaController> controllers = List.of(
                new DuplaController(
                        new DuplaRepositoryMap(
                                DuplaFileJson.getInstance()
                        )
                ),
                new DuplaController(
                        new DuplaRepositoryMap(
                                DuplaFileXml.getInstance()
                        )
                )
        );
        List<Dupla> duplas = controllerCsv.getAll();

        consultas(controllerCsv);

        saveAllControllers(controllers, duplas);
        //System.out.println("Todos los controllers tienen los mismos datos: " + checkControllers(controllers));

        createInformes(controllers.get(0));
    }

    private static void createInformes(DuplaController duplaController) {
        InformeController controllerJson = new InformeController(new InformeRepositoryMap(InformeFileJson.getInstance()));
        InformeController controllerXml = new InformeController(new InformeRepositoryMap(InformeFileXml.getInstance()));
        List<Informe> informes = InformeFactory.getInstance().createInformesMadrid(duplaController);
        controllerJson.saveAll(informes, true);
        controllerXml.saveAll(informes, true);
        //System.out.println("Todos los informes tienen los mismos datos: " + checkControllersInforme(List.of(controllerJson, controllerXml)));
    }

    /*private static Boolean checkControllers(List<DuplaController> controllers) {
        return controllers.stream().allMatch(c -> c.getAll().equals(controllers.get(0).getAll()));
    }
    private static Boolean checkControllersInforme(List<InformeController> controllers) {
        return controllers.stream().allMatch(c -> c.getAll().equals(controllers.get(0).getAll()));
    }*/

    private static void saveAllControllers(List<DuplaController> controllers, List<Dupla> duplas){
        controllers.forEach(c -> c.saveAll(duplas, true));
    }

    private static void consultas(DuplaController controller){
        System.out.println("Consultas:");
        System.out.println("1. Temperatura máxima por día y lugar:");
        controller.maxTemPorLugar().forEach((k, v) -> {
            System.out.println("Fecha: " + k);
            v.forEach((k1, v1) -> {
                System.out.println("Lugar: " + k1);
                System.out.println("Max: " + v1.getTemMax());
            });
        });
        step();
        System.out.println("2. Temperatura mínima por día y lugar:");
        controller.minTemPorLugar().forEach((k, v) -> {
            System.out.println("Fecha: " + k);
            v.forEach((k1, v1) -> {
                System.out.println("Lugar: " + k1);
                System.out.println("Min: " + v1.getTemMin());
            });
        });
        step();
        System.out.println("3. Temperatura máxima por provincia:");
        controller.maxTemPorProvincia().forEach((k, v) -> {
            System.out.println("Provincia: " + k);
            System.out.println("Max: " + v.getTemMax());
        });
        step();
        System.out.println("4. Temperatura mínima por provincia:");
        controller.minTemPorProvincia().forEach((k, v) -> {
            System.out.println("Provincia: " + k);
            System.out.println("Min: " + v.getTemMin());
        });
        step();
        System.out.println("5. Media de temperatura por provincia:");
        controller.mediaPorProvincia().forEach((k, v) -> {
            System.out.println("Provincia: " + k);
            System.out.println("Media: " + v);
        });
        step();
        System.out.println("6. Media de precipitación por día y provincia:");
        controller.mediaPrecipitacionPorDiaYProvincia().forEach((k, v) -> {
            System.out.println("Fecha: " + k);
            v.forEach((k1, v1) -> {
                System.out.println("Provincia: " + k1);
                System.out.println("Media: " + v1);
            });
        });
        step();
        System.out.println("7. Media de temperatura en Madrid:");
        System.out.println("Media Madrid:" + controller.mediaTemMadrid());
        step();
        System.out.println("8. Media de temperatura máxima:");
        System.out.println("Media máxima:" + controller.mediaTemMax());
        step();
        System.out.println("9. Media de temperatura mínima:");
        System.out.println("Media mínima:" + controller.mediaTemMin());
        step();
        System.out.println("10. Lugares donde la máxima ha sido antes de las 15:00 por día:");
        controller.maxTemAntes().forEach((k, v) -> {
            System.out.println("Fecha: " + k);
            System.out.println("Lugares: " + v);
        });
        step();
        System.out.println("11. Lugares donde la mínima ha sido después de las 17:30 por día:");
        controller.minTemDespues().forEach((k, v) -> {
            System.out.println("Fecha: " + k);
            System.out.println("Lugares: " + v);
        });
    }

    private static void step(){
        System.out.println("========================================");
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error en el sleep");
        }
    }
}