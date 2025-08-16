package com.flower.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "flow_entries")
@NoArgsConstructor
public class FlowEntry {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  String flowId;

  String currentStepId;
}
