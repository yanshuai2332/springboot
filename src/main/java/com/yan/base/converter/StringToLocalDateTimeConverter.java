package com.yan.base.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yanshuai
 * @date 17/8/7
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	private DateTimeFormatter formatter;

	public StringToLocalDateTimeConverter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public LocalDateTime convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}

		// TODO handler exception
		return LocalDateTime.parse(source, formatter);

	}
}
