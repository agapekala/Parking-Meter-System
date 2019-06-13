package ejb;

import java.io.Serializable;

public class Alert implements Serializable{
    private String message;
    private int zone_id;
    private String date;

    public Alert(String message, int zone_id, String date) {
        this.message = message;
        this.zone_id=zone_id;
        this.date=date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
