package main.converter;

import main.Model.Measurement;

public interface MeasurementConverter {
    Measurement convert(String dataReceiveData, String data, long ts);
}
