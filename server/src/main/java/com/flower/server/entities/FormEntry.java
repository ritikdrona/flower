package com.flower.server.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.flower.server.converters.JsonNodeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "form_entries")
public class FormEntry {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  String formId;

  @Convert(converter = JsonNodeConverter.class)
  JsonNode fieldValues; // stored validated fieldValues of an entry against a form as json
}
