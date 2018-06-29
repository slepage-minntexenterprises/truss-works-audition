package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;

import java.time.Duration;

public class TotalDurationNormalizer extends AbstractNormalizer {

    private AbstractNormalizer nextNormalizer;

    @Override
    public void normalize(CsvBean bean) {
        Duration totalDuration = bean.getFooDuration().plus(bean.getBarDuration());
        bean.setTotalDuration(totalDuration);
        if (nextNormalizer != null) {
            nextNormalizer.normalize(bean);
        }
    }

    @Override
    public void setNext(AbstractNormalizer next) {
        this.nextNormalizer = next;
    }
}
