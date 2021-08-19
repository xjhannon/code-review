package com.carsaver.codereview.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZipCodeLookupServiceTest {

    @Autowired
    ZipCodeLookupService zipCodeLookupService;

    String EXPECTED_RESULT = "37067";

    @Test
    void thatZipCodeLookupServiceIsNotNull() {
        assertNotEquals(null, zipCodeLookupService);
    }

    @Test
    void thatLookupCityByZipReturnsExpectedValue() {
        assertEquals(EXPECTED_RESULT, zipCodeLookupService.lookupCityByZip("11111"));
    }

    @Test
    void thatLookupCityByZipReturnsExpectedValueWhenNullInput() {
        assertEquals(EXPECTED_RESULT, zipCodeLookupService.lookupCityByZip(null));
    }
}