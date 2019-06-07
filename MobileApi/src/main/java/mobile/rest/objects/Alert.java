package mobile.rest.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alert {
    private String body;
    private String time;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void printAlert(){
        System.out.println("Alert: "+body);
        System.out.println("Time: "+time);
    }
}
