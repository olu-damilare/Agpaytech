package com.agpaytech.agpaytech.controllers;

import com.agpaytech.agpaytech.dtos.CountryDto;
import com.agpaytech.agpaytech.models.Country;
import com.agpaytech.agpaytech.responses.CountryListPaginatedResponse;
import com.agpaytech.agpaytech.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/country")
public class CountryController {

    private final CountryService countryService;


    @PostMapping
    public ResponseEntity<?> saveCountry(@RequestBody CountryDto countryDto){
        Country savedCountry = null;
        try{
           savedCountry = countryService.saveCountry(countryDto);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully added country " + savedCountry.getName(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> fetchCountries(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
                                            @RequestParam(name = "searchBy", defaultValue = "", required = false) String searchBy
                                            ){
        CountryListPaginatedResponse response = countryService.fetchCountries(searchBy, pageNumber, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
