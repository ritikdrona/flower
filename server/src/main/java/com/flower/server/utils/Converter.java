package com.flower.server.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Converter {

  final ObjectMapper objectMapper;

  public <T, R> R convert(T object, TypeReference<R> typeReference) {
    return objectMapper.convertValue(object, typeReference);
  }

  public <T, R> List<R> convertCollection(
      List<T> collection, TypeReference<List<R>> typeReference) {
    return objectMapper.convertValue(collection, typeReference);
  }
}
