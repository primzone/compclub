package com.sber.stepanyan.compclub.DTO.MonitorDTO;

import com.sber.stepanyan.compclub.entity.Monitor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateMonitorDTO {

    @Min(value = 1, message = "минимальный id = 1")
    @NotEmpty(message = "id не должен быть пустым")
    private Long id;
    @Size(min = 2, max = 20, message = "brand должен быть от 2 до 20 символов")
    private String brand;
    @Size(min = 2, max = 40, message = "model должен быть от 2 до 40 символов")
    private String model;
    @Size(min = 6, max = 30, message = "resolution должен быть от 6 до 30 символов")
    private String resolution;
    @Min(value = 10, message = "минимальный refershRate = 10")
    @Max(value = 400, message = "максимальный refershRate = 400")
    private Integer refershRate;
    @Min(value = 50, message = "минимальный pricePerHour = 50")
    @Max(value = 200, message = "максимальный pricePerHour = 200")
    private Double pricePerHour;


    public UpdateMonitorDTO() {
    }

    public UpdateMonitorDTO(Monitor monitor) {
        this.id = monitor.getId();
        this.brand = monitor.getBrand();
        this.model = monitor.getModel();
        this.resolution = monitor.getResolution();
        this.refershRate = monitor.getRefershRate();
        this.pricePerHour = monitor.getPricePerHour();
    }

    public UpdateMonitorDTO(Long id, String brand, String model, String resolution, Integer refershRate, Double pricePerHour) {
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
