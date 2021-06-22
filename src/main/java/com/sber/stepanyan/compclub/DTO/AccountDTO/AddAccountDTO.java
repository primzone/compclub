package com.sber.stepanyan.compclub.DTO.AccountDTO;

public class AddAccountDTO {


    private String name;

    public AddAccountDTO() {
    }

    public AddAccountDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
