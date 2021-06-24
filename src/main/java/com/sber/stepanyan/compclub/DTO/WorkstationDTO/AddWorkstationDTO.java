package com.sber.stepanyan.compclub.DTO.WorkstationDTO;


import com.sber.stepanyan.compclub.entity.Workstation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AddWorkstationDTO {

    @Min(value = 1, message = "минимальный workstationNumber = 1")
    @NotEmpty(message = "workstationNumber не должен быть пустым")
    private Integer workstationNumber;
    @Min(value = 1, message = "минимальный computerClubId = 1")
    @NotEmpty(message = "computerClubId не должен быть пустым")
    private Long computerClubId;
    @Min(value = 1, message = "минимальный monitorId = 1")
    @NotEmpty(message = "monitorId не должен быть пустым")
    private Long monitorId;
    @Min(value = 1, message = "минимальный systemUnitId = 1")
    @NotEmpty(message = "systemUnitId не должен быть пустым")
    private Long systemUnitId;

    public AddWorkstationDTO() {
    }

    public AddWorkstationDTO(Workstation workstation) {
        this.workstationNumber = workstation.getWorkstationNumber();
        this.computerClubId = workstation.getComputerClub().getId();
        this.monitorId = workstation.getMonitor().getId();
        this.systemUnitId = workstation.getSystemUnit().getId();
    }

    public AddWorkstationDTO(long id, int workstationNumber, long computerClubId, long monitorId, long systemUnitId) {
        this.workstationNumber = workstationNumber;
        this.computerClubId = computerClubId;
        this.monitorId = monitorId;
        this.systemUnitId = systemUnitId;
    }

    public Integer getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(Integer workstationNumber) {
        this.workstationNumber = workstationNumber;
    }

    public Long getComputerClubId() {
        return computerClubId;
    }

    public void setComputerClubId(Long computerClubId) {
        this.computerClubId = computerClubId;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getSystemUnitId() {
        return systemUnitId;
    }

    public void setSystemUnitId(Long systemUnitId) {
        this.systemUnitId = systemUnitId;
    }
}
