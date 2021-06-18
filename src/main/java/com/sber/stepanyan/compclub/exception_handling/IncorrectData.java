package com.sber.stepanyan.compclub.exception_handling;

public class IncorrectData {
    private String info;

    public IncorrectData() {
    }

    public IncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
