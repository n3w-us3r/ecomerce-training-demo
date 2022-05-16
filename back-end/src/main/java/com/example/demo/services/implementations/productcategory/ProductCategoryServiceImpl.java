package com.example.demo.services.implementations.productcategory;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.services.interfaces.productcategory.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private CategoryCrudService categoryCrudService;

    @Autowired
    public ProductCategoryServiceImpl(CategoryCrudService categoryCrudService) {
        this.categoryCrudService = categoryCrudService;
    }

    @Override
    public void updateProductCategories(ProductEntity productEntity, List<Long> categoryIds) {
        List<CategoryEntity> categoryEntities = categoryCrudService.findByIds(categoryIds);

        productEntity.getCategories().clear();
        productEntity.setCategories(categoryEntities.stream().collect(Collectors.toSet()));
    }
}
