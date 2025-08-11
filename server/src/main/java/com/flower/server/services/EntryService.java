package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.flower.server.dtos.EntryDTO;
import com.flower.server.entities.Entry;
import com.flower.server.entities.Form;
import com.flower.server.repositories.EntryRepository;
import com.flower.server.repositories.FormRepository;
import com.flower.server.utils.Converter;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryService {

  private final EntryRepository entryRepository;

  private final FormRepository formRepository;

  private final Converter converter;

  private final JsonSchemaFactory jsonSchemaFactory;

  public EntryDTO createEntry(String formId, JsonNode fieldValues) {
    validateFormExistsAndValuesConformToSchema(formId, fieldValues);

    Entry entry = new Entry();
    entry.setFormId(formId);
    entry.setFieldValues(fieldValues);
    entryRepository.save(entry);

    return converter.convert(entry, new TypeReference<>() {});
  }

  public List<EntryDTO> getEntries(String formId) {
    List<Entry> entries = entryRepository.findByFormId(formId);
    return converter.convertCollection(entries, new TypeReference<>() {});
  }

  public EntryDTO getEntry(String formId, String entryId) {
    Optional<Entry> entryOpt = entryRepository.findById(entryId);
    if (entryOpt.isEmpty() || !entryOpt.get().getFormId().equals(formId)) {
      throw new RuntimeException(
          "Entry not found for formId: " + formId + " and entryId: " + entryId);
    }
    return converter.convert(entryOpt.get(), new TypeReference<>() {});
  }

  public EntryDTO updateEntry(String formId, String entryId, JsonNode fieldValues) {
    Optional<Entry> entryOpt = entryRepository.findById(entryId);
    if (entryOpt.isEmpty() || !entryOpt.get().getFormId().equals(formId)) {
      throw new RuntimeException("Entry not found for update");
    }

    validateFormExistsAndValuesConformToSchema(formId, fieldValues);

    Entry entry = entryOpt.get();
    entry.setFieldValues(fieldValues);
    entry = entryRepository.save(entry);

    return converter.convert(entry, new TypeReference<>() {});
  }

  public Boolean deleteEntry(String entryId, String formId) {
    // we are checking here for formId too, because we don't want to delete
    // entries for another form when dealing with one
    Optional<Entry> entryOpt = entryRepository.findByIdAndFormId(entryId, formId);
    if (entryOpt.isEmpty()) {
      return false;
    }
    entryRepository.deleteById(entryId);
    return true;
  }

  public List<String> deleteEntries(String formId, List<String> entryIds) {
    List<Entry> entries = entryRepository.findAllByFormIdAndIdIn(formId, entryIds);
    List<String> deletedIds = entries.stream().map(Entry::getId).collect(Collectors.toList());
    entryRepository.deleteAllById(deletedIds);
    return deletedIds;
  }

  private void validateFormExistsAndValuesConformToSchema(String formId, JsonNode fieldValues) {
    Optional<Form> form = formRepository.findById(formId);
    if (form.isEmpty()) {
      throw new RuntimeException("Form does not exist");
    }

    JsonSchema schema = jsonSchemaFactory.getSchema(form.get().getSchema());
    Set<ValidationMessage> validationMessages = schema.validate(fieldValues);
    if (!validationMessages.isEmpty()) {
      String errorMessage =
          validationMessages.stream()
              .map(ValidationMessage::getError)
              .collect(Collectors.joining("\n"));
      throw new RuntimeException("Validation Errors:\n" + errorMessage);
    }
  }
}
