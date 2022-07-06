package com.java.study.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.study.dto.Email;
import com.java.study.service.JsonChangeFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class JsonChangeFormServiceImpl implements JsonChangeFormService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Email jsonToEmail(String path) {
        /* Check path have suffix - json */
        if (!path.contains("json")) {
            path = path.concat(".json");
        }

        /* Convert data to Email object */
        try {
            return objectMapper.readValue(new FileReader(path), Email.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean emailToJson(List<Email> emails, String folderPath) {
        FileWriter fileWriter = null;
        /* loop up emails and save to csv */
        boolean isSuccess = true;
        for (int i = 1; i <= emails.size(); i++) {
            try {
                var result = objectMapper.writeValueAsString(emails.get(i-1));
                File file = new File(folderPath + "/email" + i + ".json");
                file.getParentFile().mkdirs();

                fileWriter = new FileWriter(file);
                fileWriter.write(result);
            } catch (IOException e) {
                log.error(e.getMessage());
                isSuccess = false;
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}
