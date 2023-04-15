package Ficheros.Aemet.models;

public class Temperature {
    private final Double value;
    private final String time;
    private final String lugar;

    public Temperature(Double value, String time, String lugar) {
        this.value = value;
        this.time = time;
        this.lugar = lugar;
    }

    public Double getValue() {
        return value;
    }
}
