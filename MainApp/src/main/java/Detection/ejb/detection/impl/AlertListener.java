package Detection.ejb.detection.impl;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

//klasa już niepotrzebna, można potem wyrzucić
//zastąpiona QueueListener, który odbiera powiadomienie od razu, tutaj wiadomości były czytane co minutę
@Singleton
public class AlertListener {

//    @EJB
//    QueueListener queueListener;

    //@Schedule(minute = "*/1", hour = "*", persistent = false)
    //@Schedule( second = "*/20", minute = "*", hour = "*", persistent = false)
//    public void getMessage() throws Exception{
//        queueListener.receiveAlert();
//    }

}
