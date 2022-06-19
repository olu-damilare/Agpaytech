package com.agpaytech.agpaytech.service;

import com.agpaytech.agpaytech.dtos.CountryDto;
import com.agpaytech.agpaytech.exceptions.InvalidCountryException;
import com.agpaytech.agpaytech.responses.CountryListPaginatedResponse;
import com.agpaytech.agpaytech.exceptions.DuplicateCountryException;
import com.agpaytech.agpaytech.models.Country;
import com.agpaytech.agpaytech.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CountryService {

    public Country saveCountry(CountryDto countryDto) {

        if(countryDto.getName() == null || countryDto.getName().isEmpty() || countryDto.getName().isBlank()){
            throw new InvalidCountryException("Invalid country name. Name must not be empty.");
        }

        Country country = new Country();
        country.setName(country.getName());
        String countryName = countryDto.getName().toUpperCase().trim();
        checkDuplicateCountry(countryName);

        country.setName(countryName);
        CountryRepository.countryList.add(country);

        return country;
    }

    private void checkDuplicateCountry(String countryName){
        CountryRepository.countryList.forEach(country -> {
            if(country.getName().equalsIgnoreCase(countryName)){
                throw new DuplicateCountryException("Duplicate country. Country already exists.");
            }
        });
    }

    public CountryListPaginatedResponse fetchCountries(int pageNumber, int pageSize) {

        List<List<Country>> paginatedCountries = getPages(CountryRepository.countryList, pageSize);
        return getCountryListPaginatedResponse(pageNumber, pageSize, paginatedCountries);
    }

    private <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size())
            pageSize = list.size();
        int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
        List<List<T>> pages = new ArrayList<>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }

    public CountryListPaginatedResponse fetchCountries(String searchValue, int pageNumber, int pageSize) {

        if(searchValue.isEmpty() || searchValue.isBlank()){
            return fetchCountries(pageNumber, pageSize);
        }

        searchValue = searchValue.toUpperCase().trim();
        List<Country> matchingCountries = new ArrayList<>();

        for(Country country: CountryRepository.countryList){
            if(country.getName().contains(searchValue)){

                matchingCountries.add(country);
            }
        }

        List<List<Country>> paginatedCountries = getPages(matchingCountries, pageSize);
        return getCountryListPaginatedResponse(pageNumber, pageSize, paginatedCountries);
    }

    private CountryListPaginatedResponse getCountryListPaginatedResponse(int pageNumber, int pageSize, List<List<Country>> paginatedCountries) {
        pageNumber = pageNumber < 0 ? 0 : pageNumber - 1;

        CountryListPaginatedResponse response = new CountryListPaginatedResponse();
        response.setPageNumber(pageNumber + 1);
        if(paginatedCountries.isEmpty()){
            response.setData(Collections.emptyList());
            response.setMessage("No record found");
            response.setPageSize(0);
        }else{
            response.setPageSize(paginatedCountries.get(pageNumber).size());
            response.setData(paginatedCountries.get(pageNumber));
            response.setMessage("Successfully fetched countries.");
        }
        return response;
    }
}
