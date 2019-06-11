package ejb;

import java.io.Serializable;

public class Alert implements Serializable{
    private int alert_id;
    private String message;

    public Alert(int alert_id, String message) {
        this.alert_id = alert_id;
        this.message = message;
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

    @Override
    public String toString() {
        return message;
    }
}
