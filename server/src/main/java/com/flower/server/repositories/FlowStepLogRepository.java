package com.flower.server.repositories;

import com.flower.server.entities.FlowStepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowStepLogRepository extends JpaRepository<FlowStepLog, String> {}
