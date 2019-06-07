package Alert;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

//wysyłą przez kolejkę jms powiadomienia do modułu Alert ]
@Stateless
public class AlertSender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/MobileQueue")
    private Queue queue;

    public void sendAlert(String alert){
        TextMessage textMessage=context.createTextMessage(alert);
        context.createProducer().send(queue,textMessage);
    }

}
