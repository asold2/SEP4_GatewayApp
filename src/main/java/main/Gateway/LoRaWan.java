package main.Gateway;

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
    private String url="wss://iotnet.teracom.dk/app?token=vnoUeAAAABFpb3RuZXQudGVyYWNvbS5kawhxYha6idspsvrlQ4C7KWA=";
    PropertyChangeSupport support = null;

    public LoRaWan() {
        support = new PropertyChangeSupport(this);
//        init();
    }

public void init(){
    HttpClient client = HttpClient.newHttpClient();
    CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
            .buildAsync(URI.create(url), this);
    server = ws.join();

}

    //method for sen
    //To send to iot
    public void sendDownLink(String jsonTelegram) throws InterruptedException {
//        Thread.sleep(10000);

        server.sendText(jsonTelegram,true);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=vnoUeAAAABFpb3RuZXQudGVyYWNvbS5kawhxYha6idspsvrlQ4C7KWA="
    // Substitute ????????????????? with the token you have been given

    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);

        try{
        Gson gson = new Gson();
        DataSend toSend = new DataSend("tx", "0004A30B00219CAC", 2, true, "1000100010001000");
        String intended = "";
        intended = gson.toJson(toSend);
        System.out.println(intended + "sdasgfvasdfv");
        sendDownLink(intended);

    }catch (Exception e){
        System.out.println( e + "AAAAAAAAAAA");
    }


    }
//    public void onOpen(WebSocket webSocket) {
//        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
//        webSocket.request(1);
//        System.out.println("WebSocket Listener has been opened for requests.");
//    }

        //onError()
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
        System.out.println("Web socket aborted");
    };
    //onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    };
    //onPing()
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    };
    //onPong()
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    };
    //onText()
    //Need to support.fireEvent()
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = null;
        MeasurementConverter measurementConverter = null;
        Measurement measurement = null;
        try {
            indented = (new JSONObject(data.toString())).toString(4);
            DataReceive dataReceive =  gson.fromJson( indented, DataReceive.class);
//            System.out.println(dataReceive);
            System.out.println(dataReceive.getData() + " !!!!!!!!!!!!");
            measurementConverter = new ConvertMeasurements(dataReceive.getData()); // This data will be the hex


            measurement =  measurementConverter.convert(dataReceive.getData(),dataReceive.getEUI(), dataReceive.getTs());

            if(measurement==null ){
                support.firePropertyChange("error", null, "");
                System.out.println("Error on getting message");
            }
            else{
                support.firePropertyChange("received_measurement", null, measurement);
                System.out.println("Fired received_measurement");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(indented);

        System.out.println(measurement + "???????????????");
        webSocket.request(1);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    };
}
