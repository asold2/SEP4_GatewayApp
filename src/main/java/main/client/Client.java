package main.client;

import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.DataSend;
import main.Model.Measurement;
import main.converter.IThresholdToDataSendCoverter;
import main.converter.ThresholdToDataSendCoverterImpl;

import main.threshold.Threshold;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {

    private final RestTemplate restTemplate;
    private ILoRaWan loRaWan;
    private ThresholdManager thresholdManager;

    public Client(RestTemplateBuilder restTemplateBuilder) {
        loRaWan = new LoRaWan();

        loRaWan.addPropertyChangeListener("received_measurement", evt -> postMeasurement((Measurement) evt.getNewValue()));
        loRaWan.addPropertyChangeListener("error", evt1 -> sayError((Measurement) evt1.getNewValue()));

        this.restTemplate = restTemplateBuilder.build();

        thresholdManager = new ThresholdManager(loRaWan);


    }

    private void sayError(Measurement newValue) {
        System.out.println(newValue + " !!!!");
    }

    public void postMeasurement(Measurement data) {
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

        ResponseEntity<Threshold> response = this.restTemplate.postForEntity(url, measurement, Threshold.class);
        System.out.println("Sent measurement to cloud");

        IThresholdToDataSendCoverter sender = new ThresholdToDataSendCoverterImpl();
        DataSend finallySending = sender.convertThresholdToData(response.getBody());

        System.out.println(response.getBody());
        System.out.println(finallySending.getData() + " " + finallySending.getEUI());
        loRaWan.setDataSend(finallySending);

    }

}


