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
        this.data = data;
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

        return new Measurement( EUI, 1, 1, 1,new Date(ts));
    }
}
