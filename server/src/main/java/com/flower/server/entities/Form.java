package com.flower.server.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.flower.server.converters.JsonNodeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "forms")
@NoArgsConstructor
public class Form {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  @Convert(converter = JsonNodeConverter.class)
  JsonNode schema; // Stored fields, their types and validations if any in json

  String userId; // Not sure yet if we need orgId or not. but we first work on MVP for 1 org

  public Form(JsonNode schema, String userId) {
    this.schema = schema;
    this.userId = userId;
  }
}
