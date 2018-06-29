package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;

public class NameNormalizer extends AbstractNormalizer {

    private AbstractNormalizer nextNormalizer;


    @Override
    public void normalize(CsvBean bean) {

        String fullName = bean.getFullName();
        try {
            byte[] fullNameAsBytes = fullName.getBytes("UTF-8");
            fullName = new String(fullNameAsBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        bean.setFullName(StringUtils.upperCase(fullName));
        if (nextNormalizer != null) {
            nextNormalizer.normalize(bean);
        }
    }

    @Override
    public void setNext(AbstractNormalizer next) {
        this.nextNormalizer = next;
    }
}
