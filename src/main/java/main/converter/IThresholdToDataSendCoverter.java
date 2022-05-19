package main.converter;

import main.Model.DataSend;
import main.threshold.Threshold;

public interface IThresholdToDataSendCoverter {
    DataSend convertThresholdToData(Threshold threshold);
}
