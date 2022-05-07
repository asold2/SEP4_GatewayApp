package main.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {

    @Value("$(api.url)")
    private static String GET_URL;


    public Client() {
        ILoRaWan loRaWan = new LoRaWan();
        loRaWan.addPropertyChangeListener("received_measurement", evt -> postMeasurement((Measurement) evt.getNewValue()));

    }


    public void postMeasurement(Measurement data) {
        String requestBody = "";
        var objectMapper = new ObjectMapper();
        try {
            requestBody = objectMapper
                    .writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest reuqest = HttpRequest.newBuilder()
//                .uri(URI.create("http://air4you-env-1.eba-cpf6zx99.eu-north-1.elasticbeanstalk.com/measurement/"))
                .uri(URI.create("http://localhost:8082/measurement/"))

                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(reuqest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Tell me it's sending");
            System.out.println(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());





    }

    private String sendGetTest() throws IOException {
        URL obj = new URL("http://air4you-env-1.eba-cpf6zx99.eu-north-1.elasticbeanstalk.com/measurement/");
        HttpURLConnection con = (HttpURLConnection)
                obj.openConnection();
        con.setRequestMethod("POST");
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
            System.out.println("POST request failed");
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

