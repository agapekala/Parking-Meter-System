package Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Spot implements Serializable{
    @Id
    @GeneratedValue
    private int spot_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date payment_time;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entry_time;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration_time;

    @Column(nullable = true)
    private Integer duration = null;

    private boolean occupied;

    public boolean isOccupied() {
        return occupied;
    }

    public Date getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(Date expiration_time) {
        this.expiration_time = expiration_time;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
