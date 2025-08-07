package com.flower.server.dtos.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSingleResponse<T> {
  T response;
}
