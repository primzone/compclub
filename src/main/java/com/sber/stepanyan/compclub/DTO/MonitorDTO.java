package com.sber.stepanyan.compclub.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sber.stepanyan.compclub.entity.Monitor;

public class MonitorDTO {


    private Long id;

    private String brand;
    private String model;
    private String resolution;
    private Integer refershRate;
    private Double pricePerHour;


    public MonitorDTO() {
    }

    public MonitorDTO(Monitor monitor) {
        this.id = monitor.getId();
        this.brand = monitor.getBrand();
        this.model = monitor.getModel();
        this.resolution = monitor.getResolution();
        this.refershRate = monitor.getRefershRate();
        this.pricePerHour = monitor.getPricePerHour();
    }

    public MonitorDTO(Long id, String brand, String model, String resolution, Integer refershRate, Double pricePerHour) {
        this.id = id;
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
}
