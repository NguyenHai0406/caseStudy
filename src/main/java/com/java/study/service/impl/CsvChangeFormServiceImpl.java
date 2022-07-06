package com.java.study.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.java.study.dto.Customer;
import com.java.study.service.CsvChangeFormService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
@Slf4j
public class CsvChangeFormServiceImpl implements CsvChangeFormService {
    @Override
    public List<Customer> csvToCustomers(String path) {
        /* Check path have suffix - csv */
        if (!path.contains(".csv")) {
            path = path.concat(".csv");
        }

        /* Convert data to the customers */
        try {
            List<Customer> customers = new CsvToBeanBuilder(new FileReader(path))
                    .withType(Customer.class)
                    .build()
                    .parse();
            return customers;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean toCustomerCsv(List<Customer> customers) {
        // name of generated csv
        final String CSV_LOCATION = "src/main/java/com/java/study/path/to/errors.csv";

        /* Convert data form object to file csv */
        try {
            CsvMapper mapper = new CsvMapper();
            mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

            CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                    .addColumn("TITLE")
                    .addColumn("FIST_NAME")
                    .addColumn("LAST_NAME")
                    .addColumn("EMAIL")
                    .build();

            ObjectWriter writer = mapper.writerFor(Customer.class).with(schema);
            File file = new File(CSV_LOCATION);
            writer.writeValues(file).writeAll(customers);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
