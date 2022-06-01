package main.client;

import main.Gateway.ILoRaWan;
import main.Model.DataSend;

public class ThresholdManager{


    private ILoRaWan iLoRaWan;

    public ThresholdManager( ILoRaWan iLoRaWan){
        this.iLoRaWan = iLoRaWan;
    }


    public void runSender() {

        while(true){
            System.out.println("RUNNING THREAD");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("THREAD SLEEP DONE");
            iLoRaWan.send();
        }

    }


    public ILoRaWan getiLoRaWan() {
        return iLoRaWan;
    }

    public void setiLoRaWan(ILoRaWan iLoRaWan) {
        this.iLoRaWan = iLoRaWan;
    }
}
