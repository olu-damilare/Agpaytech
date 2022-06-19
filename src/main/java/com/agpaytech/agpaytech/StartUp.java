package com.agpaytech.agpaytech;

import com.agpaytech.agpaytech.dtos.CountryDto;
import com.agpaytech.agpaytech.models.Country;
import com.agpaytech.agpaytech.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class StartUp implements ApplicationListener<ApplicationReadyEvent> {

    private final CountryService countryService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        CountryDto[] countries = {
                new CountryDto("Nigeria"),
                new CountryDto("India"),
                new CountryDto("England"),
                new CountryDto("France"),
                new CountryDto("Canada"),
                new CountryDto("Japan"),
                new CountryDto("United States of America"),
                new CountryDto("Brazil"),
                new CountryDto("Ghana"),
                new CountryDto("Russia"),
        };

        Arrays.stream(countries).forEach(countryService::saveCountry);
    }
}
