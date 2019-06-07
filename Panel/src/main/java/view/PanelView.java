package view;

import Entities.Spot;
import dbservice.ISpotService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.LinkedList;

@ManagedBean(name = "panel")
public class PanelView {

    private ArrayList<Spot> spots=new ArrayList<>();
    private Spot currentSpot=null;

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    @PostConstruct
    public void init(){
        spots=(ArrayList<Spot>) spotService.getAllSpots();
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
}
