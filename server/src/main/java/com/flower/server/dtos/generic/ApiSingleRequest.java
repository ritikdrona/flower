package com.flower.server.dtos.generic;

import lombok.Data;

@Data
public class ApiSingleRequest<T> {
  T request;
}
