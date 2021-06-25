package com.sber.stepanyan.compclub.entity;

import com.sber.stepanyan.compclub.DTO.MonitorDTO.AddMonitorDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Monitor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "resolution", nullable = false)
    private String resolution;
    @Column(name = "refershRate", nullable = false)
    private Integer refershRate;
    @Column(name = "pricePerHour", nullable = false)
    private Double pricePerHour;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "monitor")
    private Set<Workstation> workstations;


    public Monitor() {
    }

    public Monitor(AddMonitorDTO monitorDTO) {
        this.brand = monitorDTO.getBrand();
        this.model = monitorDTO.getModel();
        this.resolution = monitorDTO.getResolution();
        this.refershRate = monitorDTO.getRefershRate();
        this.pricePerHour = monitorDTO.getPricePerHour();
    }

    public Monitor(String brand, String model, String resolution, Integer refershRate, Double pricePerHour) {
        this.brand = brand;
        this.model = model;
        this.resolution = resolution;
        this.refershRate = refershRate;
        this.pricePerHour = pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getRefershRate() {
        return refershRate;
    }

    public void setRefershRate(Integer refershRate) {
        this.refershRate = refershRate;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Set<Workstation> getWorkstations() {
        return workstations;
    }

    public void setWorkstations(Set<Workstation> workstations) {
        this.workstations = workstations;
    }
}
