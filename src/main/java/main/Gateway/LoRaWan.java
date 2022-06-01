package main.Gateway;

//import jdk.swing.interop.SwingInterOpUtils;
import main.Model.DataReceive;
import main.Model.DataSend;
import main.Model.Measurement;
import com.google.gson.Gson;
import main.converter.ConvertMeasurements;
import main.converter.MeasurementConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class LoRaWan implements WebSocket.Listener, ILoRaWan {
    private WebSocket server = null;
    Gson gson = new Gson();
    static private String url="wss://iotnet.teracom.dk/app?token=vnoUeAAAABFpb3RuZXQudGVyYWNvbS5kawhxYha6idspsvrlQ4C7KWA=";
    PropertyChangeSupport support = null;
    private DataSend dataSend = new DataSend();

    public LoRaWan() {
        support = new PropertyChangeSupport(this);
        setDataSend(new DataSend("tx", "0004A30B00219CAC", 2, false, "0000000000000000"));
        init();
    }

    public DataSend getDataSend() {
        return dataSend;
    }

    public void setDataSend(DataSend dataSend) {
        this.dataSend = dataSend;
    }

    public void init(){
        System.out.println("Calling init");
    HttpClient client = HttpClient.newHttpClient();
    CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
            .buildAsync(URI.create(url), this);
    server =  ws.join();

}

    //method for sen
    //To send to iot
    public void sendDownLink(String jsonTelegram) throws InterruptedException {

        server.sendText(jsonTelegram,true);
        System.out.println("I JUST SEND DATA  " + jsonTelegram);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }


    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
//    WebSocket.Listener.super.onOpen(webSocket);
    }



    public void onError(WebSocket webSocket, Throwable error) {
        webSocket.abort();
    };

    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {

        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);

    };

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    };
    //onPong()
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    };


    public synchronized void send(){

        System.out.println("Data inside Lorawan : " + getDataSend().toString());
        if(getDataSend()!=null){
            System.out.println("Still not ready");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", getDataSend().getData());
            jsonObject.put("port", 2);
            jsonObject.put("EUI", getDataSend().getEUI());
            jsonObject.put("cmd", "tx");
                sendDownLink(jsonObject.toString());

            } catch (JSONException | InterruptedException e) {
                e.printStackTrace();
        }
        }

    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = null;
        System.out.println("       CALLING ONtEXT           ");
        boolean temp = false;
        MeasurementConverter measurementConverter = null;
        Measurement measurement = null;

            try {
                indented = (new JSONObject(data.toString())).toString(4);
                System.out.println("INTENDED" + indented );
                DataReceive dataReceive = gson.fromJson(indented, DataReceive.class);
                measurementConverter = new ConvertMeasurements(dataReceive.getData()); // This data will be the hex

                if(last){
                    measurement = measurementConverter.convert(dataReceive.getData(), dataReceive.getEUI(), dataReceive.getTs());
                    if (measurement == null) {
                        support.firePropertyChange("error", null, new Measurement());
                        System.out.println("Error on getting message");
                    } else {
                        support.firePropertyChange("received_measurement", null, measurement);
                        System.out.println("Fired received_measurement");
                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Error fired for Json Property");}
        indented = "";

        webSocket.request(1);
        return CompletableFuture.completedFuture("onText() completed.").thenAccept(System.out::println);
//    return null;

        // Test
    }
}
