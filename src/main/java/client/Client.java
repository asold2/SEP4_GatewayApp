package client;

import Gateway.ILoRaWan;
import Gateway.LoRaWan;
import Model.Measurement;
import converter.ConvertMeasurements;
import converter.MeasurementConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {
    private String url="localhost:5000";
    private ILoRaWan loRaWan = new LoRaWan();
    private MeasurementConverter measurementConverter;

    public Client(){
        loRaWan.addPropertyChangeListener("received_measurement", evt ->postMeasurement((Measurement) evt.getNewValue()));
    }

    //Add event to the paramter of the method.
    private void postMeasurement(Measurement data)
    {
        System.out.println("Reached the method in Client!!!!");
//WE RECEIVE BACK THE JSON WITH THRESHOLDS, DESERIALIZE THEM, AND SEND THEM TO IOT.

        System.out.println(data.toString());

        //Todo
        //http request

        //Todo
        //receive back null or new thresholds depending on the time you check in data server
    }


}

