package DAO;


import Entities.Spot;
import Entities.Ticket;
import Entities.Zone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SpotDAO {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    private EntityManager em = factory.createEntityManager();
    private ZoneDAO zoneDAO=new ZoneDAO();


    //aktulizuje dane miejsca na podstawie kupionego biletu
    public void updatePaidSpot(Ticket ticket) throws Exception{
        try{
            em.getTransaction().begin();
            Date date=new SimpleDateFormat("yy/MM/dd HH:mm:ss")
                    .parse(ticket.getPayment_time());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, ticket.getDuration());
            Date expiration_time=new Date(cal.getTimeInMillis());

            String hql ="update Spot s set s.payment_time=\'"+date +
                    "\', s.duration="+ticket.getDuration()+
                    ", s.expiration_time=\'"+expiration_time+
                    "\' where s.spot_id="+ticket.getSpot_id();
            Query q = em.createQuery(hql);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Blad przy aktualizowaniu rekordu: "+ e);
            throw e;
        }
    }

    //aktualizuje dane miejsca  na podstawie danych od sensora
    public void updateSpot(Spot spot){
        try{
            em.getTransaction().begin();
            Spot tmp=getSpotById(spot);
            spot.setZone(tmp.getZone());

//            String hql1 ="update Spot s set s.occupied="+spot.isOccupied() +
//                    ", s.duration="+spot.getDuration()+
//                    ", s.payment_time=\'"+spot.getPayment_time() +
//                    "\', s.entry_time=\'"+spot.getEntry_time()+
//                    "\', s.expiration_time=\'"+spot.getExpiration_time()+
//                    "\' where s.spot_id="+spot.getSpot_id();
//            Query q = em.createQuery(hql1);
//            q.executeUpdate();
            em.merge(spot);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Blad przy aktualizowaniu rekordu: "+ e);
        }
    }

    public void setEntryDate(Spot spot){
        try{
            em.getTransaction().begin();
            String hql1 ="update Spot s set s.entry_time=\'"+spot.getEntry_time() +
                    "\' where s.spot_id="+spot.getSpot_id();
            Query q = em.createQuery(hql1);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Blad przy aktualizowaniu rekordu: "+ e);
        }
    }



    public void setSpotOccupied(Spot spot){
        try{
            em.getTransaction().begin();
            String hql1 ="update Spot s set s.occupied="+spot.isOccupied() +
                    " where s.spot_id="+spot.getSpot_id();
            Query q = em.createQuery(hql1);
            q.executeUpdate();
           // em.merge(spot);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Blad przy aktualizowaniu rekordu: "+ e);
            e.printStackTrace();
        }
    }

    //znajduje wszystkie miejsca z przeterminowanym biletem
    public List<Spot> findExpiredSpots(){
        try{
            Date currentTime = new Date();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Spot> cr = cb.createQuery(Spot.class);
            Root<Spot> root = cr.from(Spot.class);

            cr.select(root).where(cb.lessThan(root.get("expiration_time"),currentTime));

            Query query = em.createQuery(cr);
            List<Spot> results = query.getResultList();

            return results;

        }catch(Exception e){
            System.out.println("Błąd przy wyszukiwaniu rekordów "+e);
        }
        return null;
    }

    //znajduje miejsca, które nie zostały opłacone w ciągu 10 min
    public List<Spot> findUnpaidSpots(){
        try{
            Date currentTime = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentTime);
            cal.add(Calendar.MINUTE, -10);
            Date expiration_time=new Date(cal.getTimeInMillis());

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Spot> cr = cb.createQuery(Spot.class);
            Root<Spot> root = cr.from(Spot.class);

            cr.select(root).where(cb.lessThan(root.get("entry_time"),expiration_time),
                    cb.equal(root.get("occupied"),true),
                    cb.isNull(root.get("payment_time")));
            Query query = em.createQuery(cr);
            List<Spot> results = query.getResultList();
            return results;
        }catch(Exception e){
            System.out.println("Błąd przy pobieraniu rekordu: "+e);
        }
        return null;
    }

    public void addSpot(Spot spot){

    }

    public Spot getSpotById(Spot spot){
        String hql = "FROM Spot s WHERE s.id ="+spot.getSpot_id();
        Query query = em.createQuery(hql);
        List<Spot> results =query.getResultList();
        if(results.isEmpty()){
            return null;
        }
        return results.get(0);
    }

    public Spot getSpotById(int id){
        String hql = "FROM Spot s WHERE s.id ="+id;
        Query query = em.createQuery(hql);
        List<Spot> results =query.getResultList();
        if(results.isEmpty()){
            return null;
        }
        return results.get(0);
    }

    public List<Spot> getAllSpots(){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Spot> cr = cb.createQuery(Spot.class);
            Root<Spot> root = cr.from(Spot.class);

            cr.select(root);
            Query query = em.createQuery(cr);
            List<Spot> results = query.getResultList();
            for(Spot s:results){
                em.refresh(s);
            }
            return results;
        }catch (Exception e){
            System.out.println("Błąd przy pobieraniu rekordów: "+e);
            return null;
        }

    }

    public int getZoneIdForSpot(int id){
        String hql = "SELECT z FROM Zone z INNER JOIN z.spots s WHERE s.id="+id;
        Query query = em.createQuery(hql);
        List<Zone> results =query.getResultList();
        if(results.isEmpty()){
            return 0;
        }
        return results.get(0).getZone_id();
    }

}
