package main.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataSend implements Serializable {
    @SerializedName("cmd")
    public String cmd;
    @SerializedName("EUI")
    private String EUI;
    @SerializedName("port")
    private int port;
    @SerializedName("confirmed")
    private boolean confirmed;
    @SerializedName("data")
   private String data;

    //Class for serializing in jason telegram for sending back to the iot
    public DataSend(String cmd, String EUI, int port, boolean confirmed, String data) {
        this.cmd = "tx";
        this.EUI = EUI;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }
    public DataSend(){}

    public String getCmd() {
        return cmd;
    }

    public String getEUI() {
        return EUI;
    }

    public int getPort() {
        return port;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getData() {
        return data;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataSend{" +
                "cmd='" + cmd + '\'' +
                ", EUI='" + EUI + '\'' +
                ", port=" + port +
                ", confirmed=" + confirmed +
                ", data='" + data + '\'' +
                '}';
    }
}

