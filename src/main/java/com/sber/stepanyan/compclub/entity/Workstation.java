package com.sber.stepanyan.compclub.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Workstation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "workstationNumber", nullable = false, unique = true)
//    @GenericGenerator(name="kaugen" , strategy="increment")
//    @GeneratedValue(generator="kaugen")
    private Integer workstationNumber;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "computerClub_id")
    private ComputerClub computerClub;

    @OneToMany(mappedBy = "workstation", fetch = FetchType.EAGER)
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "workstation", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
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
