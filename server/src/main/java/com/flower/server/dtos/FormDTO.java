package com.flower.server.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class FormDTO {

  String id;

  JsonNode schema; // Stored fields, their types and validations if any in json

  String userId;
}
