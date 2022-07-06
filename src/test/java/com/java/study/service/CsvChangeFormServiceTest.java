package com.java.study.service;

import com.java.study.dto.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvChangeFormServiceTest extends ConstantTestService {
    @Autowired
    CsvChangeFormService service;

    @AfterEach
    void afterEach() {
        File file = new File(CUSTOMER_ERROR_CSV);
        file.delete();
    }

    @ParameterizedTest
    @DisplayName("Test read customer from csv is success")
    @ValueSource(strings = {CUSTOMER_CSV, CUSTOMER_CSV_NOT_SUFFIX})
    void csvToCustomers(String path) {
        List<Customer> customers = service.csvToCustomers(path);
        customers.sort(Comparator.comparing(Customer::getFirstName));
        List<Customer> customersExpected = createCustomers();
        assertNotNull(customers);
        assertEquals(customersExpected, customers);
    }

    @Test
    @DisplayName("Test not found csv file")
    void csvToCustomers_NotFound() {
        List<Customer> customers = service.csvToCustomers("");
        assertNull(customers);
    }

    @Test
    @DisplayName("Test write customer error to csv success")
    void toCustomerCsv() {
        boolean actual = service.toCustomerCsv(createCustomers());
        assertTrue(actual);
        File file = new File(CUSTOMER_ERROR_CSV);
        assertTrue(file.canRead());
    }

    private List<Customer> createCustomers() {
        Customer customer1 = Customer.builder()
                .title("Mr")
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@example.com")
                .build();
        Customer customer2 = Customer.builder()
                .title("Mrs")
                .firstName("Michelle")
                .lastName("Smith")
                .email("michelle.smith@example.com")
                .build();
        Customer customer3 = Customer.builder()
                .title("Mr")
                .firstName("Nguyen")
                .lastName("Hai")
                .email("")
                .build();
        return List.of(customer1, customer2, customer3);
    }
}
