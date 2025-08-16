package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.flower.server.dtos.FormEntryDTO;
import com.flower.server.entities.FormEntry;
import com.flower.server.entities.Form;
import com.flower.server.repositories.FormEntryRepository;
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
public class FormEntryService {

  private final FormEntryRepository formEntryRepository;

  private final FormRepository formRepository;

  private final Converter converter;

  private final JsonSchemaFactory jsonSchemaFactory;

  public FormEntryDTO createEntry(String formId, JsonNode fieldValues) {
    validateFormExistsAndValuesConformToSchema(formId, fieldValues);

    FormEntry formEntry = new FormEntry();
    formEntry.setFormId(formId);
    formEntry.setFieldValues(fieldValues);
    formEntryRepository.save(formEntry);

    return converter.convert(formEntry, new TypeReference<>() {});
  }

  public List<FormEntryDTO> getEntries(String formId) {
    List<FormEntry> entries = formEntryRepository.findByFormId(formId);
    return converter.convertCollection(entries, new TypeReference<>() {});
  }

  public FormEntryDTO getEntry(String formId, String entryId) {
    Optional<FormEntry> entryOpt = formEntryRepository.findById(entryId);
    if (entryOpt.isEmpty() || !entryOpt.get().getFormId().equals(formId)) {
      throw new RuntimeException(
          "Entry not found for formId: " + formId + " and entryId: " + entryId);
    }
    return converter.convert(entryOpt.get(), new TypeReference<>() {});
  }

  public FormEntryDTO updateEntry(String formId, String entryId, JsonNode fieldValues) {
    Optional<FormEntry> entryOpt = formEntryRepository.findById(entryId);
    if (entryOpt.isEmpty() || !entryOpt.get().getFormId().equals(formId)) {
      throw new RuntimeException("Entry not found for update");
    }

    validateFormExistsAndValuesConformToSchema(formId, fieldValues);

    FormEntry formEntry = entryOpt.get();
    formEntry.setFieldValues(fieldValues);
    formEntry = formEntryRepository.save(formEntry);

    return converter.convert(formEntry, new TypeReference<>() {});
  }

  public Boolean deleteEntry(String entryId, String formId) {
    // we are checking here for formId too, because we don't want to delete
    // entries for another form when dealing with one
    Optional<FormEntry> entryOpt = formEntryRepository.findByIdAndFormId(entryId, formId);
    if (entryOpt.isEmpty()) {
      return false;
    }
    formEntryRepository.deleteById(entryId);
    return true;
  }

  public List<String> deleteEntries(String formId, List<String> entryIds) {
    List<FormEntry> entries = formEntryRepository.findAllByFormIdAndIdIn(formId, entryIds);
    List<String> deletedIds = entries.stream().map(FormEntry::getId).collect(Collectors.toList());
    formEntryRepository.deleteAllById(deletedIds);
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
