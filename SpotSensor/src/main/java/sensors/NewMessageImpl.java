package sensors;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Future;

@WebService(endpointInterface = "sensors.NewMessageInt")
public class NewMessageImpl implements NewMessageInt{

    @Override
    @WebMethod
    public Event sendMessage() {
        Event event=new Event();
        System.out.println("Wybierz rodzaj zdarzenia: ");
        System.out.println("1 - nowe auto");
        System.out.println("2 - zwolnienie miejsca");

        Scanner myObj = new Scanner(System.in);
        String event_type = myObj.nextLine();
        if(Integer.parseInt(event_type)==1){
            event.setEvent_type("Nowe auto");
        }else if(Integer.parseInt(event_type)==2){
            event.setEvent_type("Zwolniono miejsce");
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
        String time=myObj.nextLine();
        event.setEvent_time(time);
        return event;
//        try {
//            Thread.sleep(sleep_time); //Sleep for random time (2s-10s)
//
////            Random rr=new Random();
////            int event=rr.nextInt(1)+1;
////            switch (event){
////                case 1:
////                    return "Nowy samochód";
////                case 2:
////                    return "Zwolnienie miejsca";
////            }
//            return "Nowy zdarzenie";
//        }
//        catch(InterruptedException ex) {
//            System.err.println("exception: "+ex);
//            // Exception Handling code here..
//        }
    }

}
