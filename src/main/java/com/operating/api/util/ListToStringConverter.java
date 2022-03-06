package com.operating.api.util;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h2> Conversão de Lista para String</h2>
 * Conversão para ser usado na model Atuacao
 */

public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute == null ? null : String.join(",",attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData == null ? Collections.emptyList() : Arrays.asList(dbData.split(","));
    }
}