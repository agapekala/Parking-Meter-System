package Detection.SensorSOAP.sensors;

import javax.xml.ws.Endpoint;


//to już niepotrzebne bo wszystko jest w deployu
public class NewMessagePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8000/NewMessageService", new NewMessageImpl());
    }
}
