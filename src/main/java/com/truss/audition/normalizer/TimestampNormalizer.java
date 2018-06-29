package com.truss.audition.normalizer;

import com.truss.audition.model.CsvBean;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestampNormalizer extends AbstractNormalizer {

    private AbstractNormalizer nextNormalizer;
    private ZoneId newYorkZoneId = ZoneId.of("America/New_York");

    @Override
    public void normalize(CsvBean bean) {

        ZonedDateTime eastCoast = bean.getTimestamp().withZoneSameInstant(newYorkZoneId);
        bean.setTimestamp(eastCoast);

        if (nextNormalizer != null) {
            nextNormalizer.normalize(bean);
        }

    }

    @Override
    public void setNext(AbstractNormalizer next) {
        this.nextNormalizer = next;
    }
}
