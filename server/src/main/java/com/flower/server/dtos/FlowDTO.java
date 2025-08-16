package com.flower.server.dtos;

import java.util.List;
import lombok.Data;

@Data
public class FlowDTO {

  String id;

  List<FlowStepDTO> steps;
}
