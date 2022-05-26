package main.client;

import main.Gateway.ILoRaWan;
import main.Model.DataSend;

public class ThresholdManager implements Runnable{


    private ILoRaWan iLoRaWan;

    public ThresholdManager( ILoRaWan iLoRaWan){

        this.iLoRaWan = iLoRaWan;
        run();
    }

    @Override
    public void run() {
while(true){
//    if(!iLoRaWan.getDataSend().getData().equals("0000000000000000")){
//    while(!iLoRaWan.getDataSend().getData().equals("0000000000000000")){
        System.out.println("RUNNING THREAD");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("THREAD SLEEP DONE");

        iLoRaWan.send();
//}
    }
//}
    }


    public ILoRaWan getiLoRaWan() {
        return iLoRaWan;
    }

    public void setiLoRaWan(ILoRaWan iLoRaWan) {
        this.iLoRaWan = iLoRaWan;
    }
}
