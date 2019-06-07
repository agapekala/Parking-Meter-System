package Detection.ejb.detection.impl;

import Alert.AlertSender;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.*;

//odczytywanie wiadomości z kolejki
//Wiadomość jest przechwytywana w momencie wysałania
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName =
        "destinationType", propertyValue =
        "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination"
                ,propertyValue = "java:/jboss/exported/jms/queue/Alert")})

public class QueueListener implements MessageListener {

    @EJB
    private AlertSender alertSender;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("Message received in module Main: " + textMessage.getText());

            //wysłanie komunikatu dalej do modułu odpowiadającego za powiadomianie
            //urządzeń mobilnych
            alertSender.sendAlert(textMessage.getText());
        } catch (JMSException e) {
            System.out.println(
                    "Error while trying to consume messages: " + e.getMessage());
        }
    }
}
