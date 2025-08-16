package com.flower.server.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class FormEntryDTO {

  String id;

  String formId;

  JsonNode fieldValues;

  // TODO: see how userId can be integrated here, maybe we can as we are building
  //  this for orgs internal purposes rather than online anonymous survey forms
  //  Even if it is for survey, maybe we can still ask for login.

}
