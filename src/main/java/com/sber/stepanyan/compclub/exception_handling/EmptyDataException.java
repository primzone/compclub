package com.sber.stepanyan.compclub.exception_handling;

public class EmptyDataException extends RuntimeException{
    public EmptyDataException(String message) {
        super(message);
    }
}
