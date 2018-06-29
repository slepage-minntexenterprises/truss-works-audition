package com.truss.audition.csv;

import com.truss.audition.model.CsvBean;
import com.truss.audition.parser.ParseToDuration;
import com.truss.audition.parser.ParseToUsPacificZonedDateTime;
import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class CsvFileReader {


    private ICsvBeanReader beanReader = null;

    private static final Logger LOG = Logger.getLogger(CsvFileReader.class);


    public List<CsvBean> read(InputStreamReader inputStreamReader) throws Exception {

        List<CsvBean> beans = new ArrayList<>();

        try {
            beanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);

            beanReader.getHeader(true);

            final String[] header = new String[]{
                    "timestamp", "address", "zip", "fullName", "fooDuration", "barDuration", null, "notes"
            };

            final CellProcessor[] processors = getProcessors();

            CsvBean csvBean;
            while ((csvBean = beanReader.read(CsvBean.class, header, processors)) != null) {
                LOG.debug(String.format("lineNo=%s, rowNo=%s, csvBean=%s", beanReader.getLineNumber(),
                        beanReader.getRowNumber(), csvBean));
                beans.add(csvBean);
            }
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
        return beans;
    }

    private CellProcessor[] getProcessors() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yy h:mm:ss a");


        return new CellProcessor[]{
                new ParseToUsPacificZonedDateTime(dateTimeFormatter), //move this from Pacific to Eastern
                new NotNull(), //address pass through with a Unicode Validation
                new NotNull(), //zip, we need a padding here...
                new NotNull(), //fullName needs to be upper case
                new ParseToDuration(), //need a specialized parser for the text... h:mm:ss.SSS
                new ParseToDuration(),
                null,
                new Optional(new NotNull()) //replace invalid UTF-8 with Unicode Replacement Character \uFFFD

        };


    }
}
