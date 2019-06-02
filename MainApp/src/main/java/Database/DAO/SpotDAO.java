package Database.DAO;

import Database.Entities.Spot;
import Detection.ParkingMeterREST.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SpotDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    private EntityManager em = factory.createEntityManager();

    //aktulizuje dane miejsca na podstawie kupionego biletu
    public void updatePaidSpot(Ticket ticket){

    }

    //aktualizuje dane miejsca  na podstawie danych od sensora
    public void updateOccupiedSpot(Spot spot){

    }

    public void addSpot(Spot spot){

    }

}
