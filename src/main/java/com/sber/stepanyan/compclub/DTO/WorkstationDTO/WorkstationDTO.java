package com.sber.stepanyan.compclub.DTO.WorkstationDTO;


import com.sber.stepanyan.compclub.entity.Workstation;

public class WorkstationDTO {

    private Long id;
    private Integer workstationNumber;
    private Long computerClubId;
    private Long monitorId;
    private Long systemUnitId;


    public WorkstationDTO() {
    }

    public WorkstationDTO(Workstation workstation) {
        this.id = workstation.getId();
        this.workstationNumber = workstation.getWorkstationNumber();
        this.computerClubId = workstation.getComputerClub().getId();
        this.monitorId = workstation.getMonitor().getId();
        this.systemUnitId = workstation.getSystemUnit().getId();
    }

    public WorkstationDTO(long id, int workstationNumber, long computerClubId, long monitorId, long systemUnitId) {
        this.id = id;
        this.workstationNumber = workstationNumber;
        this.computerClubId = computerClubId;
        this.monitorId = monitorId;
        this.systemUnitId = systemUnitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
