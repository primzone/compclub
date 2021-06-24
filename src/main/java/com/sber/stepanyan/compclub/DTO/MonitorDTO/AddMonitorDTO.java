package com.sber.stepanyan.compclub.DTO.MonitorDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sber.stepanyan.compclub.entity.Monitor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddMonitorDTO {


    @Size(min = 2, max = 20, message = "brand должен быть от 2 до 20 символов")
    @NotBlank(message = "brand не должен быть пустым")
    private String brand;
    @Size(min = 2, max = 40, message = "model должен быть от 2 до 40 символов")
    @NotBlank(message = "model не должен быть пустым")
    private String model;
    @Size(min = 6, max = 30, message = "resolution должен быть от 6 до 30 символов")
    @NotBlank(message = "resolution не должен быть пустым")
    private String resolution;
    @Min(value = 10, message = "минимальный refershRate = 10")
    @Max(value = 400, message = "максимальный refershRate = 400")
    private Integer refershRate;
    @Min(value = 50, message = "минимальный pricePerHour = 50")
    @Max(value = 200, message = "максимальный pricePerHour = 200")
    private Double pricePerHour;


    public AddMonitorDTO() {
    }

    public AddMonitorDTO(Monitor monitor) {
        this.brand = monitor.getBrand();
        this.model = monitor.getModel();
        this.resolution = monitor.getResolution();
        this.refershRate = monitor.getRefershRate();
        this.pricePerHour = monitor.getPricePerHour();
    }

    public AddMonitorDTO(Long id, String brand, String model, String resolution, Integer refershRate, Double pricePerHour) {
        this.brand = brand;
        this.model = model;
        this.resolution = resolution;
        this.refershRate = refershRate;
        this.pricePerHour = pricePerHour;
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
