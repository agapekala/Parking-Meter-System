package ejb;

import Entities.Employee;
import Entities.Spot;
import Entities.Zone;
import dbservice.ISpotService;
import exceptions.WrongPasswordException;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


//funkcjolnalności dla pracownika
@Stateful
@RolesAllowed("Employee")
@SecurityDomain("postgresqldomain")
public class EmployeeBean{

    @EJB(lookup = "java:global/Panel-1.0-SNAPSHOT/LogicBean")
    private ILogicBean logicBean;

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    public String test(){
        return "User";
    }

    public List<Spot> getSpots(String login){
        ArrayList<Spot> allSpots=(ArrayList<Spot>) spotService.getAllSpots();
        List<Spot> employeeSpots=allSpots.stream().filter(spot->spot.getZone().getEmployee().getLogin().equals(login)
            ).collect(Collectors.toList());
        return employeeSpots;
    }

    public List<Alert> getAlerts(String login){
        LinkedList<Alert> allAlerts=logicBean.getAlerts();
        List<Alert>alerts=allAlerts.stream().filter(alert -> spotService.getUserByZone(alert.getZone_id()).equals(login)).collect(Collectors.toList());
        return alerts;
    }

    public void clearAlerts(String login){
        List<Zone> zones=spotService.getAllZones();
        int zone_id=0;
        for (Zone z: zones){
            if(z.getEmployee().getLogin().equals(login)){
                zone_id=z.getZone_id();
            }
        }
        logicBean.clearListByZone(zone_id);
    }

    public void changePassword(String login, String oldPassword, String newPassword) throws WrongPasswordException{
        Employee e=spotService.getEmployee(login);
        if(e.getPassword().equals(oldPassword)){
            spotService.updatePassword(e.getEmployee_id(),newPassword);
        }else{
            throw new WrongPasswordException();
        }
    }
}
