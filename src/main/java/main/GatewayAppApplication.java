package main;

import main.Model.Measurement;
import main.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.sql.Date;
import java.time.Instant;

@SpringBootApplication

public class GatewayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);
        Client client = new Client(new RestTemplateBuilder());
//        System.out.println("Something");
//        client.postMeasurement(new Measurement(new Date(Instant.now().toEpochMilli()),"0004A30B00219CAC", 22.0, 48.0, 1050.0));

    }

}
