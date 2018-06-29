package com.truss.audition.csv;


import com.truss.audition.formatter.FmtDurationCustom;
import com.truss.audition.model.CsvBean;
import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.time.FmtZonedDateTime;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvFileWriter {

    private ICsvBeanWriter beanWriter;
    private static final Logger LOG = Logger.getLogger(CsvFileWriter.class);


    public CsvFileWriter(BufferedWriter fileWriter) {
        if (fileWriter == null) {
            throw new IllegalArgumentException("File writer cannot be null");
        }
        this.beanWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);
    }


    public void writeBeans(List<CsvBean> csvBeans) throws IOException {
        final String[] headers = new String[]{
                "Timestamp", "Address", "Zip", "FullName", "FooDuration", "BarDuration", "TotalDuration", "Notes"
        };
        beanWriter.writeHeader(headers);
        CellProcessor[] processors = getProcessors();
        try {
            csvBeans.forEach(bean -> {
                try {
                    beanWriter.write(bean, headers, processors);
                } catch (IOException e) {
                    LOG.error("Could not write bean ", e);
                }
            });
            for (final CsvBean bean : csvBeans) {
                beanWriter.write(bean, headers, processors);
                beanWriter.flush();
            }
        } finally {
            closeWriter();
        }

    }

    private void closeWriter() throws IOException {
        if (beanWriter != null) {
            beanWriter.flush();
            beanWriter.close();
        }
    }

    private CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new FmtZonedDateTime(DateTimeFormatter.ISO_OFFSET_DATE_TIME), //timestamp
                new NotNull(), //address
                new NotNull(), //zip,
                new NotNull(), //fullName
                new FmtDurationCustom(), //foo duration
                new FmtDurationCustom(), //bar duration
                new FmtDurationCustom(), //total duration
                new Optional(new NotNull()) // notes
        };
    }
}
