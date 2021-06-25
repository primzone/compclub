package com.sber.stepanyan.compclub.DTO.SystemUnitDTO;

import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.SystemUnitPower;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class addSystemUnitDTO {

    @Size(min = 3, max = 50, message = "cpu должен быть от 3 до 50 символов")
    @NotBlank(message = "cpu не должен быть пустым")
    private String cpu;
    @Size(min = 3, max = 50, message = "craphicsCard должен быть от 3 до 50 символов")
    @NotBlank(message = "craphicsCard не должен быть пустым")
    private String craphicsCard;
    @Min(value = 2, message = "минимальный ram = 2")
    @Max(value = 256, message = "максимальный ram = 256")
    @NotEmpty(message = "ram не должен быть пустым")
    private Integer ram;
    @Min(value = 100, message = "минимальный pricePerHour = 100")
    @Max(value = 500, message = "максимальный pricePerHour = 500")
    @NotEmpty(message = "pricePerHour не должен быть пустым")
    private Double pricePerHour;

    public addSystemUnitDTO() {
    }

    public addSystemUnitDTO(SystemUnit systemUnit) {
        this.cpu = systemUnit.getCpu();
        this.craphicsCard = systemUnit.getCraphicsCard();
        this.ram = systemUnit.getRam();
        this.pricePerHour = systemUnit.getPricePerHour();
    }

    public addSystemUnitDTO(Long id, String cpu, String craphicsCard, Integer ram, SystemUnitPower power, Double pricePerHour) {
        this.cpu = cpu;
        this.craphicsCard = craphicsCard;
        this.ram = ram;
        this.pricePerHour = pricePerHour;
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

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
