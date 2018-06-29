package com.truss.audition;


import com.truss.audition.csv.CsvFileReader;
import com.truss.audition.csv.CsvFileWriter;
import com.truss.audition.model.CsvBean;
import com.truss.audition.normalizer.BeanNormalizer;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class CsvNormalizationApp {
    private static final Logger LOG = Logger.getLogger(CsvNormalizationApp.class);

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Need an in and out file");
            System.exit(1);
        }

        File inFile = new File(args[0]);
        File outfile = new File(args[1]);
        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileWriter csvFileWriter = new CsvFileWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile, false), StandardCharsets.UTF_8)));
        List<CsvBean> beans = csvFileReader.read(new InputStreamReader(new FileInputStream(inFile), StandardCharsets.UTF_8));
        BeanNormalizer normalizer = new BeanNormalizer();
        beans.forEach(normalizer::normalize);
        beans.forEach(bean -> LOG.debug(bean.toString()));
        //write to csv
        csvFileWriter.writeBeans(beans);
    }

}
