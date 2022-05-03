package com.example.demo.services.interfaces.sport;

import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.SportEntity;

import java.util.List;
import java.util.Optional;

public interface SportService {
    Optional<SportEntity> findById(Long id);

    List<SportResponseDto> findAll();
}
