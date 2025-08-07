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
@Table(name = "forms")
@NoArgsConstructor
public class Form {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    String id;

    String schema; // Stored fields, their types and validations if any in json

    String userId; // Not sure yet if we need orgId or not. but we first work on MVP for 1 org

    public Form(String schema, String userId) {
        this.schema = schema;
        this.userId = userId;
    }

}
