package client;

import Gateway.ILoRaWan;
import Gateway.LoRaWan;
import Model.Measurement;
import converter.ConvertMeasurements;
import converter.MeasurementConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Should be an Observer for when the LoraWan receives data from iot.
@RestController
public class Client {

    @Value("$(api.url)")
    private static String GET_URL;

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

    private String sendGetTest() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection)
                obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET RESPONSE CODE :: " + responseCode);

        if(responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()
            ));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("GET request failed");
            return "fail";
        }

    }

    @PostMapping("/test/")
    public void testPost(){
        try {
            System.out.println(sendGetTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

