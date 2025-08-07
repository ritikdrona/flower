package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
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

  public EntryDTO createEntry(EntryDTO entryDTO) {
    validateFormExistsAndValuesConformToSchema(entryDTO);

    Entry entry = new Entry();
    entry.setFormId(entryDTO.getFormId());
    entry.setValues(entryDTO.getValues().toString());
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

  public EntryDTO updateEntry(EntryDTO entryDTO, String formId) {
    Optional<Entry> entryOpt = entryRepository.findById(entryDTO.getId());
    if (entryOpt.isEmpty() || !entryOpt.get().getFormId().equals(formId)) {
      throw new RuntimeException("Entry not found for update");
    }

    validateFormExistsAndValuesConformToSchema(entryDTO);

    Entry entry = entryOpt.get();
    entry.setValues(entryDTO.getValues().toString());
    entry = entryRepository.save(entry);

    return converter.convert(entry, new TypeReference<>() {});
  }

  public Boolean deleteEntry(String entryId, String formId) {
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

  private void validateFormExistsAndValuesConformToSchema(EntryDTO entryDTO) {
    Optional<Form> form = formRepository.findById(entryDTO.getFormId());
    if (form.isEmpty()) {
      throw new RuntimeException("Form does not exist");
    }

    JsonSchema schema = jsonSchemaFactory.getSchema(form.get().getSchema());
    Set<ValidationMessage> validationMessages = schema.validate(entryDTO.getValues());
    if (!validationMessages.isEmpty()) {
      String errorMessage =
          validationMessages.stream()
              .map(ValidationMessage::getError)
              .collect(Collectors.joining("\n"));
      throw new RuntimeException("Validation Errors:\n" + errorMessage);
    }
  }
}
