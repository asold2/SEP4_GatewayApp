package main.tempThreshold;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempThresholdController {

    @PostMapping("/send/tempThreshold/")
    public void sendTempThresholdToIoT(@RequestBody TemperatureThreshold temperatureThreshold){
        System.out.println(temperatureThreshold);
    }

}

