package ejb;

import Entities.Employee;
import dbservice.ISpotService;
import org.primefaces.json.JSONObject;
import view.PanelView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


//tylko odbiera powiadomienia od modułu głównego
@Singleton
@Remote(ILogicBean.class)
public class LogicBean implements ILogicBean{


    //żeby było powiadomienia dla managera i reszty można zrobić dwie listy
    LinkedList<Alert> alerts=new LinkedList<>();
    @EJB
    private EmployeeBean employeeBean;

    @EJB ManagerBean managerBean;


    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    public void addNewAlert(String alert){
        JSONObject json=new JSONObject(alert);
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String date = dateFormat.format(currentDate);
        int zone_id=(Integer) json.get("zone_id");
        String parsedAlert="";
        parsedAlert+=date+" ";
        parsedAlert+="Miejsce "+json.get("spot_id")+"\n";
        parsedAlert+=" Strefa "+json.get("zone_id")+" \n";
        parsedAlert+=json.getString("message");

        //bardzo nieładnie zrobione; sprawdzam ostatnie id w liście i ustawiam kolejne
        if(alerts.isEmpty()){
            alerts.add(new Alert(1,parsedAlert,zone_id));
        }else {
            alerts.add(new Alert(alerts.getLast().getAlert_id()+1,parsedAlert,zone_id));
        }
    }

    public void clearList(){
        alerts.clear();
    }

    public void clearListByZone(int zone_id){
        List<Alert> found=new ArrayList<>();
        for (Alert a: alerts){
            if(a.getZone_id()==zone_id){
                found.add(a);
            }
        }
        alerts.removeAll(found);
    }

    public LinkedList<Alert> getAlerts() {
        return alerts;
    }

}
