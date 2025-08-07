package com.flower.server.dtos;

import lombok.Data;

@Data
public class ApiSingleRequest<T> {
    T request;
}
