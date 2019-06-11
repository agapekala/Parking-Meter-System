package view;

import Entities.Spot;
import dbservice.ISpotService;
import ejb.Alert;
import ejb.ILogicBean;
import ejb.LogicBean;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.LinkedList;

@ManagedBean(name = "panel")
@ApplicationScoped
public class PanelView {

    private ArrayList<Spot> spots=new ArrayList<>();
    private Spot currentSpot=null;
    private LinkedList<Alert> alerts=new LinkedList<>();

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    @EJB(lookup = "java:global/Panel-1.0-SNAPSHOT/LogicBean")
    private ILogicBean logicBean;

    @PostConstruct
    public void init(){
        spots=(ArrayList<Spot>) spotService.getAllSpots();
    }

    public void showAlerts(){
        alerts=logicBean.getAlerts();
        //jak pojawi się powiadomienie, to sprawdzamy bazę, bo coś się mogło zmienić
        spots=(ArrayList<Spot>) spotService.getAllSpots();
        RequestContext.getCurrentInstance().update("spotTable");
        RequestContext.getCurrentInstance().update("alert");
    }

    public void clearNotifications(){
        logicBean.clearList();
        alerts.clear();
        RequestContext.getCurrentInstance().update("alert");
    }

    public ArrayList<Spot> getSpots() {
        return spots;
    }

    public void setSpots(ArrayList<Spot> spots) {
        this.spots = spots;
    }

    public Spot getCurrentSpot() {
        return currentSpot;
    }

    public void setCurrentSpot(Spot currentSpot) {
        this.currentSpot = currentSpot;
    }


    public LinkedList<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(LinkedList<Alert> alerts) {
        this.alerts = alerts;
    }
}
