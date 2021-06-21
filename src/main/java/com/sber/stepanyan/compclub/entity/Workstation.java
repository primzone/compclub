package com.sber.stepanyan.compclub.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Workstation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
//    @GenericGenerator(name="kaugen" , strategy="increment")
//    @GeneratedValue(generator="kaugen")
    private int workstationNumber;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "computerClub_id")
    private ComputerClub computerClub;

    @OneToMany(mappedBy = "workstation")
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "workstation")
    private Set<Order> orders;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "systemUnit_id")
    private SystemUnit systemUnit;


    public Workstation() {
    }

    public Workstation(int workstationNumber, ComputerClub computerClub, Monitor monitor, SystemUnit systemUnit) {
        this.workstationNumber = workstationNumber;
        this.computerClub = computerClub;
        this.monitor = monitor;
        this.systemUnit = systemUnit;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public int getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(int workstationNumber) {
        this.workstationNumber = workstationNumber;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public SystemUnit getSystemUnit() {
        return systemUnit;
    }

    public void setSystemUnit(SystemUnit systemUnit) {
        this.systemUnit = systemUnit;
    }
}
