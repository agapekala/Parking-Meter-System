package sensors;

import javax.xml.ws.Endpoint;

public class NewMessagePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8000/NewMessageService", new NewMessageImpl());
    }
}
