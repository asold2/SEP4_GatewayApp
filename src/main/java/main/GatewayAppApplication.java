package main;

import main.Model.Measurement;
import main.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication

public class GatewayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);
        Client client = new Client();
        System.out.println("Something");
        client.postMeasurement(new Measurement("0004A30B00219CAC", 22.0, 48.0, 1050.0, null));


    }

}
