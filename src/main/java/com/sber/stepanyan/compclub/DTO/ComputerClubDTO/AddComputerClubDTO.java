package com.sber.stepanyan.compclub.DTO.ComputerClubDTO;

import com.sber.stepanyan.compclub.entity.ComputerClub;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class AddComputerClubDTO {


    @Size(min = 5, max = 200, message = "address должен быть от 5 до 200 символов")
    @NotBlank(message = "address не должен быть пустым")
    private String address;













    @Size(min = 3, max = 30, message = "name должен быть от 3 до 30 символов")
    @NotBlank(message = "name не должен быть пустым")
    private String name;

    public AddComputerClubDTO() {
    }

    public AddComputerClubDTO(ComputerClub computerClub) {
        this.address = computerClub.getAddress();
        this.name = computerClub.getName();
    }

    public AddComputerClubDTO(Long id, String address, String name) {
        this.address = address;
        this.name = name;
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
