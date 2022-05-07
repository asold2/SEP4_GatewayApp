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
        //////
//logic to convert the hexadecimal from String data
        int chunksize = 4;
        String[] chunks = data.split("(?<=\\G.{"+chunksize+"})");
        System.out.println(data.substring(0,4));
        System.out.println(chunks[1]);
        System.out.println(chunks[2]);


        int co2 = Integer.parseInt(data.substring(0,4), 16);
        System.out.println(co2 + "aaaaa");
        System.out.println(data.substring(4, 8) + "Temperature");
        System.out.println(data.substring(8, 12) + "Humidity");


        int temperature = Integer.parseInt(data.substring(4, 8), 16);
        int humidity = Integer.parseInt(data.substring(8, 12), 16);

        System.out.println(temperature + "bbbb");
        System.out.println(humidity + "ccccc");


        Measurement measurement = new Measurement( EUI, temperature, humidity, co2,new Date(ts));
        System.out.println(measurement.toString() + "Heeeeererere is the measurment");
        return measurement;
    }
}
