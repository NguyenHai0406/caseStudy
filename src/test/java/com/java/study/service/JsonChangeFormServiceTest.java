package com.java.study.service;

import com.java.study.dto.Email;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JsonChangeFormServiceTest extends ConstantTestService {
    @Autowired
    JsonChangeFormService service;

    @AfterEach
    void afterEach() {
        File file = new File(EMAIL_OUTPUT_FOLDER);
        file.delete();
    }

    @ParameterizedTest
    @DisplayName("Test read email template from json is success")
    @ValueSource(strings = {EMAIL_TEMPLATE_JSON, EMAIL_TEMPLATE_NOT_SUFFIX})
    void jsonToEmail(String path) {
        Email email = service.jsonToEmail(path);
        assertNotNull(email);
        Email expected = createTemplate();
        assertEquals(expected, email);
    }

    @Test
    @DisplayName("Test read email template not found")
    void jsonToEmail_NotFound() {
        Email email = service.jsonToEmail("");
        assertNull(email);
    }

    @Test
    @DisplayName("Test write email to json")
    void emailToJson() {
        boolean actual = service.emailToJson(createEmails(), EMAIL_OUTPUT_FOLDER);
        assertTrue(actual);
        File file = new File(EMAIL_OUTPUT_FOLDER);
        assertTrue(file.canRead());
        File fileEmail1 = new File(EMAIL_OUTPUT_1);
        assertTrue(fileEmail1.canRead());
        File fileEmail2 = new File(EMAIL_OUTPUT_2);
        assertTrue(fileEmail2.canRead());
    }

    private Email createTemplate() {
        return Email.builder()
                .from("The Marketing Team<NguyenHai@Vip.com>")
                .subject("A new product is being launched soon...")
                .mimeType("text/plain")
                .body("Hi {{TITLE}} {{FIRST_NAME}} {{LAST_NAME}},\nToday, {{TODAY}}, we would like to tell you that... Sincerely,\n The Marketing team")
                .build();
    }

    private List<Email> createEmails() {
        Email email1 = Email.builder()
                .from("NguyenHai")
                .to("NguyenHai@gmail.com")
                .body("Hello")
                .subject("Test")
                .mimeType("text/plant")
                .build();
        Email email2 = Email.builder()
                .from("Smith")
                .to("john.smith@example.com")
                .body("Hello there")
                .subject("Test subject")
                .mimeType("text/plant")
                .build();
        return List.of(email1, email2);
    }
}
