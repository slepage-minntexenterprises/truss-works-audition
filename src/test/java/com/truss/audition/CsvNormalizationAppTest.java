package com.truss.audition;


import org.junit.Test;

import java.io.FileNotFoundException;

public class CsvNormalizationAppTest {

    @Test
    public void testMain() throws Exception {
        //simple test, fails on any exception....
        CsvNormalizationApp.main(new String[]{"sample.csv", "normalized_test.csv"});
    }

    @Test(expected = FileNotFoundException.class)
    public void testMain_BadInput_BlankOutput() throws Exception {
        CsvNormalizationApp.main(new String[]{"sample_1.csv", ""});
    }

}