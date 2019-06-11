package ejb;

import org.primefaces.json.JSONObject;
import view.PanelView;

import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Stateless
@Remote(ILogicBean.class)
public class LogicBean implements ILogicBean{

    private LinkedList<Alert> alerts=new LinkedList<>();

    public void addNewAlert(String alert){
        JSONObject json=new JSONObject(alert);
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String date = dateFormat.format(currentDate);
        String parsedAlert="";
        parsedAlert+=date+" ";
        parsedAlert+="Miejsce "+json.get("spot_id")+"\n";
        parsedAlert+=" Strefa "+json.get("zone_id")+" \n";
        parsedAlert+=json.getString("message");

        //bardzo nieładnie zrobione; sprawdzam ostatnie id w liście i ustawiam kolejne
        if(alerts.isEmpty()){
            alerts.add(new Alert(1,parsedAlert));
        }else alerts.add(new Alert(alerts.getLast().getAlert_id()+1,parsedAlert));
    }

    public void clearList(){
        alerts.clear();
    }

    public LinkedList<Alert> getAlerts() {
        return alerts;
    }
}
