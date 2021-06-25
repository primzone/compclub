package com.sber.stepanyan.compclub.DTO.SystemUnitDTO;

import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.SystemUnitPower;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateSystemUnitDTO {

    @Min(value = 1, message = "минимальный id = 1")
    @NotEmpty(message = "id не должен быть пустым")
    private Long id;
    @Size(min = 3, max = 50, message = "cpu должен быть от 3 до 50 символов")
    private String cpu;
    @Size(min = 3, max = 50, message = "craphicsCard должен быть от 3 до 50 символов")
    private String craphicsCard;
    @Min(value = 2, message = "минимальный ram = 2")
    @Max(value = 256, message = "максимальный ram = 256")
    private Integer ram;
    @Min(value = 100, message = "минимальный pricePerHour = 100")
    @Max(value = 500, message = "максимальный pricePerHour = 500")
    private Double pricePerHour;

    public UpdateSystemUnitDTO() {
    }

    public UpdateSystemUnitDTO(SystemUnit systemUnit) {
        this.id = systemUnit.getId();
        this.cpu = systemUnit.getCpu();
        this.craphicsCard = systemUnit.getCraphicsCard();
        this.ram = systemUnit.getRam();
        this.pricePerHour = systemUnit.getPricePerHour();
    }

    public UpdateSystemUnitDTO(Long id, String cpu, String craphicsCard, Integer ram, Double pricePerHour) {
        this.id = id;
        this.cpu = cpu;
        this.craphicsCard = craphicsCard;
        this.ram = ram;
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

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
