package mobile.rest.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alert {
    private String body;
    private String time;
    private int zone_id;
    private int spot_id;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void printAlert(){
        System.out.println("Alert: "+body);
        System.out.println("Time: "+time);
        System.out.println("Spot: "+spot_id);
        System.out.println("Zone: "+zone_id);
    }
}
