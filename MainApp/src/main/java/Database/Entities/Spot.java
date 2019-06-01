package Database.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Spot {
    @Id
    @GeneratedValue
    private int spot_id;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date payment_time;

    private int duration;

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
