package com.flower.server.repositories;

import com.flower.server.entities.FormEntry;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormEntryRepository extends JpaRepository<FormEntry, String> {

  List<FormEntry> findByFormId(String formId);

  Optional<FormEntry> findByIdAndFormId(String id, String formId);

  List<FormEntry> findAllByFormIdAndIdIn(String formId, List<String> ids);

  long deleteByFormId(String formId);
}
