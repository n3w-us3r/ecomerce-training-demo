package com.example.demo.services.implementations.category;

import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CategoryCrudServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryEntity> findByIds(List<Long> ids) {
        int countExist = 0;
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(ids);

        if (categoryEntities.size() != ids.size()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntities;
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryResponseDto> result = null;

        result = categoryEntities.stream().map(categoryEntity -> {
            return modelMapper.map(categoryEntity, CategoryResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = new CategoryEntity();

        modelMapper.map(requestDto, categoryEntity);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.map(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(UpdateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = findById(requestDto.getCategoryId());

        modelMapper.map(requestDto, categoryEntity);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.map(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        if (!categoryEntity.isPresent()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntity.get();
    }


}
