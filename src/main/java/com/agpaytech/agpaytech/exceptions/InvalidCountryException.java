package com.agpaytech.agpaytech.exceptions;

public class InvalidCountryException extends RuntimeException{

    public InvalidCountryException(String message){
        super(message);
    }

    public InvalidCountryException(){
        super();
    }
}
