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

    public void sendAlert(String message) {
        TextMessage msg = context.createTextMessage(message);
        context.createProducer().send(queue, msg);
    }
}
