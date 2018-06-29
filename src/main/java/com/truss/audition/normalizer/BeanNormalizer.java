package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;

public class BeanNormalizer {

    private AbstractNormalizer chain;

    public BeanNormalizer() {
        initializeChain();
    }

    private void initializeChain() {
        chain = new TimestampNormalizer();
        AbstractNormalizer zipNormalizer = new ZipNormalizer();
        AbstractNormalizer nameNormalizer = new NameNormalizer();
        AbstractNormalizer totalDurationNormalizer = new TotalDurationNormalizer();
        zipNormalizer.setNext(nameNormalizer);
        nameNormalizer.setNext(totalDurationNormalizer);
        chain.setNext(zipNormalizer);

        //TODO still need to deal with Unicode Characters....
    }

    public void normalize(CsvBean bean) {
        chain.normalize(bean);
    }


}
