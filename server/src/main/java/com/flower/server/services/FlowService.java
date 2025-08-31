package com.flower.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flower.server.dtos.FlowDTO;
import com.flower.server.dtos.FlowStepDTO;
import com.flower.server.entities.Flow;
import com.flower.server.repositories.FlowRepository;
import com.flower.server.utils.Converter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowService {

  private final FlowRepository flowRepository;

  private final FlowStepService flowStepService;

  private final Converter converter;

  public FlowDTO createFlow(FlowDTO flowDTO) {
    Flow flow = new Flow();
    flowRepository.save(flow);

    if (CollectionUtils.isEmpty(flowDTO.getSteps()) || flowDTO.getSteps().size() < 2) {
      throw new RuntimeException("Flow should have at least two steps");
    }

    List<FlowStepDTO> flowStepDTOS =
        flowStepService.createFlowSteps(flowDTO.getSteps(), flow.getId());

    return new FlowDTO(flow.getId(), flowStepDTOS);
  }

  public List<FlowDTO> getFlows() {
    List<Flow> flows = flowRepository.findAll();
    List<FlowDTO> flowDTOS = converter.convertCollection(flows, new TypeReference<>() {});

    Map<String, List<FlowStepDTO>> flowStepMap = flowStepService.getFlowSteps();
    flowDTOS.forEach(flowDTO -> flowDTO.setSteps(flowStepMap.get(flowDTO.getId())));

    return flowDTOS;
  }

  public FlowDTO getFlow(String flowId) {
    Optional<Flow> flowOptional = flowRepository.findById(flowId);
    if (flowOptional.isEmpty()) {
      throw new RuntimeException("Flow not found with id: " + flowId);
    }
    return new FlowDTO(flowId, flowStepService.getFlowStepsByFlowId(flowId));
  }

  public FlowDTO updateFlow(String flowId, FlowDTO flowDTO) {
    Optional<Flow> flowOptional = flowRepository.findById(flowId);
    if (flowOptional.isEmpty()) {
      throw new RuntimeException("Flow not found with id: " + flowId);
    }
    return new FlowDTO(flowId, flowStepService.updateFlowSteps(flowDTO.getSteps(), flowId));
  }

  public boolean deleteFlow(String flowId) {
    flowRepository.deleteById(flowId);
    flowStepService.deleteFlowStepsByFlowId(flowId);
    return true;
  }
}
