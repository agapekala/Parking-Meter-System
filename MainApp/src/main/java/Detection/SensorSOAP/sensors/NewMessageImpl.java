package Detection.SensorSOAP.sensors;



import Detection.ejb.detection.impl.ReceiveEvent;
import Entities.Spot;
import dbservice.ISpotService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;


//Implementacja logiki soap; odbiera kounikaty o nowych zdarzeniach i wysyła do
//bazy danych
@WebService(endpointInterface = "Detection.SensorSOAP.sensors.NewMessageInt")
public class NewMessageImpl implements NewMessageInt{

    private final String jndi="java:global/MainApp-1.0-SNAPSHOT/ReceiveEvent";

    @Override
    @WebMethod
    public void sendMessage(@WebParam Event event){

        try {
            Spot spot = new Spot();
            spot.setSpot_id(event.getSpot());
            if(event.getEvent_type().equals("Nowe auto")){
                spot.setEntry_time(event.getEvent_time());
                spot.setOccupied(true);
            }else if(event.getEvent_type().equals("Zwolniono miejsce")){
                spot.setOccupied(false);
                spot.setDuration(null);
                spot.setEntry_time(null);
                spot.setPayment_time(null);
                spot.setExpiration_time(null);
            }else{
                System.out.println("Nie wiem co robić");
            }

            //EJB
            ReceiveEvent receiver=(ReceiveEvent) new InitialContext().lookup(jndi);
            receiver.receiveSensorEvent(spot, event.getEvent_type());
//            System.out.println("Wykryto nowe zdarzenie:");
//            System.out.println("Typ: " + event.getEvent_type());
//            System.out.println("Miejsce/Strefa: " + event.getSpot() + "/" + event.getZone());
//            System.out.println("Godzina: " + event.getEvent_time());
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
