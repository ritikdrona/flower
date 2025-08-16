package com.flower.server.repositories;

import com.flower.server.entities.FlowStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowStepRepository extends JpaRepository<FlowStep, String> {}
