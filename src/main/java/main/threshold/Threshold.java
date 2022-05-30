package main.threshold;

import javax.persistence.Id;
import java.io.Serializable;

public class Threshold implements Serializable {

    @Id
    private int thresholdId;
    private String roomId = "none";
    private int minTemp =0 ;

    private int maxTemp =0;

    private int minHumidity =0 ;

    private int maxHumidity  = 0;

    public Threshold( String roomId, int minTemp, int maxTemp, int minHumidity, int maxHumidity) {
        this.roomId = roomId;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;

    }

    public Threshold(){}


    public int getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(int thresholdId) {
        this.thresholdId = thresholdId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(int minHumidity) {
        this.minHumidity = minHumidity;
    }

    public int getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(int maxHumidity) {
        this.maxHumidity = maxHumidity;
    }
}

