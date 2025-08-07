package com.flower.server.dtos.generic;

import java.util.List;
import lombok.Data;

@Data
public class ApiRequest<T> {
  List<T> request;
}
