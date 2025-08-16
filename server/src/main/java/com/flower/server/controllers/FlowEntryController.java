package com.flower.server.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.flower.server.dtos.FlowEntryDTO;
import com.flower.server.dtos.generic.ApiResponse;
import com.flower.server.dtos.generic.ApiSingleRequest;
import com.flower.server.dtos.generic.ApiSingleResponse;
import com.flower.server.services.FlowEntryService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flows/{flowId}/entries")
@RequiredArgsConstructor
public class FlowEntryController {

  @PostMapping
  public ResponseEntity<ApiSingleResponse<FlowEntryDTO>> createEntry(
      @RequestBody ApiSingleRequest<JsonNode> request, @PathVariable String flowId) {
    FlowEntryDTO created = flowEntryService.createEntry(flowId, request.getRequest());
    return ResponseEntity.ok(new ApiSingleResponse<>(created));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<FlowEntryDTO>> getEntries(@PathVariable String flowId) {
    List<FlowEntryDTO> entries = flowEntryService.getEntries(flowId);
    return ResponseEntity.ok(new ApiResponse<>(entries));
  }

  @GetMapping("{entryId}")
  public ResponseEntity<ApiSingleResponse<FlowEntryDTO>> getEntry(
      @PathVariable String flowId, @PathVariable String entryId) {
    FlowEntryDTO entry = flowEntryService.getEntry(flowId, entryId);
    return ResponseEntity.ok(new ApiSingleResponse<>(entry));
  }

  @PutMapping("{entryId}")
  public ResponseEntity<ApiSingleResponse<FlowEntryDTO>> updateEntry(
      @RequestBody ApiSingleRequest<JsonNode> request,
      @PathVariable String flowId,
      @PathVariable String entryId) {
    FlowEntryDTO updated = flowEntryService.updateEntry(flowId, entryId, request.getRequest());
    return ResponseEntity.ok(new ApiSingleResponse<>(updated));
  }

  @DeleteMapping("{entryId}")
  public ResponseEntity<ApiSingleResponse<Boolean>> deleteEntry(
      @PathVariable String flowId, @PathVariable String entryId) {
    Boolean deleted = flowEntryService.deleteEntry(entryId, flowId);
    return ResponseEntity.ok(new ApiSingleResponse<>(deleted));
  }

  @DeleteMapping
  public ResponseEntity<ApiResponse<String>> deleteEntries(
      @PathVariable String flowId, @RequestParam List<String> ids) {
    List<String> deletedIds = flowEntryService.deleteEntries(flowId, ids);
    return ResponseEntity.ok(new ApiResponse<>(deletedIds));
  }
}
