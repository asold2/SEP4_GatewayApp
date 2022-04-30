package converter;

import Model.DataReceive;
import Model.Measurement;
import converter.MeasurementConverter;

import javax.swing.*;
import java.util.Date;

public class ConvertMeasurements implements MeasurementConverter {
    private String data;
    private Measurement measurement;
    private DataReceive model;

    public ConvertMeasurements(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    //Send to the data server
    public Measurement convert(String data) {
        //////


        return new Measurement(model.getCmd(), 1, 1, 1, new Date(model.getTs()));
    }
}
