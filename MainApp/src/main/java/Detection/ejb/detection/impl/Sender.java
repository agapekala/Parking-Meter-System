package Detection.ejb.detection.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

//wysyłanie informacji o nowym zdarzeniu z sensorów do kolejki
@Stateless
public class Sender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/Alert")
    private Queue queue;

    public void sendEvent(String message){
        TextMessage textMessage=context.createTextMessage(message);
        context.createProducer().send(queue,textMessage);
    }
}
