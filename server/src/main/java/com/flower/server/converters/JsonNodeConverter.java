package com.flower.server.converters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;

public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

  final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(JsonNode jsonNode) {
    return jsonNode == null ? null : jsonNode.toString();
  }

  @Override
  public JsonNode convertToEntityAttribute(String jsonInDb) {
    if (StringUtils.isBlank(jsonInDb)) {
      return null;
    }

    try {
      return objectMapper.readTree(jsonInDb);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Failed to convert JSON string to JsonNode: " + jsonInDb, e);
    }
  }
}
