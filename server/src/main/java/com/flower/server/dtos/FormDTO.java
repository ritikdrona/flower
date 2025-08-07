package com.flower.server.dtos;

import lombok.Data;

@Data
public class FormDTO {

  String id;

  String schema; // Stored fields, their types and validations if any in json

  String userId;
}
