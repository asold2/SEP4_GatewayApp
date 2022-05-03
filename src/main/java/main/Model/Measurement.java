package main.Model;

import java.util.Date;

public class Measurement {
    private String EUI;
    private double temperature;
    private double humidity;
    private double co2;
    private Date date;

    public Measurement(String EUI, double temperature, double humidity, double co2, Date date) {
        this.EUI = EUI;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.date = date;
    }

    public String getEUI() {
        return EUI;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getCo2() {
        return co2;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "EUI='" + EUI + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", date=" + date +
                '}';
    }
}
