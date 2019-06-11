import org.apache.activemq.artemis.utils.json.JSONObject;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;


//wysyła komunikat do kolejki
@Stateless
public class Sender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/Alert")
    private Queue queue;

    public void sendAlert(String message, int spot_id, int zone_id) {
        try {
            String jsonMessage ="{\"message\":"+message+", \"spot_id\":"+spot_id+", \"zone_id\":"+zone_id+"}";
            TextMessage msg = context.createTextMessage(jsonMessage);
            context.createProducer().send(queue, msg);
        }catch (Exception e){
            System.out.println("Błąd przy wysyłaniu powiadomienia: "+e);
        }
    }
}
