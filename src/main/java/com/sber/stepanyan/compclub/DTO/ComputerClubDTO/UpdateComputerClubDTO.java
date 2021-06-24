package com.sber.stepanyan.compclub.DTO.ComputerClubDTO;

import com.sber.stepanyan.compclub.entity.ComputerClub;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateComputerClubDTO {


    @Min(value = 1, message = "минимальный id = 1")
    @NotEmpty(message = "id не должен быть пустым")
    private Long id;
    @Size(min = 5, max = 200, message = "address должен быть от 5 до 200 символов")
    private String address;
    @Size(min = 3, max = 30, message = "name должен быть от 3 до 30 символов")
    private String name;

    public UpdateComputerClubDTO() {
    }

    public UpdateComputerClubDTO(ComputerClub computerClub) {
        this.id = computerClub.getId();
        this.address = computerClub.getAddress();
        this.name = computerClub.getName();
    }

    public UpdateComputerClubDTO(Long id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
