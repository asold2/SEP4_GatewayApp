package main.Gateway;

import java.beans.PropertyChangeListener;

public interface ILoRaWan {
    void sendDownLink(String jsontelegram);
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
}
