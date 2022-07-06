package com.java.study.mapper;

import com.java.study.dto.Customer;
import com.java.study.dto.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    /**
     * Mapper from email and customer to email
     * @param email
     * @param customer
     * @return
     */
    @Mapping(target = "to", source = "customer.email")
    @Mapping(target = "body", expression = "java(replaceBody(email.getBody(), customer))")
    Email toEmail(Email email, Customer customer);

    /**
     * Replace body of email
     * @param body
     * @param customer
     * @return
     */
    @ObjectFactory
    default String replaceBody(String body, Customer customer) {
        String pattern = "dd MMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        body = body
                .replace("{{TITLE}}", customer.getTitle())
                .replace("{{FIRST_NAME}}", customer.getFirstName())
                .replace("{{LAST_NAME}}", customer.getLastName())
                .replace("{{TODAY}}", date);
        return body;
    }
}
