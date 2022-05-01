package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateProductRequestDto {
    @NotNull
    private Long productId;

    private Long genderId;

    private String name;

    private String description;

    private double price;

    private int year;

    private Long sportId;

    private Long categoryId;
}
