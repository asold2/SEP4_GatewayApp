package main.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {

    private final RestTemplate restTemplate;

    @Value("$(api.url)")
    private static String GET_URL;


    public Client(RestTemplateBuilder restTemplateBuilder) {
        ILoRaWan loRaWan = new LoRaWan();
        loRaWan.addPropertyChangeListener("received_measurement", evt -> postMeasurement((Measurement) evt.getNewValue()));
        this.restTemplate = restTemplateBuilder.build();
    }

    public Measurement postMeasurement(Measurement data) {
        String url = "http://air4you-env-1.eba-cpf6zx99.eu-north-1.elasticbeanstalk.com/measurement/";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("date", data.getDate());
        map.put("roomId", data.getRoomId());
        map.put("temperature", data.getTemperature());
        map.put("humidity", data.getHumidity());
        map.put("co2", data.getCo2());

        HttpEntity<Map<String, Object>> measurement = new HttpEntity<>(map, headers);

        ResponseEntity<Measurement> response = this.restTemplate.postForEntity(url, measurement, Measurement.class);

        System.out.println("Sent measurement to cloud");

        if(response.getStatusCode() == HttpStatus.CREATED){
            return response.getBody();
        } else {
            return null;
        }
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

