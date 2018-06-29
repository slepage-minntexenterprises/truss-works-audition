package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;
import org.apache.commons.lang.StringUtils;

public class ZipNormalizer extends AbstractNormalizer {

    private AbstractNormalizer nextNormalizer;

    @Override
    public void normalize(CsvBean bean) {
        String normalizedZip = StringUtils.leftPad(bean.getZip(), 5, "0");
        bean.setZip(normalizedZip);
        if (nextNormalizer != null) {
            nextNormalizer.normalize(bean);
        }
    }

    @Override
    public void setNext(AbstractNormalizer next) {

        this.nextNormalizer = next;
    }
}
