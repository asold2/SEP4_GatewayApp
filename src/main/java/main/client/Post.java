package main.client;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private Date date;
    private String roomId;
    private double temperature;
    private double humidity;
    private double co2;
}
