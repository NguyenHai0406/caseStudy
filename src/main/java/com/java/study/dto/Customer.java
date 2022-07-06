package com.java.study.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @CsvBindByName(column = "TITLE")
    @JsonProperty("TITLE")
    private String title;

    @CsvBindByName(column = "FIST_NAME")
    @JsonProperty("FIST_NAME")
    private String firstName;

    @CsvBindByName(column = "LAST_NAME")
    @JsonProperty("LAST_NAME")
    private String lastName;

    @CsvBindByName(column = "EMAIL")
    @JsonProperty("EMAIL")
    private String email;
}
