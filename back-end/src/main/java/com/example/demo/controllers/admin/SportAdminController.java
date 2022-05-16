package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.services.interfaces.sport.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/sport")
public class SportAdminController {
    @Autowired
    private SportService sportService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateSport(@RequestParam(name = "id") Long sportId,
                                                       @Valid @RequestBody UpdateSportRequestDto requestDto) {
        SportResponseDto updatedSport = sportService.updateSport(sportId, requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(updatedSport).build();

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto> createSport(@Valid @RequestBody CreateSportRequestDto requestDto) {
        SportResponseDto createdSport = sportService.createSport(requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(createdSport).build();

        return ResponseEntity.ok(responseBodyDto);
    }
}