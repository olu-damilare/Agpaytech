package com.agpaytech.agpaytech.models;

import lombok.Data;


public class Country {

    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
