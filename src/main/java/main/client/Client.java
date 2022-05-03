package main.client;

import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {

    @Value("$(api.url)")
    private static String GET_URL;


    public Client() {
        ILoRaWan loRaWan = new LoRaWan();
        loRaWan.addPropertyChangeListener("received_measurement", evt -> postMeasurement((Measurement) evt.getNewValue()));

    }

    //Add event to the paramter of the method.
    private void postMeasurement(Measurement data) {
        System.out.println("Reached the method in Client!!!!");
//WE RECEIVE BACK THE JSON WITH THRESHOLDS, DESERIALIZE THEM, AND SEND THEM TO IOT.

        System.out.println(data.toString());

        //Todo
        //http request


        //Todo
        //receive back null or new thresholds depending on the time you check in data server
    }

    private String sendGetTest() throws IOException {
        URL obj = new URL("http://air4you-env-1.eba-cpf6zx99.eu-north-1.elasticbeanstalk.com/");
        HttpURLConnection con = (HttpURLConnection)
                obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET RESPONSE CODE :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()
            ));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("GET request failed");
            return "fail";
        }

    }

    @GetMapping("/")
    public String testPost() {
        try {
            return sendGetTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}

