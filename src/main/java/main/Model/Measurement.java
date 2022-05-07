package main.Model;

import java.io.Serializable;
import java.util.Date;

public class Measurement implements Serializable {
    //this is the EUI but had to rename to roomID
    private String roomId;
    private double temperature;
    private double humidity;
    private double co2;
    private Date date;

    public Measurement(Date date, String roomId, double temperature, double humidity, double co2) {
        this.date = date;
        this.roomId = roomId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public String getRoomId() {
        return roomId;
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
                "roomId='" + roomId + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", date=" + date +
                '}';
    }
}
