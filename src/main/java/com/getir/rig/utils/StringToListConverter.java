package com.getir.rig.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringToListConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> list) {
        if(list == null) return "";
        return list.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Long> convertToEntityAttribute(String str) {
        if(str == null) return new ArrayList<>();
        return Arrays.stream(str.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }
}