package com.flower.server.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class FlowStepDTO {

  String id;

  JsonNode schema;

  String flowId;

  int order;
}
