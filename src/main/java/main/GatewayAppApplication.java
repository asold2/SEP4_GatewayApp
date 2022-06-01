package main;

import main.Gateway.ILoRaWan;
import main.Gateway.LoRaWan;
import main.Model.Measurement;
import main.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.time.Instant;

@SpringBootApplication
public class GatewayAppApplication {





    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);
//        loRaWan.init();
//        Client client = new Client(new RestTemplateBuilder(), loRaWan);

//        printshit();
    }

//    @Scheduled(initialDelay = 100000, fixedRate = 30000)
//    public static void printshit(){
//            loRaWan.send();
//    }

}
