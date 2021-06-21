package com.sber.stepanyan.compclub.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ComputerClub {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String address;
    private String name;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "computerClub")
    private Set<Workstation> workstations;


    public void addWorkstationToComputerClub(Workstation workstation){
        if (workstations == null){
            workstations = new HashSet<>();
        }
        workstations.add(workstation);
        workstation.setComputerClub(this);
    }

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


    public Set<Workstation> getWorkstations() {
        return workstations;
    }

    public void setWorkstations(Set<Workstation> workstations) {
        this.workstations = workstations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerClub that = (ComputerClub) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return workstations != null ? workstations.equals(that.workstations) : that.workstations == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (workstations != null ? workstations.hashCode() : 0);
        return result;
    }
}
