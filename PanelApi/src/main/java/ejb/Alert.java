package ejb;

import java.io.Serializable;

public class Alert implements Serializable{
    private int alert_id;
    private String message;
    private int zone_id;

    public Alert(int alert_id, String message, int zone_id) {
        this.alert_id = alert_id;
        this.message = message;
        this.zone_id=zone_id;
    }

    public int getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(int alert_id) {
        this.alert_id = alert_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    @Override
    public String toString() {
        return message;
    }
}
