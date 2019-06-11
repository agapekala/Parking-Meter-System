package Detection.ejb.detection.impl;

import Entities.Spot;
import Entities.Ticket;
import dbservice.ISpotService;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//dodatkowe ejb, które zapisuje w bazie danych nowy zdarzenie i domyślnie powinno wysyłać komunikat
//o nowy zdarzeniu
@Stateless
public class ReceiveEvent {

//    @EJB
//    private Sender sender;

    final String jndi="java:global/Database-1.0-SNAPSHOT/SpotService" ;

    final String jndi2="java:global/MainApp-1.0-SNAPSHOT/Sender";

    public void receiveSensorEvent(Spot spot, String message) throws NamingException{

        //adnotacja EJB nie działa w soapie więc trzeba łączyć się przez context
        ISpotService spotService=(ISpotService) new InitialContext().lookup(jndi);
        Sender sender=(Sender) new InitialContext().lookup(jndi2);

        //aktualizacja bazy
        spotService.updateSpot(spot);

        //json do alertu
        int zone_id=spotService.getZoneIdForSpot(spot.getSpot_id());
        String jsonAlert=createMessage(spot.getSpot_id(),zone_id,message);
        sender.sendEvent(jsonAlert);
    }

    public void receiveParkingMeterEvent(Ticket ticket) throws Exception{
        ISpotService spotService=(ISpotService) new InitialContext().lookup(jndi);
        spotService.updatePaidSpot(ticket);

        Sender sender=(Sender) new InitialContext().lookup(jndi2);

        String jsonAlert = createMessage(ticket.getSpot_id(), ticket.getZone_id(), "Opłacono miejsce");
        sender.sendEvent(jsonAlert);
    }

    private String createMessage(int spot_id, int zone_id, String message){
        return "{\"message\":"+message+", \"spot_id\":"+spot_id+", \"zone_id\":"+zone_id+"}";
    }
}
