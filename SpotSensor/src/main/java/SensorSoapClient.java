import detection.sensorsoap.sensors.Event;
import detection.sensorsoap.sensors.NewMessageImplService;
import detection.sensorsoap.sensors.NewMessageInt;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class SensorSoapClient {
    public static void main(String[] args)throws Exception {
        URL url =new URL("http://localhost:8080/new_message_service?wsdl");

        NewMessageImplService message_service=new NewMessageImplService(url);
        NewMessageInt proxy=message_service.getNewMessageImplPort();

        while(true) {
            Event event=new Event();
            System.out.println("Wybierz rodzaj zdarzenia: ");
            System.out.println("1 - nowe auto");
            System.out.println("2 - zwolnienie miejsca");

            Scanner myObj = new Scanner(System.in);
            String event_type = myObj.nextLine();
            if(Integer.parseInt(event_type)==1){
                event.setEventType("Nowe auto");
            }else if(Integer.parseInt(event_type)==2){
                event.setEventType("Zwolniono miejsce");
            }else{
                System.out.println("Opcja niedostępna");
            }
            System.out.println("Strefa: ");
            String zone=myObj.nextLine();

            event.setZone(Integer.parseInt(zone));

            System.out.println("Miejsce: ");
            String spot=myObj.nextLine();
            event.setSpot(Integer.parseInt(spot));

            System.out.println("Godzina: ");
           // String time=myObj.nextLine();
            Date currentDate = new Date();
            //DateFormat dateFormat= new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            //String time=dateFormat.format(currentDate);
            event.setEventTime(currentDate);
            proxy.sendMessage(event);

        }
    }
}
