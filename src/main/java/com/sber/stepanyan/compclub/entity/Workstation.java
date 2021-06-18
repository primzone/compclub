package com.sber.stepanyan.compclub.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Workstation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "computerClub_id")
    private ComputerClub computerClub;

    @OneToMany(mappedBy = "workstation")
    private Set<Schedule> schedules;


    public Workstation() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ComputerClub getComputerClub() {
        return computerClub;
    }

    public void setComputerClub(ComputerClub computerClub) {
        this.computerClub = computerClub;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }
}
