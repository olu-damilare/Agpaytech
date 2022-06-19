package com.agpaytech.agpaytech.responses;

import com.agpaytech.agpaytech.models.Country;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class CountryListPaginatedResponse {

    private int pageNumber;
    private int pageSize;
    private String message;
    private List<Country> data;
}
