package com.sber.stepanyan.compclub.DTO.WorkstationDTO;


import com.sber.stepanyan.compclub.entity.Workstation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class WorkstationResponseDTO {

    private Integer workstationNumber;
    private Long computerClubId;
    private Long monitorId;
    private Long systemUnitId;

    public WorkstationResponseDTO() {
    }

    public WorkstationResponseDTO(Workstation workstation) {
        this.workstationNumber = workstation.getWorkstationNumber();
        this.computerClubId = workstation.getComputerClub().getId();
        this.monitorId = workstation.getMonitor().getId();
        this.systemUnitId = workstation.getSystemUnit().getId();
    }

    public WorkstationResponseDTO(int workstationNumber, long computerClubId, long monitorId, long systemUnitId) {
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
