import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//odbiera powiadomienia z kolejki
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName =
        "destinationType", propertyValue =
        "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination"
                ,propertyValue = "java:/jboss/exported/jms/queue/MobileQueue")})
public class AlertListener implements MessageListener{

    @EJB
    private RestSender restSender;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage=(TextMessage)message;
        try {
            System.out.println("Message received in module Alert: " + textMessage.getText());

            //wysłanie komunikatu dalej do modułu odpowiadającego za powiadomianie
            //urządzeń mobilnych
            restSender.sendAlert(textMessage.getText());
        } catch (JMSException e) {
            System.out.println(
                    "Error while trying to consume messages: " + e.getMessage());
        }
    }
}
