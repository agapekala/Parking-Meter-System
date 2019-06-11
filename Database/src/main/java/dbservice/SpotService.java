package dbservice;

import DAO.SpotDAO;
import Entities.Spot;
import Entities.Ticket;

import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.util.List;

//EJB udostępniane pozostałym modułom, w celu robienia opercaji na bd
//Korzysta z klas dao, które  bezpośrednio łączą się z bazą

@Singleton
@Remote(ISpotService.class)
public class SpotService implements ISpotService{

    private SpotDAO spotDAO=new SpotDAO();

    @Override
    public void updatePaidSpot(Ticket ticket) throws Exception{
        spotDAO.updatePaidSpot(ticket);
    }

    @Override
    @Lock
    public void setEntryDate(Spot spot) {
        spotDAO.setEntryDate(spot);
    }

    @Override
    @Lock
    public void setOccupied(Spot spot) {
        spotDAO.setSpotOccupied(spot);
    }

    @Override
    @Lock
    public void updateSpot(Spot spot){
        spotDAO.updateSpot(spot);
    }

    @Override
    public List<Spot> getAllSpots() {
        return spotDAO.getAllSpots();
    }

    @Override
    public List<Spot> findExpiredSpots() {
        return spotDAO.findExpiredSpots();
    }

    @Override
    public List<Spot> findUnpaidSpots() {
        return spotDAO.findUnpaidSpots();
    }

    @Override
    public int getZoneIdForSpot(int id) {
        return spotDAO.getZoneIdForSpot(id);
    }

    @Override
    public Spot getSpotById(int id) {
        return spotDAO.getSpotById(id);
    }
}
