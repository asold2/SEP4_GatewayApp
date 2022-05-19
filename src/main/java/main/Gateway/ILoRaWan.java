package main.Gateway;

import java.beans.PropertyChangeListener;
import java.net.http.WebSocket;

public interface ILoRaWan {
    void sendDownLink(String jsontelegram) throws InterruptedException;
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    WebSocket init();
}
