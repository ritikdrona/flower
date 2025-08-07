package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flower.server.dtos.FormDTO;
import com.flower.server.entities.Form;
import com.flower.server.repositories.FormRepository;
import com.flower.server.utils.Converter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormService {

  private final FormRepository formRepository;

  private final Converter converter;

  public FormDTO createForm(FormDTO formDTO, String userId) {
    // TODO: validate schema and userId here.
    Form form = new Form(formDTO.getSchema(), userId);
    form = formRepository.save(form);
    return converter.convert(form, new TypeReference<>() {});
  }

  public List<FormDTO> getForms() {
    List<Form> forms = formRepository.findAll();
    return forms.stream()
        .map(form -> converter.convert(form, new TypeReference<FormDTO>() {}))
        .collect(Collectors.toList());
  }

  public FormDTO getForm(String formId) {
    Optional<Form> formOpt = formRepository.findById(formId);
    if (formOpt.isEmpty()) {
      throw new RuntimeException("Form not found with id: " + formId);
    }
    return converter.convert(formOpt.get(), new TypeReference<>() {});
  }

  public FormDTO updateForm(String formId, FormDTO formDTO) {
    Optional<Form> formOpt = formRepository.findById(formId);
    if (formOpt.isEmpty()) {
      throw new RuntimeException("Form not found with id: " + formId);
    }
    Form form = formOpt.get();
    form.setSchema(formDTO.getSchema());
    form = formRepository.save(form);
    return converter.convert(form, new TypeReference<>() {});
  }

  public boolean deleteForm(String formId) {
    if (!formRepository.existsById(formId)) {
      return false;
    }
    formRepository.deleteById(formId);
    return true;
  }
}
