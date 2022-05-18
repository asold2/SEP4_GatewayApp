package main.Gateway;

import java.beans.PropertyChangeListener;

public interface ILoRaWan {
    void sendDownLink(String jsontelegram) throws InterruptedException;
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    void init();
}
