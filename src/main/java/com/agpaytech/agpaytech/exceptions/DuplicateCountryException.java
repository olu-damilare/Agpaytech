package com.agpaytech.agpaytech.exceptions;

public class DuplicateCountryException extends RuntimeException{

    public DuplicateCountryException(String message){
        super(message);
    }

    public DuplicateCountryException(){
        super();
    }
}
