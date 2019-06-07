package Detection.SensorSOAP.sensors;

import java.util.Date;

//klasa pomocnicza, dane dotyczące nowego zdarzenia przesyłanego przez sensor
public class Event {
    private String event_type;
    private int zone;
    private int spot;
    private Date event_time;

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public Date getEvent_time() {
        return event_time;
    }

    public void setEvent_time(Date event_time) {
        this.event_time = event_time;
    }
}
