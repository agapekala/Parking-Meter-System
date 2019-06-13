package DAO;

import Entities.Zone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ZoneDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    private EntityManager em = factory.createEntityManager();

    public Zone getZoneById(int id){
        String hql = "FROM Zone z WHERE z.id ="+id;
        Query query = em.createQuery(hql);
        List<Zone> results =query.getResultList();
        if(results.isEmpty()){
            return null;
        }
        return results.get(0);
    }

    public String getUserByZoneId(int zone_id){
        String hql = "FROM Zone z WHERE z.id ="+zone_id;
        Query query = em.createQuery(hql);
        List<Zone> results =query.getResultList();
        if(results.isEmpty()){
            return null;
        }
        Zone zone=results.get(0);
        return zone.getEmployee().getLogin();
    }

    public List<Zone> getAllZones(){
        String hql = "FROM Zone z";
        Query query = em.createQuery(hql);
        List<Zone> results =query.getResultList();
        if(results.isEmpty()){
            return null;
        }
        return results;
    }
}
