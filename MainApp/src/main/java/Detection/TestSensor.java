package Detection;

import Database.Entities.Spot;
import sensors.Event;
import sensors.NewMessageImplService;
import sensors.NewMessageInt;

import java.net.URL;

//ta klasa odpowiada za sprawdzanie czy zostało wykryte jakieś zdarzenie przez sensor
//domyślnie funkcja powinna być odpalana z deploya a nie z maina
public class TestSensor {
    public static void main(String[] args)throws Exception {
        URL url =new URL("http://localhost:8000/NewMessageService?wsdl");

        NewMessageImplService message_service=new NewMessageImplService(url);
        NewMessageInt proxy=message_service.getNewMessageImplPort();
        while(true) {
            Event event=proxy.sendMessage();
            System.out.println("Wykryto nowe zdarzenie:");
            System.out.println("Typ: "+event.getEventType());
            System.out.println("Miejsce/Strefa: "+event.getSpot()+"/"+event.getZone());
            System.out.println("Godzina: "+event.getEventTime());


            //w tym miejscu zamiast wypisywania może być updateowanie bazy danych - zmiana miejsca na zajęte/zwolnione
            //+godzina zajęcia miejsca
            Spot spot=new Spot();
            spot.setSpot_id(event.getSpot());


            if(event.getEventType().equals("Nowe auto")){
                spot.setOccupied(true);
            }else{
                spot.setOccupied(false);
            }
        }
    }
}
