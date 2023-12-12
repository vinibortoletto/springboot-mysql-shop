package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Category;

import java.util.List;

public record CategoryResponseDTO(
        String id,
        String name
) {
    public CategoryResponseDTO(Category category) {
        this(
                category.getId(),
                category.getName()
        );
    }

    public static List<CategoryResponseDTO> convert(List<Category> categoryList) {
        return categoryList.stream().map(CategoryResponseDTO::new).toList();
    }
}
