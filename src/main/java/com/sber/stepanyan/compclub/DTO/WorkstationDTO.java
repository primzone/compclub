package com.sber.stepanyan.compclub.DTO;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.entity.SystemUnit;

public class WorkstationDTO {

    private long id;
    private int workstationNumber;
    private long computerClubId;
    private long monitorId;
    private long systemUnitId;


    public WorkstationDTO() {
    }

    public WorkstationDTO(long id, int workstationNumber, long computerClubId, long monitorId, long systemUnitId) {
        this.id = id;
        this.workstationNumber = workstationNumber;
        this.computerClubId = computerClubId;
        this.monitorId = monitorId;
        this.systemUnitId = systemUnitId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(int workstationNumber) {
        this.workstationNumber = workstationNumber;
    }

    public long getComputerClubId() {
        return computerClubId;
    }

    public void setComputerClubId(long computerClubId) {
        this.computerClubId = computerClubId;
    }

    public long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(long monitorId) {
        this.monitorId = monitorId;
    }

    public long getSystemUnitId() {
        return systemUnitId;
    }

    public void setSystemUnitId(long systemUnitId) {
        this.systemUnitId = systemUnitId;
    }
}
