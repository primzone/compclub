package com.sber.stepanyan.compclub.DTO;

import com.sber.stepanyan.compclub.entity.ComputerClub;

import javax.persistence.Column;

public class ComputerClubDTO {

    private Long id;
    private String address;
    private String name;

    public ComputerClubDTO() {
    }

    public ComputerClubDTO(ComputerClub computerClub) {
        this.id = computerClub.getId();
        this.address = computerClub.getAddress();
        this.name = computerClub.getName();
    }

    public ComputerClubDTO(Long id, String address, String name) {
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
