package com.yan.base.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.yan.base.converter.StringToCommonEnumConverterFactory;
import com.yan.base.converter.StringToLocalDateConverter;
import com.yan.base.converter.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author yanshuai
 * @date 17/8/7
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	private static final String DEFAULT_LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_TIME_PATTERN);

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
			.serializationInclusion(JsonInclude.Include.NON_NULL)
			.serializationInclusion(JsonInclude.Include.NON_EMPTY)
			.serializerByType(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
			.deserializerByType(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
			.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER))
			.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
//		converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedHeaders("*")
			.allowedMethods("*")
			.allowedOrigins("*");
	}

	@Override
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService conversionService = new FormattingConversionService();
		addFormatters(conversionService);
		return conversionService;
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		// 覆盖DefaultConversionService的ConvererFactory顺序, 先注册自定义的
		registry.addConverterFactory(stringToCommonEnumConverterFactory());


		DefaultConversionService.addDefaultConverters(registry);
		DefaultFormattingConversionService.addDefaultFormatters(registry);

		registry.addConverter(stringToLocalDateConverter());
		registry.addConverter(stringToLocalDateTimeConverter());
	}

	public ConverterFactory stringToCommonEnumConverterFactory() {
		return new StringToCommonEnumConverterFactory();
	}

	public Converter stringToLocalDateTimeConverter() {
		return new StringToLocalDateTimeConverter(DATE_TIME_FORMATTER);
	}

	public Converter stringToLocalDateConverter() {
		return new StringToLocalDateConverter();
	}
}

