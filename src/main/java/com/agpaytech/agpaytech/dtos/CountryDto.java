package com.agpaytech.agpaytech.dtos;

public class CountryDto {

    private String name;

    public CountryDto(String name) {
        this.name = name;
    }

    public CountryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
