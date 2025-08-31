package com.flower.server.controllers;

import com.flower.server.dtos.FlowDTO;
import com.flower.server.dtos.generic.ApiResponse;
import com.flower.server.dtos.generic.ApiSingleRequest;
import com.flower.server.dtos.generic.ApiSingleResponse;
import com.flower.server.services.FlowService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flows")
@RequiredArgsConstructor
public class FlowController {

  private final FlowService flowService;

  @PostMapping
  public ResponseEntity<ApiSingleResponse<FlowDTO>> createFlow(
      @RequestBody ApiSingleRequest<FlowDTO> request) {
    FlowDTO created = flowService.createFlow(request.getRequest());
    return ResponseEntity.ok(new ApiSingleResponse<>(created));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<FlowDTO>> getFlows() {
    List<FlowDTO> flows = flowService.getFlows();
    return ResponseEntity.ok(new ApiResponse<>(flows));
  }

  @GetMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<FlowDTO>> getFlow(@PathVariable String flowId) {
    FlowDTO flowDTO = flowService.getFlow(flowId);
    return ResponseEntity.ok(new ApiSingleResponse<>(flowDTO));
  }

  @PutMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<FlowDTO>> updateFlow(
      @PathVariable String flowId, @RequestBody ApiSingleRequest<FlowDTO> request) {
    FlowDTO updated = flowService.updateFlow(flowId, request.getRequest());
    return ResponseEntity.ok(new ApiSingleResponse<>(updated));
  }

  @DeleteMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<Boolean>> deleteFlow(@PathVariable String flowId) {
    Boolean deleted = flowService.deleteFlow(flowId);
    return ResponseEntity.ok(new ApiSingleResponse<>(deleted));
  }
}
