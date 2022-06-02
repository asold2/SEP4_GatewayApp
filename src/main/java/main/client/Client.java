package main.client;

import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.DataSend;
import main.Model.Measurement;
import main.converter.IThresholdToDataSendCoverter;
import main.converter.ThresholdToDataSendCoverterImpl;

import main.threshold.Threshold;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
        this.loRaWan = new LoRaWan();

        loRaWan.addPropertyChangeListener("received_measurement", evt -> postMeasurement((Measurement) evt.getNewValue()));
        loRaWan.addPropertyChangeListener("error", evt1 -> sayError((Measurement) evt1.getNewValue()));

        this.restTemplate = restTemplateBuilder.build();


//        thresholdManager = new ThresholdManager(loRaWan);
//        thresholdManager.runSender();

    }



    private void sayError(Measurement newValue) {
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
        System.out.println("Received Measurement");
        this.restTemplate.postForLocation(url, measurement);
        System.out.println("Sent measurement to cloud");
    }


    //Receiving a threshold from air4you every 4 minutes
    @PostMapping("/send/tempThreshold/")
    public void getThresholdfromAir4You(@RequestBody Threshold threshold){

        IThresholdToDataSendCoverter sender = new ThresholdToDataSendCoverterImpl();
        DataSend finallySending = sender.convertThresholdToData(threshold);

        loRaWan.setDataSend(finallySending);
        System.out.println(threshold.toString() + "        THRESHOLD              ");
        System.out.println(finallySending.toString() + "                            ");

        loRaWan.send();
        return;
    }

}


