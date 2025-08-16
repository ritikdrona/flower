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
@Table(name = "flow_step_log")
@NoArgsConstructor
public class FlowStepLog {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  String fromStageId;

  String toStageId;

  @Convert(converter = JsonNodeConverter.class)
  JsonNode fieldValues;

  String triggeredByUserId;

  String flowEntryId;
}
