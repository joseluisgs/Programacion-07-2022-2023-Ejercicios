package org.example;

import model.Accidente;
import model.ConjuntoDeAccidentes;
import model.Distrito;
import storage.accidentes.AccidenteStorageService;
import storage.accidentes.AccidenteStorageServiceCSV;
import storage.accidentes.AccidenteStorageServiceJson;
import storage.accidentes.AccidenteStorageServiceXml;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        AccidenteStorageService storageCSV = new AccidenteStorageServiceCSV();

        List<Accidente> accidentes = storageCSV.loadAll();
        for(int i = 0; i<10; i++){
            // System.out.println(accidentes.get(i));
        }

        System.out.println();

        ConjuntoDeAccidentes conjuntoDeAccidentes = new ConjuntoDeAccidentes(accidentes);
        
        // almacenarEnUnFicheroJson(conjuntoDeAccidentes);

        System.out.println();

        // almacenarEnUnFicheroXml(conjuntoDeAccidentes);

        System.out.println();

        System.out.println("Empezamos con las consultas:");
        System.out.println();

        System.out.println("Accidentes en los que estan implicados alcohol o drogas:");
        int entero1 = accidentes.stream().filter(a -> a.getEsPositivaEnAlchol() || a.getEsPositivaEnDrogas()).toList().size();
        System.out.println(entero1);
        System.out.println();

        System.out.println("Numero de accidentes que han dado positivo en alcohol y drogas:");
        int entero2 = accidentes.stream().filter(a -> a.getEsPositivaEnAlchol() && a.getEsPositivaEnDrogas()).toList().size();
        System.out.println(entero2);
        System.out.println();

        System.out.println("Accidentes agrupados por sexo:");
        Map<Object, Long> mapa1 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getSexo(), Collectors.counting()));
        mapa1.keySet().forEach(key ->
                System.out.println(key+"--"+mapa1.get(key))
        );
        System.out.println();

        System.out.println("Accidentes agrupados por meses:");
        Map<Object, Long> mapa2 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getFecha().getMonth(), Collectors.counting()));
        mapa2.keySet().forEach(key ->
                System.out.println(key+"--"+mapa2.get(key))
        );
        System.out.println();

        System.out.println("Accidentes agrupados por tipos de vehiculos:");
        Map<Object, Long> mapa3 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getTipoDeVehiculo(), Collectors.counting()));
        mapa3.keySet().forEach(key ->
                System.out.println(key+"--"+mapa3.get(key))
        );
        System.out.println();

        System.out.println("Accidentes ocurridos en la calle de leganes:");
        int entero3 = accidentes.stream().filter(a -> a.getLocalizacion().stream().filter(l -> l.toLowerCase().contains("leganes")).toList().size() >= 1).toList().size();
        System.out.println(entero3);
        System.out.println();

        System.out.println("Numero de accidentes por distrito:");
        Map<Object, Long> mapa4 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getDistrito().getNombre(), Collectors.counting()));
        mapa4.keySet().forEach(key ->
                System.out.println(key+"--"+mapa4.get(key))
        );
        System.out.println();

        System.out.println("Estadisticas por distrito:");
        Integer totalAccidentes = accidentes.size();
        Map<Object, Long> mapa5 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getDistrito().getNombre(), Collectors.counting()));
        Map<Object, Double> mapa6 = new HashMap<>();
        mapa5.keySet().forEach(k ->
                        mapa6.put(k, Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(Integer.parseInt(mapa5.get(k).toString())*100))/totalAccidentes)))
                );
        mapa6.keySet().forEach(key ->
                System.out.println(key+"--"+String.format("%.2f", mapa6.get(key))+"%")
        );
        System.out.println();

        System.out.println("Accidentes por distrito ordenadas de manera descendente:");
        List<String> lista = accidentes.stream().collect(Collectors.groupingBy(a -> a.getDistrito().getNombre())).keySet().stream().sorted(Comparator.comparing(a -> String.valueOf(a), Comparator.reverseOrder())).toList();
        Map<Object, Long> mapa7 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getDistrito().getNombre(), Collectors.counting()));
        Map<Object, Long> mapa8 = new LinkedHashMap<>();
        lista.forEach(k ->
                mapa8.put(k, mapa7.get(k))
        );
        mapa8.keySet().forEach(key ->
                System.out.println(key+"--"+mapa8.get(key))
        );
        System.out.println();

        System.out.println("Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana):");
        List<Accidente> lista1 = accidentes.stream().filter(a ->
                !(a.getHora().getHour() < 20 && a.getHora().getHour() > 6) && a.getFecha().getDayOfWeek().compareTo(DayOfWeek.FRIDAY) > 0 && a.getFecha().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) < 0
        ).toList();
        for(int i=0; i<10; i++){
            System.out.println(lista1.get(i)+"\n");
        }
        System.out.println();

        System.out.println("Listado de accidentes que se produzcan en fin de semana y de noche donde se haya dado positivo en alchol:");
        List<Accidente> lista2 = accidentes.stream().filter(a ->
                !(a.getHora().getHour() < 20 && a.getHora().getHour() > 6) && a.getFecha().getDayOfWeek().compareTo(DayOfWeek.FRIDAY) > 0 && a.getFecha().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) < 0 && a.getEsPositivaEnAlchol()
        ).toList();
        for(int i=0; i<10; i++){
            System.out.println(lista2.get(i)+"\n");
        }
        System.out.println();

        System.out.println("Accidentes donde haya mas de un fallecido:");
        List<Accidente> lista3 = accidentes.stream().filter(a -> a.getLesividad().getLesividad().toLowerCase().contains("fallecido")).toList();
        for(int i=0; i<10; i++){
            System.out.println(lista3.get(i)+"\n");
        }
        System.out.println();

        System.out.println("Comprobar si el distrito con mas accidentes coincide con el distrito donde hay más accidentes en fin de semana:");
        System.out.println("Distrito con mas accidentes en general:");

        Map<Object, Long> mapa9 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getDistrito(), Collectors.counting()));

        final Distrito[] distritos = {null, null};
        AtomicReference<Long> finalNumAccidentes = new AtomicReference<>(0L);
        mapa9.keySet().forEach(k -> {
            if (finalNumAccidentes.get() < mapa9.get(k)) {
                finalNumAccidentes.set(mapa9.get(k));
                distritos[0] = (Distrito) k;
            }
        }
        );
        System.out.println(distritos[0]);
        System.out.println("Distrito con mas accidentes durante los fines de semana:");

        Map<Object, Long> mapa10 = accidentes.stream().filter(a ->a.getFecha().getDayOfWeek().compareTo(DayOfWeek.FRIDAY) > 0 && a.getFecha().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) < 0).collect(Collectors.groupingBy(a -> a.getDistrito(), Collectors.counting()));

        AtomicReference<Long> finalNumAccidentes1 = new AtomicReference<>(0L);
        mapa10.keySet().forEach(k -> {
            if (finalNumAccidentes1.get() < mapa10.get(k)) {
                finalNumAccidentes1.set(mapa10.get(k));
                distritos[1] = (Distrito) k;
            }
        }
        );
        System.out.println(distritos[1]);
        if(distritos[0].equals(distritos[1])){
            System.out.println("Como podemos ver, si son el mismo.");
        }else{
            System.out.println("Como podemos ver, no son el mismo.");
        }
        System.out.println();

        System.out.println("Numero de accidentes donde ha habido (alcohol o drogas) y victimas mortales:");
        int entero4 = accidentes.stream().filter(a ->
                a.getLesividad().getLesividad().toLowerCase().contains("fallecido")
                && (a.getEsPositivaEnDrogas() || a.getEsPositivaEnAlchol())
        ).toList().size();
        System.out.println(entero4);
        System.out.println();

        System.out.println("Numero de accidentes donde ha habido atropellos a personas:");
        int entero5 = accidentes.stream().filter(a ->
                a.getTipoAccidente().toLowerCase().contains("atropello")
                        && a.getTipoAccidente().toLowerCase().contains("persona")
        ).toList().size();
        System.out.println(entero5);
        System.out.println();

        System.out.println("Accidentes agrupados por estado metereológio:");
        Map<Object, Long> mapa11 = accidentes.stream().collect(Collectors.groupingBy(a -> a.getEstadoMeteorologico(), Collectors.counting()));
        mapa11.keySet().forEach(key ->
                System.out.println(key+"--"+mapa11.get(key))
        );
        System.out.println();

        System.out.println("Lista de accidentes donde haya habido un atropello animal:");
        List<Accidente> lista4 = accidentes.stream().filter(a ->
                a.getTipoAccidente().toLowerCase().contains("atropello")
                        && a.getTipoAccidente().toLowerCase().contains("animal")
        ).toList();
        for(int i=0; i<10; i++){
            System.out.println(lista4.get(i)+"\n");
        }
        System.out.println();

    }

    private static void almacenarEnUnFicheroJson(ConjuntoDeAccidentes conjuntoDeAccidentes) throws Exception {
        AccidenteStorageService json = new AccidenteStorageServiceJson();

        json.saveAll(conjuntoDeAccidentes.getAccidentes());

        List<Accidente> lista = json.loadAll();
        for(int i = 0; i<10; i++){
            System.out.println(lista.get(i));
        }
    }

    private static void almacenarEnUnFicheroXml(ConjuntoDeAccidentes conjuntoDeAccidentes) throws Exception {
        //Todo este proceso termina en un error de casteo al intentar castear algo a una colleccion, pero no consigo solucionarlo.
        //Update, si le paso directamente el objeto ListaAccidentesDto, si que funciona
        AccidenteStorageService json = new AccidenteStorageServiceXml();

        json.saveAll(conjuntoDeAccidentes.getAccidentes());

        List<Accidente> lista = json.loadAll();
        for(int i = 0; i<10; i++){
            System.out.println(lista.get(i));
        }
    }
}
