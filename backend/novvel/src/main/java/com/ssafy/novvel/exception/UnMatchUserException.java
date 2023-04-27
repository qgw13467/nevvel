package com.ssafy.novvel.exception;

public class UnMatchUserException extends RuntimeException{
    public UnMatchUserException(){
        super("User is Different");
    }
    public UnMatchUserException(String msg){
        super(msg);
    }
}
