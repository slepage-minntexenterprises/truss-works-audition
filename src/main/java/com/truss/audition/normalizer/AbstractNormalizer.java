package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;

public abstract class AbstractNormalizer {


    AbstractNormalizer nextNormalizer;


    public abstract void normalize(CsvBean bean);

    public abstract void setNext(AbstractNormalizer next);

}
