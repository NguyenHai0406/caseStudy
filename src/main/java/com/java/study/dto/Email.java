package com.java.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Email {
    private String from;
    private String to;
    private String subject;
    private String mimeType;
    private String body;
}
