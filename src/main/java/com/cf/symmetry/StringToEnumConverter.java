package com.cf.symmetry;

import com.cf.symmetry.exceptions.StringToENumConverterException;
import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

public class StringToEnumConverter implements Converter<String, MethodEvaluation> {
    @Override
    public MethodEvaluation convert(String source) {
        try {
            return MethodEvaluation.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new StringToENumConverterException("Inserted method is not recognized!", e);
        }

    }
}
