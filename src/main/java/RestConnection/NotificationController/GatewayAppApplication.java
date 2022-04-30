package RestConnection.NotificationController;

import Gateway.LoRaWan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class GatewayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);
        LoRaWan loRaWan=new LoRaWan();

    }

}
