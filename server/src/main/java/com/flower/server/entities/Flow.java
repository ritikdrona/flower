package com.flower.server.entities;

import com.flower.server.converters.ListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "flows")
@NoArgsConstructor
public class Flow {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;
}
