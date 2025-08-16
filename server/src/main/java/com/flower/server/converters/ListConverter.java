package com.flower.server.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class ListConverter implements AttributeConverter<List<String>, String> {

  final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<String> list) {
    if (CollectionUtils.isEmpty(list)) {
      return null;
    }
    return String.join(",", list);
  }

  @Override
  public List<String> convertToEntityAttribute(String listAsString) {
    if (StringUtils.isBlank(listAsString)) {
      return null;
    }
    return new ArrayList<>(List.of(listAsString.split(",")));
  }
}
