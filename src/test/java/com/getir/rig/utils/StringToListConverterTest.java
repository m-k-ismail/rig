package com.getir.rig.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StringToListConverterTest {

    private StringToListConverter stringToListConverter;

    @Before
    public void setUp() {
        stringToListConverter = new StringToListConverter();
    }

    @Test
    public void should_convert_string_to_database_column() {
        // Given
        List<Long> ids = Arrays.asList(1L, 2L);

        // When
        String dbColumn = stringToListConverter.convertToDatabaseColumn(ids);

        // Then
        Assert.assertEquals("1,2", dbColumn);
    }

    @Test
    public void should_convert_database_column_to_string() {
        // Given
        String ids = "1,2";

        // When
        List<Long> idsList = stringToListConverter.convertToEntityAttribute(ids);

        // Then
        Assert.assertEquals(Arrays.asList(1L, 2L), idsList);
    }
}
