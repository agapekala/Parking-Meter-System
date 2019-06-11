package dbservice;

import Entities.Spot;
import Entities.Ticket;

import java.util.List;

//Interfejs dla Spot Service z Database
public interface ISpotService {
    void updatePaidSpot(Ticket ticket) throws Exception;
    void updateSpot(Spot spot);
    void setEntryDate(Spot spot);
    void setOccupied(Spot spot);
    List<Spot> findExpiredSpots();
    List<Spot> findUnpaidSpots();
    List<Spot> getAllSpots();
    int getZoneIdForSpot(int id);
    Spot getSpotById(int id);
}
