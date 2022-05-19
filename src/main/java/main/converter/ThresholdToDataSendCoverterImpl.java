package main.converter;

import main.Model.DataSend;
import main.threshold.Threshold;

public class ThresholdToDataSendCoverterImpl implements IThresholdToDataSendCoverter{
    @Override
    public DataSend convertThresholdToData(Threshold threshold) {
        String data = String.valueOf(threshold.getMaxTemp())+ String.valueOf(threshold.getMinTemp()) + String.valueOf(threshold.getMaxHumidity()) + String.valueOf(threshold.getMinHumidity());
        DataSend dataToSend = new DataSend("tx", threshold.getRoomId(), 2, false, data);
        return dataToSend;
    }
}
