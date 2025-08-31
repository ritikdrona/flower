package com.flower.server.repositories;

import com.flower.server.entities.FlowStep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowStepRepository extends JpaRepository<FlowStep, String> {

  List<FlowStep> findByFlowId(String flowId);

  long deleteByFlowId(String flowId);
}
