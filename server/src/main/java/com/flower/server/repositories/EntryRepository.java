package com.flower.server.repositories;

import com.flower.server.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {

    List<Entry> findByFormId(String formId);

    Optional<Entry> findByIdAndFormId(String id, String formId);

    List<Entry> findAllByFormIdAndIdIn(String formId, List<String> ids);

}
