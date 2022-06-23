package com.agpaytech.agpaytech;


import com.agpaytech.agpaytech.dtos.CountryDto;
import com.agpaytech.agpaytech.exceptions.InvalidCountryException;
import com.agpaytech.agpaytech.responses.CountryListPaginatedResponse;
import com.agpaytech.agpaytech.exceptions.DuplicateCountryException;
import com.agpaytech.agpaytech.models.Country;
import com.agpaytech.agpaytech.repository.CountryRepository;
import com.agpaytech.agpaytech.service.CountryService;
import com.agpaytech.agpaytech.utils.CustomPaginator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AgpaytechAssessmentApplicationTests {

	private CountryService countryService;

	@BeforeEach
	void setUp(){
		countryService = new CountryService(new CustomPaginator());
	}

	@AfterEach
	void tearDown(){
		CountryRepository.countryList.clear();
	}

	@Test
	void testToAddCountry(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("Nigeria");

		countryService.saveCountry(country);
		assertFalse(CountryRepository.countryList.isEmpty());

		Country savedCountry = CountryRepository.countryList.get(0);
		assertThat(savedCountry.getName()).isEqualToIgnoringCase(country.getName());

	}

	@Test
	void testToAddMoreThanOneCountry(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("Nigeria");

		CountryDto secondCountry = new CountryDto();
		secondCountry.setName("England");

		countryService.saveCountry(country);
		countryService.saveCountry(secondCountry);
		assertFalse(CountryRepository.countryList.isEmpty());

		assertThat(CountryRepository.countryList.size()).isEqualTo(2);
	}

	@Test
	void testThatAddingAnAlreadySavedCountryThrowsDuplicateCountryException(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("Nigeria");

		countryService.saveCountry(country);
		assertFalse(CountryRepository.countryList.isEmpty());
		assertThat(CountryRepository.countryList.size()).isEqualTo(1);

		assertThrows(DuplicateCountryException.class, () -> countryService.saveCountry(country));

		assertThat(CountryRepository.countryList.size()).isEqualTo(1);
	}

	@Test
	void testThatAddingCountryWithInvalidNameThrowsInvalidCountryException(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("");

		assertThrows(InvalidCountryException.class, () -> countryService.saveCountry(country));

		assertTrue(CountryRepository.countryList.isEmpty());
	}

	@Test
	void testToRetrievePaginatedListOfCountry(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("Nigeria");

		CountryDto country1 = new CountryDto();
		country1.setName("England");

		CountryDto country2 = new CountryDto();
		country2.setName("India");

		CountryDto country3 = new CountryDto();
		country3.setName("Brazil");

		countryService.saveCountry(country);
		countryService.saveCountry(country1);
		countryService.saveCountry(country2);
		countryService.saveCountry(country3);

		assertFalse(CountryRepository.countryList.isEmpty());

		CountryListPaginatedResponse response = countryService.fetchCountries(1, 4);
		assertThat(response.getPageSize()).isEqualTo(4);
		assertThat(response.getData().size()).isEqualTo(4);
		assertThat(response.getPageNumber()).isEqualTo(1);
		System.out.println(response);
	}

	@Test
	void testToSearchCountryByPartialName(){
		assertTrue(CountryRepository.countryList.isEmpty());
		CountryDto country = new CountryDto();
		country.setName("Nigeria");

		CountryDto country1 = new CountryDto();
		country1.setName("England");

		CountryDto country2 = new CountryDto();
		country2.setName("India");

		CountryDto country3 = new CountryDto();
		country3.setName("Brazil");

		countryService.saveCountry(country);
		countryService.saveCountry(country1);
		countryService.saveCountry(country2);
		countryService.saveCountry(country3);

		assertFalse(CountryRepository.countryList.isEmpty());

		CountryListPaginatedResponse response = countryService.fetchCountries("Z", 1, 4);


		assertThat(response.getPageSize()).isEqualTo(1);
		assertThat(response.getData().size()).isEqualTo(1);
		assertThat(response.getPageNumber()).isEqualTo(1);
	}

}
