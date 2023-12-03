package com.example.demo.domain;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream tutorialsToCSV(List<Customer> customers) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Customer c : customers) {
                List<String> data = Arrays.asList(
                        String.valueOf(c.getId()),
                        c.getCity(),
                        c.getCountry(),
                        String.valueOf(c.getSalary()),
                        String.valueOf(c.getMarried()),
                        c.getGender(),
                        String.valueOf(c.getChildrenCount()),
                        c.getCreditCardType(),
                        c.getName(),
                        c.getEmail()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
