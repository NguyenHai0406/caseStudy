package com.java.study.service;

import com.java.study.dto.Email;

import java.util.List;

public interface JsonChangeFormService {
    /**
     * Get the email from path email template
     * @param path
     * @return
     */
    Email jsonToEmail(String path);

    /**
     * Convert the emails to json with path folder
     * @param emails
     * @param folderPath
     */
    boolean emailToJson(List<Email> emails, String folderPath);
}
