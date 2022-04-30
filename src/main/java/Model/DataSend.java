package Model;

public class DataSend {
   public String cmd;
   private String EUI;
   private int port;
   private boolean confirmed;
   private String data;

    //Class for serializing in jason telegram for sending back to the iot
    public DataSend(String cmd, String EUI, int port, boolean confirmed, String data) {
        this.cmd = "tx";
        this.EUI = EUI;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }

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
}
