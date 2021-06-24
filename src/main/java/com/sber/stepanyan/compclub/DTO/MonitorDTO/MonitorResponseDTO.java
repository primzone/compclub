package com.sber.stepanyan.compclub.DTO.MonitorDTO;

import com.sber.stepanyan.compclub.entity.Monitor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MonitorResponseDTO {

    private Long id;
    private String brand;
    private String model;
    private String resolution;
    private Integer refershRate;
    private Double pricePerHour;

    public MonitorResponseDTO() {
    }

    public MonitorResponseDTO(Monitor monitor) {
        this.id = monitor.getId();
        this.brand = monitor.getBrand();
        this.model = monitor.getModel();
        this.resolution = monitor.getResolution();
        this.refershRate = monitor.getRefershRate();
        this.pricePerHour = monitor.getPricePerHour();
    }

    public MonitorResponseDTO(Long id, String brand, String model, String resolution, Integer refershRate, Double pricePerHour) {
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
