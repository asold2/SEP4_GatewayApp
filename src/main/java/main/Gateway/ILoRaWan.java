package main.Gateway;

import main.Model.DataSend;

import java.beans.PropertyChangeListener;
import java.net.http.WebSocket;

public interface ILoRaWan {
    void sendDownLink(String jsontelegram) throws InterruptedException;
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    void init();
    void send();
    void setDataSend(DataSend dataSend);
    DataSend getDataSend();
}
