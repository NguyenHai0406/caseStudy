package com.java.study.service;

import com.java.study.dto.Customer;

import java.util.List;

public interface CsvChangeFormService {
    /**
     * Get the customer form path file csv
     * @param path
     * @return
     */
    List<Customer> csvToCustomers(String path);

    /**
     * Convert the customers to Csv file
     * @param customers
     */
    boolean toCustomerCsv(List<Customer> customers);
}
