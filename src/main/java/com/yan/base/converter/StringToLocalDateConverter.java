package com.yan.base.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by chenjiahai on 17/8/7.
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        // TODO handler exception
        return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);

    }
}
