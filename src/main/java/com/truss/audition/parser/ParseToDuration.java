package com.truss.audition.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.time.Duration;
import java.time.format.DateTimeParseException;

public class ParseToDuration extends CellProcessorAdaptor {

    public ParseToDuration() {
        super();
    }

    public ParseToDuration(CellProcessor next) {
        super(next);
    }

    @Override
    public <T> T execute(Object value, CsvContext context) {
        validateInputNotNull(value, context);  // throws an Exception if the input is null
        if (!(value instanceof String)) {
            throw new SuperCsvCellProcessorException(String.class, value, context, this);
        } else {
            Duration result;
            //this should be a String... substring it to hours, minutes, seconds, milliseconds on the ':'
            String[] values = ((String) value).split(":");
            try {
                result = Duration.parse(String.format("PT%sH%sM%sS", values[0], values[1], values[2]));
            } catch (DateTimeParseException var5) {
                throw new SuperCsvCellProcessorException("Failed to parse value as a Duration", context, this, var5);
            }
            return this.next.execute(result, context);
        }
    }
}
