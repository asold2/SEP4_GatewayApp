package main.Gateway;

import main.Model.DataSend;

import java.beans.PropertyChangeListener;
import java.net.http.WebSocket;

public interface ILoRaWan {
    void sendDownLink(String jsontelegram) throws InterruptedException;
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    WebSocket init();
    void send(DataSend dataToSend);
}
