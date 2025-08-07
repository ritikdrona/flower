package com.flower.server.dtos.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    List<T> response;
}
