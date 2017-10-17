package com.yan.base.converter;


import com.yan.base.entity.CommonEntityEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author yanshuai
 * @date 17/8/7
 */
public class StringToCommonEnumConverterFactory implements ConverterFactory<String, CommonEntityEnum> {

    @Override
    public <T extends CommonEntityEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum<>(targetType);
    }

    private static class StringToEnum<T extends CommonEntityEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return CommonEntityEnum.getEnum(enumType, Integer.valueOf(source.trim()));
        }
    }

}
