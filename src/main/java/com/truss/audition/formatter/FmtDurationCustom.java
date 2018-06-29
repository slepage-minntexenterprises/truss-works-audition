package com.truss.audition.formatter;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.time.Duration;

public class FmtDurationCustom extends CellProcessorAdaptor {
    public FmtDurationCustom() {
    }

    public FmtDurationCustom(CellProcessor next) {
        super(next);
    }

    @Override
    public <T> T execute(Object value, CsvContext context) {
        validateInputNotNull(value, context);  // throws an Exception if the input is null
        if (!(value instanceof Duration)) {
            throw new SuperCsvCellProcessorException(Duration.class, value, context, this);
        } else {
            String result = DurationFormatUtils.formatDuration(((Duration) value).toMillis(), "H:mm:ss.SSS", true);
            return this.next.execute(result, context);
        }
    }
}
