import converter.MeasurementConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {
    private String url="localhost:5000";

    private MeasurementConverter measurementConverter;


    //Add event to the paramter of the method.
    private void postMeasurement()
    {

        measurementConverter.convert("");

        //Todo
        //http request

        //Todo
        //receive back null or new thresholds depending on the time you check in data server
    }


}
