import Entities.Spot;
import dbservice.ISpotService;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.ArrayList;

//wykrywanie zdarzeń na podstawie bazy danych
@Singleton
public class Detector {

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    @EJB
    private Sender sender;

    //sprawdza co 20 s czy są jakieś nieopłacone lub przedawnione miejsca w bazie
//    @Schedule(second = "*/20", minute = "*", hour = "*", persistent = false)
    public void doCheck(){
        System.out.println("checking database");
        ArrayList<Spot> expiredSpots=(ArrayList<Spot>) spotService.findExpiredSpots();
        ArrayList<Spot> unpaidSpots=(ArrayList<Spot>) spotService.findUnpaidSpots();


//        expiredSpots.forEach(element -> {
//            String message=Integer.toString(element.getSpot_id());
//            message+=" wygaśnięcie ważności biletu";
//            sender.sendAlert(message);
//        });
//
        unpaidSpots.forEach(element->{
            String message="Miejsce nieopłacone";
            sender.sendAlert(message,element.getSpot_id(), element.getZone().getZone_id());
        });

        //unpaidSpots.forEach(element -> System.out.println(element.getEntry_time()));
    }

}
