package com.sber.stepanyan.compclub.DTO.ComputerClubDTO;

import javax.validation.constraints.Min;

public class AddWorkstationToComputerClubDTO {

    @Min(value = 1, message = "минимальный ComputerClubId = 1")
    Long computerClubId;
    @Min(value = 1, message = "минимальный WorkstationId = 1")
    Integer workstationNumber;

    public AddWorkstationToComputerClubDTO() {
    }

    public Long getComputerClubId() {
        return computerClubId;
    }

    public void setComputerClubId(Long computerClubId) {
        this.computerClubId = computerClubId;
    }

    public Integer getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(Integer workstationNumber) {
        this.workstationNumber = workstationNumber;
    }
}
