package main.client;

import main.Gateway.ILoRaWan;
import main.Model.DataSend;

public class ThresholdManager implements Runnable{


    private ILoRaWan iLoRaWan;

    public ThresholdManager( ILoRaWan iLoRaWan){

        this.iLoRaWan = iLoRaWan;
    }

    @Override
    public void run() {
while(true){
        System.out.println("RUNNING THREAD");
        try {
            Thread.sleep(60000);
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
