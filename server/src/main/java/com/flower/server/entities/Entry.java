package com.flower.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "entries")
public class Entry {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  String formId;

  String values; // stored validated values of an entry against a form as json
}
