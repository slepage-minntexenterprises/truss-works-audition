package com.truss.audition.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ParseToUsPacificZonedDateTime extends CellProcessorAdaptor {

    private DateTimeFormatter dateTimeFormatter;

    private static final ZoneId US_PACIFIC = ZoneId.of("America/Los_Angeles");


    public ParseToUsPacificZonedDateTime(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public ParseToUsPacificZonedDateTime(CellProcessor next, DateTimeFormatter dateTimeFormatter) {
        super(next);
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public <T> T execute(Object value, CsvContext context) {
        validateInputNotNull(value, context);
        if (!(value instanceof String)) {
            throw new SuperCsvCellProcessorException(String.class, value, context, this);
        } else {
            ZonedDateTime result = LocalDateTime.parse(((String) value), dateTimeFormatter).atZone(US_PACIFIC);
            return this.next.execute(result, context);
        }
    }
}
