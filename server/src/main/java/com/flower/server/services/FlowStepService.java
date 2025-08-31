package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flower.server.dtos.FlowStepDTO;
import com.flower.server.entities.FlowStep;
import com.flower.server.repositories.FlowStepRepository;
import com.flower.server.utils.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowStepService {

  private final FlowStepRepository flowStepRepository;

  private final Converter converter;

  protected List<FlowStepDTO> createFlowSteps(List<FlowStepDTO> flowStepDTOS, String flowId) {
    List<FlowStep> flowSteps = new ArrayList<>();

    for (FlowStepDTO flowStepDTO : flowStepDTOS) {
      FlowStep flowStep = new FlowStep();
      flowStep.setFlowId(flowId);
      flowStep.setSchema(flowStepDTO.getSchema());
      flowStep.setOrder(flowStepDTO.getOrder());
      flowSteps.add(flowStep);
    }

    flowStepRepository.saveAll(flowSteps);

    return converter.convertCollection(flowSteps, new TypeReference<>() {});
  }

  protected List<FlowStepDTO> getFlowStepsByFlowId(String flowId) {
    List<FlowStep> flowSteps = flowStepRepository.findByFlowId(flowId);
    return converter.convertCollection(flowSteps, new TypeReference<>() {});
  }

  protected Map<String, List<FlowStepDTO>> getFlowSteps() {
    List<FlowStep> flowSteps = flowStepRepository.findAll();
    List<FlowStepDTO> flowStepDTOS =
        converter.convertCollection(flowSteps, new TypeReference<>() {});
    return converter.convertToMapList(flowStepDTOS, FlowStepDTO::getFlowId, Function.identity());
  }

  protected List<FlowStepDTO> updateFlowSteps(List<FlowStepDTO> flowStepDTOS, String flowId) {
    List<FlowStep> flowSteps = flowStepRepository.findByFlowId(flowId);
    Map<String, FlowStep> flowStepMap =
        converter.convertToMap(flowSteps, FlowStep::getId, Function.identity());
    List<String> updatedFlowStepIds = flowStepDTOS.stream().map(FlowStepDTO::getId).toList();

    List<FlowStep> updatedFlowSteps = new ArrayList<>();
    List<FlowStep> deletedFlowSteps = new ArrayList<>();

    for (FlowStepDTO flowStepDTO : flowStepDTOS) {
      if (!updatedFlowStepIds.contains(flowStepDTO.getId())) {
        deletedFlowSteps.addAll(flowSteps);
        continue;
      }

      if (StringUtils.isBlank(flowStepDTO.getId())) {
        FlowStep flowStep = new FlowStep();
        flowStep.setFlowId(flowId);
        flowStep.setSchema(flowStepDTO.getSchema());
        flowStep.setOrder(flowStepDTO.getOrder());
        updatedFlowSteps.add(flowStep);
      } else if (flowStepMap.containsKey(flowStepDTO.getId())) {
        FlowStep flowStep = flowStepMap.get(flowStepDTO.getId());
        flowStep.setSchema(flowStepDTO.getSchema());
        flowStep.setOrder(flowStepDTO.getOrder());
        updatedFlowSteps.add(flowStep);
      } else {
        throw new RuntimeException(
            "Flow step not found for update with id: " + flowStepDTO.getId());
      }
    }

    flowStepRepository.saveAll(updatedFlowSteps);
    flowStepRepository.deleteAll(deletedFlowSteps);

    return converter.convertCollection(updatedFlowSteps, new TypeReference<>() {});
  }

  protected void deleteFlowStepsByFlowId(String flowId) {
    flowStepRepository.deleteByFlowId(flowId);
  }
}
