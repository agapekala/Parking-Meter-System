package view;

import Entities.Employee;
import Entities.Spot;
import dbservice.ISpotService;
import ejb.*;
import exceptions.WrongPasswordException;
import hash.PasswordConverter;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

@ManagedBean(name = "panel")
@SessionScoped
public class PanelView implements Serializable {

    private ArrayList<Spot> spots = new ArrayList<>();
    private Spot currentSpot = null;
    private ArrayList<Alert> alerts = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private String old_password;
    private String new_password;
    private String chosen_user;

    @EJB
    private HelpBean helpBean;

    @PostConstruct
    public void init() {
        spots = (ArrayList<Spot>) helpBean.getSpots();
        alerts = (ArrayList<Alert>) helpBean.getAlerts();
        employees = (ArrayList<Employee>) helpBean.getAllEmployees();
    }

    public void showAlerts() {
        alerts = (ArrayList<Alert>) helpBean.getAlerts();
        //jak pojawi się powiadomienie, to sprawdzamy bazę, bo coś się mogło zmienić
        spots = (ArrayList<Spot>) helpBean.getSpots();
        RequestContext.getCurrentInstance().update("spotTable");
        RequestContext.getCurrentInstance().update("alert");
    }

    public void clearNotifications() {
        helpBean.clearAlerts();
        alerts.clear();
        RequestContext.getCurrentInstance().update("alert");
    }

    public void changePassword() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            helpBean.changePassword(chosen_user, old_password, new_password);
            context.getExternalContext().redirect("index.xhtml");
        } catch (WrongPasswordException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Błędne hasło");
            facesContext.addMessage(null, facesMessage);
        } catch (Exception e) {
            System.out.println("Cannot redirect " + e);
        }
    }


    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String occupationBooleanToString() {
        if (currentSpot.isOccupied()) {
            return "Zajęte";
        }
        return "Wolne";
    }

    public String occupationBooleanToString(boolean isOccupied) {
        if (isOccupied) {
            return "Zajęte";
        }
        return "Wolne";
    }

    public boolean checkIfManager() {
        return helpBean.isManager();
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


    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(ArrayList<Alert> alerts) {
        this.alerts = alerts;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        PasswordConverter conv = new PasswordConverter();
        old_password = conv.convertToDatabaseColumn(old_password);
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getChosen_user() {
        return chosen_user;
    }

    public void setChosen_user(String chosen_user) {
        this.chosen_user = chosen_user;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
