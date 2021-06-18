package com.sber.stepanyan.compclub.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    private Workstation workstation;

    private Timestamp start;
    private Timestamp end;

    private String status;

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Workstation getComputer() {
        return workstation;
    }

    public void setComputer(Workstation workStation) {
        this.workstation = workStation;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
