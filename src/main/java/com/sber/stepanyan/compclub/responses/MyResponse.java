package com.sber.stepanyan.compclub.responses;

public class MyResponse {

    private boolean success;

    public MyResponse() {
    }

    public MyResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static MyResponse getSuccesResponse(){
        return new MyResponse(true);
    }

}
