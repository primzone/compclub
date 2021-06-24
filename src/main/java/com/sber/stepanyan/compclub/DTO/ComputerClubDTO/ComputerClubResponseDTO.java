package com.sber.stepanyan.compclub.DTO.ComputerClubDTO;

import com.sber.stepanyan.compclub.entity.ComputerClub;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ComputerClubResponseDTO {

    private String name;
    private String address;


    public ComputerClubResponseDTO() {
    }

    public ComputerClubResponseDTO(ComputerClub computerClub) {
        this.address = computerClub.getAddress();
        this.name = computerClub.getName();
    }

    public ComputerClubResponseDTO(Long id, String address, String name) {
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
