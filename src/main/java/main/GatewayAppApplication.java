package main;

import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.Measurement;
import main.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Date;
import java.time.Instant;

@SpringBootApplication
public class GatewayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);


    }

}
