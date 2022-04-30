package converter;

import Model.Measurement;

public interface MeasurementConverter {
    Measurement convert(String dataReceiveData, String data, long ts);
}
