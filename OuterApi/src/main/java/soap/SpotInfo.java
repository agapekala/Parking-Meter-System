package soap;

import Entities.Spot;
import dbservice.ISpotService;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "soap.ISpotInfo")
public class SpotInfo implements ISpotInfo{

    private final String jndi="java:global/Database-1.0-SNAPSHOT/SpotService";
    private ISpotService spotService;

    @PostConstruct
    public void init(){
        try {
            spotService =(ISpotService) new InitialContext().lookup(jndi);
        }catch(Exception e){
            System.out.println("Nie udało się wstrzyknąć ejb "+e);
        }
    }

    @Override
    @WebMethod
    public List<Spot> getAllFreeSpots() {
        List<Spot> spots=spotService.getAllSpots();
        List<Spot> freespots=spots.stream().filter(element-> !element.isOccupied())
                .collect(Collectors.toList());
        return freespots;
    }

    @Override
    @WebMethod
    public List<Spot> getAllOccupiedSpots() {
        List<Spot> spots=spotService.getAllSpots();
        List<Spot> occupiedSpots=spots.stream().filter(element-> element.isOccupied())
                .collect(Collectors.toList());
        return occupiedSpots;
    }

    @Override
    @WebMethod
    public boolean checkIfSpotIsOccupied(@WebParam int id) {
        Spot spot=spotService.getSpotById(id);
        return spot.isOccupied();
    }

    @Override
    @WebMethod
    public List<Spot> getAllOccupiedFromZone(@WebParam int zone) {
        List<Spot> spots = spotService.getAllSpots();
        List<Spot> matchingSpots=spots.stream()
                .filter(element->element.getZone().getZone_id()==zone)
                .collect(Collectors.toList());
        return matchingSpots;
    }
}
