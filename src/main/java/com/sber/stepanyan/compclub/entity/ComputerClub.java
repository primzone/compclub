package com.sber.stepanyan.compclub.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ComputerClub {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String address;
    private String name;
    private int computer_count;

    @OneToMany(mappedBy = "computerClub")
    private List<Workstation> workstations;


    public ComputerClub() {
    }

    public ComputerClub(String adress, String name) {
        this.address = adress;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComputer_count() {
        return computer_count;
    }

    public void setComputer_count(int computer_count) {
        this.computer_count = computer_count;
    }

    public List<Workstation> getComputers() {
        return workstations;
    }

    public void setComputers(List<Workstation> workstations) {
        this.workstations = workstations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerClub that = (ComputerClub) o;

        if (id != that.id) return false;
        if (computer_count != that.computer_count) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return workstations != null ? workstations.equals(that.workstations) : that.workstations == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + computer_count;
        result = 31 * result + (workstations != null ? workstations.hashCode() : 0);
        return result;
    }
}
