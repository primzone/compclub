package com.sber.stepanyan.compclub.DTO;

import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.SystemUnitPower;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class SystemUnitDTO {

    private Long id;
    private String cpu;
    private String craphicsCard;
    private Integer ram;
    private SystemUnitPower power;
    private Double pricePerHour;

    public SystemUnitDTO() {
    }

    public SystemUnitDTO(SystemUnit systemUnit) {
        this.id = systemUnit.getId();
        this.cpu = systemUnit.getCpu();
        this.craphicsCard = systemUnit.getCraphicsCard();
        this.ram = systemUnit.getRam();
        this.power = systemUnit.getPower();
        this.pricePerHour = systemUnit.getPricePerHour();
    }

    public SystemUnitDTO(Long id, String cpu, String craphicsCard, Integer ram, SystemUnitPower power, Double pricePerHour) {
        this.id = id;
        this.cpu = cpu;
        this.craphicsCard = craphicsCard;
        this.ram = ram;
        this.power = power;
        this.pricePerHour = pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public SystemUnitPower getPower() {
        return power;
    }

    public void setPower(SystemUnitPower power) {
        this.power = power;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
