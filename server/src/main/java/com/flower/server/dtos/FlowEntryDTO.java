package com.flower.server.dtos;

import lombok.Data;

@Data
public class FlowEntryDTO {

  String id;

  String flowId;

  FlowStepDTO currentStep;
}
