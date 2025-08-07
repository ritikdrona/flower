package com.flower.server.services.generic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flower.server.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public class CrudService<E, D, Id> {

    final private JpaRepository<E, Id> repository;

    final private Converter converter;

    public List<D> getResources() {
        List<E> resources = repository.findAll();
        return converter.convertCollection(resources, new TypeReference<>() {
        });
    }

}
