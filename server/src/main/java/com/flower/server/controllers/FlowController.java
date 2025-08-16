package com.flower.server.controllers;

import com.flower.server.dtos.FlowDTO;
import com.flower.server.dtos.generic.ApiResponse;
import com.flower.server.dtos.generic.ApiSingleRequest;
import com.flower.server.dtos.generic.ApiSingleResponse;
import java.util.ArrayList;
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

  @PostMapping
  public ResponseEntity<ApiSingleResponse<FlowDTO>> createFlow(
      @RequestBody ApiSingleRequest<FlowDTO> request) {
    return ResponseEntity.ok(new ApiSingleResponse<>(new FlowDTO()));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<FlowDTO>> getFlows() {
    return ResponseEntity.ok(new ApiResponse<>(new ArrayList<>()));
  }

  @GetMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<FlowDTO>> getFlow(@PathVariable String flowId) {
    return ResponseEntity.ok(new ApiSingleResponse<>(new FlowDTO()));
  }

  @PutMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<FlowDTO>> updateFlow(
      @PathVariable String flowId, @RequestBody ApiSingleRequest<FlowDTO> request) {
    return ResponseEntity.ok(new ApiSingleResponse<>(new FlowDTO()));
  }

  @DeleteMapping("{flowId}")
  public ResponseEntity<ApiSingleResponse<Boolean>> deleteFlow(@PathVariable String flowId) {
    return ResponseEntity.ok(new ApiSingleResponse<>(Boolean.TRUE));
  }
}
