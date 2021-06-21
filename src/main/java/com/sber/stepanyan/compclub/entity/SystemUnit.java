package com.sber.stepanyan.compclub.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SystemUnit {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


    @Column(nullable = false)
    private String cpu;
    @Column(nullable = false)
    private String craphicsCard;
    @Column(nullable = false)
    private int ram;
    @Column(nullable = false)
    private SystemUnitPower power;
    @Column(nullable = false)
    private double pricePerHour;
    @Enumerated(EnumType.STRING)



    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "systemUnit")
    private Set<Workstation> workstations;

    public SystemUnit() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Workstation> getWorkstations() {
        return workstations;
    }

    public void setWorkstations(Set<Workstation> workstations) {
        this.workstations = workstations;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public SystemUnitPower getPower() {
        return power;
    }

    public void setPower(SystemUnitPower power) {
        this.power = power;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCraphicsCard() {
        return craphicsCard;
    }

    public void setCraphicsCard(String craphicsCard) {
        this.craphicsCard = craphicsCard;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
