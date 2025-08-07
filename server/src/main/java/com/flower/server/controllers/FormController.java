package com.flower.server.controllers;

import com.flower.server.dtos.generic.ApiResponse;
import com.flower.server.dtos.generic.ApiSingleRequest;
import com.flower.server.dtos.generic.ApiSingleResponse;
import com.flower.server.dtos.FormDTO;
import com.flower.server.services.FormService;
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
@RequestMapping("forms")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping
    public ResponseEntity<ApiSingleResponse<FormDTO>> createForm(@RequestBody ApiSingleRequest<FormDTO> request) {
        FormDTO created = formService.createForm(request.getRequest(), "userId");
        return ResponseEntity.ok(new ApiSingleResponse<>(created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<FormDTO>> getForms() {
        List<FormDTO> forms = formService.getForms();
        return ResponseEntity.ok(new ApiResponse<>(forms));
    }

    @GetMapping("{formId}")
    public ResponseEntity<ApiSingleResponse<FormDTO>> getForm(@PathVariable String formId) {
        FormDTO form = formService.getForm(formId);
        return ResponseEntity.ok(new ApiSingleResponse<>(form));
    }

    @PutMapping("{formId}")
    public ResponseEntity<ApiSingleResponse<FormDTO>> updateForm(@PathVariable String formId, @RequestBody ApiSingleRequest<FormDTO> request) {
        FormDTO updated = formService.updateForm(formId, request.getRequest());
        return ResponseEntity.ok(new ApiSingleResponse<>(updated));
    }

    @DeleteMapping("{formId}")
    public ResponseEntity<ApiSingleResponse<Boolean>> deleteForm(@PathVariable String formId) {
        Boolean deleted = formService.deleteForm(formId);
        return ResponseEntity.ok(new ApiSingleResponse<>(deleted));
    }

}
