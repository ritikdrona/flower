package com.flower.server.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

  public <T, K, V> Map<K, V> convertToMap(
      List<T> collection, Function<T, K> keyMapper, Function<T, V> valueMapper) {
    Map<K, V> map = new HashMap<>();
    for (T item : collection) {
      map.put(keyMapper.apply(item), valueMapper.apply(item));
    }
    return map;
  }

  public <T, K, V> Map<K, List<V>> convertToMapList(
      List<T> collection, Function<T, K> keyMapper, Function<T, V> valueMapper) {
    Map<K, List<V>> map = new HashMap<>();
    for (T item : collection) {
      map.putIfAbsent(keyMapper.apply(item), new ArrayList<>());
      map.get(keyMapper.apply(item)).add(valueMapper.apply(item));
    }
    return map;
  }
}
