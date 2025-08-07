package com.flower.server.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ApiRequest<T> {
    List<T> request;
}
