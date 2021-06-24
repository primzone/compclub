package com.sber.stepanyan.compclub.DTO.WorkstationDTO;


import com.sber.stepanyan.compclub.entity.Workstation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UpdateWorkstationDTO {

    @Min(value = 1, message = "минимальный id = 1")
    @NotEmpty(message = "id не должен быть пустым")
    private Long id;
    @Min(value = 1, message = "минимальный workstationNumber = 1")
    private Integer workstationNumber;
    @Min(value = 1, message = "минимальный computerClubId = 1")
    private Long computerClubId;
    @Min(value = 1, message = "минимальный monitorId = 1")
    private Long monitorId;
    @Min(value = 1, message = "минимальный systemUnitId = 1")
    private Long systemUnitId;

    public UpdateWorkstationDTO() {
    }

    public UpdateWorkstationDTO(Workstation workstation) {
        this.id = workstation.getId();
        this.workstationNumber = workstation.getWorkstationNumber();
        this.computerClubId = workstation.getComputerClub().getId();
        this.monitorId = workstation.getMonitor().getId();
        this.systemUnitId = workstation.getSystemUnit().getId();
    }

    public UpdateWorkstationDTO(long id, int workstationNumber, long computerClubId, long monitorId, long systemUnitId) {
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
