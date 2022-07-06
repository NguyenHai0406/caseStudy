package com.java.study.service.impl;

import com.java.study.common.Constant;
import com.java.study.dto.Customer;
import com.java.study.dto.Email;
import com.java.study.mapper.EmailMapper;
import com.java.study.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    CsvChangeFormService csvChangeFormService;

    @Autowired
    JsonChangeFormService jsonChangeFormService;

    @Autowired
    EmailMapper emailMapper;

    @Override
    public void outputEmail(String csvPath, String jsonPath, String outputFolderPath) throws FileNotFoundException {
        List<Customer> customers = csvChangeFormService.csvToCustomers(csvPath);
        List<Customer> errorCustomers = new ArrayList<>();
        Email emailTemplate = jsonChangeFormService.jsonToEmail(jsonPath);
        List<Email> emails = new ArrayList<>();

        /* Check link customer csv is correct */
        if (Objects.isNull(customers)) {
            throw new FileNotFoundException(Constant.CUSTOMER_NOT_FOUND);
        }

        /* Check link email template is correct */
        if (Objects.isNull(emailTemplate)) {
            throw new FileNotFoundException(Constant.EMAIL_TEMPLATE_NOT_FOUND);
        }

        /* Add email to list */
        customers.forEach(customer -> {
            if (StringUtils.isEmpty(customer.getEmail())) {
                errorCustomers.add(customer);
            } else {
                Email email = emailMapper.toEmail(emailTemplate, customer);
                emails.add(email);
            }
        });

        /* Save error customer to csv file */
        if (!errorCustomers.isEmpty()) {
            csvChangeFormService.toCustomerCsv(errorCustomers);
        }

        /* Save email csv for each customer */
        jsonChangeFormService.emailToJson(emails, outputFolderPath);
    }
}
