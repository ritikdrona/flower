package com.flower.server.controllers;

import com.flower.server.dtos.generic.ApiRequest;
import com.flower.server.dtos.generic.ApiResponse;
import com.flower.server.dtos.generic.ApiSingleRequest;
import com.flower.server.dtos.generic.ApiSingleResponse;
import com.flower.server.dtos.EntryDTO;
import com.flower.server.services.EntryService;
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

import java.util.List;

@RestController
@RequestMapping("forms/{formId}/entries")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    public ResponseEntity<ApiSingleResponse<EntryDTO>> createEntry(
            @RequestBody ApiSingleRequest<EntryDTO> request,
            @PathVariable String formId) {
        EntryDTO created = entryService.createEntry(request.getRequest());
        return ResponseEntity.ok(new ApiSingleResponse<>(created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<EntryDTO>> getEntries(@PathVariable String formId) {
        List<EntryDTO> entries = entryService.getEntries(formId);
        return ResponseEntity.ok(new ApiResponse<>(entries));
    }

    @GetMapping("{entryId}")
    public ResponseEntity<ApiSingleResponse<EntryDTO>> getEntry(
            @PathVariable String formId,
            @PathVariable String entryId) {
        EntryDTO entry = entryService.getEntry(formId, entryId);
        return ResponseEntity.ok(new ApiSingleResponse<>(entry));
    }

    @PutMapping("{entryId}")
    public ResponseEntity<ApiSingleResponse<EntryDTO>> updateEntry(
            @RequestBody ApiSingleRequest<EntryDTO> request,
            @PathVariable String formId,
            @PathVariable String entryId) {
        EntryDTO updated = entryService.updateEntry(request.getRequest(), formId);
        return ResponseEntity.ok(new ApiSingleResponse<>(updated));
    }

    @DeleteMapping("{entryId}")
    public ResponseEntity<ApiSingleResponse<Boolean>> deleteEntry(
            @PathVariable String formId,
            @PathVariable String entryId) {
        Boolean deleted = entryService.deleteEntry(entryId, formId);
        return ResponseEntity.ok(new ApiSingleResponse<>(deleted));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteEntries(
            @PathVariable String formId,
            @RequestBody ApiRequest<String> request) {
        List<String> deletedIds = entryService.deleteEntries(formId, request.getRequest());
        return ResponseEntity.ok(new ApiResponse<>(deletedIds));
    }
}
