package com.example.demo.services.implementations.producttechnology;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.services.interfaces.producttechnology.ProductTechnologyService;
import com.example.demo.services.interfaces.technology.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTechnologyServiceImpl implements ProductTechnologyService {
    private TechnologyService technologyService;

    @Autowired
    public ProductTechnologyServiceImpl(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @Override
    public void updateProductTechnologies(ProductEntity productEntity, List<Long> technologyIds) {
        List<TechnologyEntity> technologyEntities = technologyService.findByIds(technologyIds);

        productEntity.getTechnologies().clear();
        productEntity.setTechnologies(technologyEntities.stream().collect(Collectors.toSet()));
    }
}