package com.fms.error.custom.errors;

public class DifferentAccountException extends RuntimeException{
    public DifferentAccountException(String message){
        super(message);
    }
}
