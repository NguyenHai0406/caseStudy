package com.java.study.service;

import com.java.study.common.Constant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MainServiceTest extends ConstantTestService {
    @Autowired
    MainService service;

    @Test
    @DisplayName("Test output Email success")
    void outputEmail() {
        assertDoesNotThrow(() -> service.outputEmail(CUSTOMER_CSV, EMAIL_TEMPLATE_JSON, EMAIL_OUTPUT_FOLDER));
    }

    @ParameterizedTest
    @MethodSource("provideNotFoundFile")
    @DisplayName("Test output Email not found")
    void outputEmail_NotFound(String csvPath, String jsonPath, String errorMessage) {
        Throwable actual = assertThrows(Throwable.class, () -> service.outputEmail(csvPath, jsonPath, EMAIL_OUTPUT_FOLDER));
        assertEquals(FileNotFoundException.class, actual.getClass());
        assertEquals(errorMessage, actual.getMessage());
    }

    private static Stream<Arguments> provideNotFoundFile() {
        return Stream.of(
                Arguments.of(CUSTOMER_CSV, "", Constant.EMAIL_TEMPLATE_NOT_FOUND),
                Arguments.of("", EMAIL_TEMPLATE_JSON, Constant.CUSTOMER_NOT_FOUND)
        );
    }

}
