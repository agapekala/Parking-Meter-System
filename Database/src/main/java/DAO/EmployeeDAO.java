package DAO;

import Entities.Employee;
import Entities.Spot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    private EntityManager em = factory.createEntityManager();

    public Employee getEmployeeByLogin(String login){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
        Root<Employee> root = cr.from(Employee.class);

        cr.select(root).where(cb.equal(root.get("login"),login));
        Query query = em.createQuery(cr);
        List<Employee> results = query.getResultList();
        em.refresh(results.get(0));
        return results.get(0);
    }

    public void updatePassword(int id, String password){
        try{
            em.getTransaction().begin();
            String hql1 ="update Employee e set e.password=\'"+password +
                    "\' where e.id="+id;
            Query q = em.createQuery(hql1);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Blad przy aktualizowaniu rekordu: "+ e);
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees(){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);

            cr.select(root);
            Query query = em.createQuery(cr);
            List<Employee> results = query.getResultList();
            for(Employee s:results){
                em.refresh(s);
            }
            return results;
        }catch (Exception e){
            System.out.println("Błąd przy pobieraniu rekordów: "+e);
            return null;
        }
    }
}
