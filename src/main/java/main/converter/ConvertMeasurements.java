package main.converter;

import main.Model.DataReceive;
import main.Model.Measurement;

import java.util.Date;


public class ConvertMeasurements implements MeasurementConverter {
    private String data;
    //s
    private Measurement measurement;
    private DataReceive model;

    public ConvertMeasurements(String dataReceiveData) {
        this.data = dataReceiveData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    //Send to the data server
    public Measurement convert(String data, String EUI, long ts) {
        int co2 = Integer.parseInt(data.substring(0,4), 16);
        int temperature = Integer.parseInt(data.substring(4, 8), 16);
        int humidity = Integer.parseInt(data.substring(8, 12), 16);



        double tempToSend = (double)temperature/10;
        double humidityToSend = (double)humidity/10;
        System.out.println(co2 + "!!!!!!!!!!!!!!!!!!!");
        System.out.println(tempToSend + "!!!!!!!!!!!!!!!!!!!");

        System.out.println(humidityToSend + "!!!!!!!!!!!!!!!!!!!");


        Measurement measurement = new Measurement(new Date(ts), EUI, tempToSend, humidityToSend, co2);
        return measurement;
    }
}
