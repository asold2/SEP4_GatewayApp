package main.converter;

import main.Model.DataSend;
import main.threshold.Threshold;

public class ThresholdToDataSendCoverterImpl implements IThresholdToDataSendCoverter{
    @Override
    public DataSend convertThresholdToData(Threshold threshold) {


//
//        String hex = String.format("%04X",(int)threshold.getMaxTemp()) +
//                String.format("%04X", (int)threshold.getMinTemp()) +
//                String.format("%04X", (int)threshold.getMaxHumidity()) +
//                String.format("%04X", (int)threshold.getMinHumidity());



        String hex = String.format("%04X",(int)threshold.getMaxTemp()) +
                String.format("%04X", (int)threshold.getMinTemp()) +
                String.format("%04X", (int)threshold.getMaxHumidity()) +
                String.format("%04X", (int)threshold.getMinHumidity());

        DataSend dataToSend = new DataSend("tx", threshold.getRoomId(), 2, false, hex);
        return dataToSend;
    }
}
