package ejb;

import Entities.Employee;
import Entities.Spot;
import dbservice.ISpotService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//funkcjonalności dla Managera
@Stateful
@RolesAllowed("Manager")
@SecurityDomain("postgresqldomain")
public class ManagerBean{

    @EJB(lookup = "java:global/Panel-1.0-SNAPSHOT/LogicBean")
    private ILogicBean logicBean;

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    public String test(){
        return "Manager";
    }

    public List<Spot> getSpots(){
        return spotService.getAllSpots();
    }

    public void changePassword(String login, String password){
        Employee e=spotService.getEmployee(login);
        spotService.updatePassword(e.getEmployee_id(),password);
    }

    public List<Employee> getEmployees(){
        return spotService.getAllEmployees();
    }
//    public List<Alert> getAlerts(){
//        LinkedList<Alert> alerts=logicBean.getAlerts();
//        return new ArrayList<>(alerts);
//    }

}
