package com.sber.stepanyan.compclub.DTO.AccountDTO;

import javax.validation.constraints.Size;

public class AddAccountDTO {

    @Size(min = 3, max = 30, message = "name должен быть от 3 до 30 символов")
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
