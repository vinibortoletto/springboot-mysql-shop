package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.CategoryResponseDTO;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "categories")
@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Return all categories")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponseDTO> response = CategoryResponseDTO.convert(categoryList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Return category by id")
    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable String categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryResponseDTO response = new CategoryResponseDTO(category);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
