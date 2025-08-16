package com.flower.server.repositories;

import com.flower.server.entities.FlowEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowEntryRepository extends JpaRepository<FlowEntry, String> {}
