package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.category.CategoryRequestDTO;
import com.vinibortoletto.simpleshop.dtos.category.CategoryResponseDTO;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
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

    @Operation(summary = "Create new category")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody @Valid CategoryRequestDTO dto) {
        Category category = categoryService.save(dto);
        CategoryResponseDTO response = new CategoryResponseDTO(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update category")
    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> update(@RequestBody @Valid CategoryRequestDTO dto, @PathVariable String categoryId) {
        Category category = categoryService.update(dto, categoryId);
        CategoryResponseDTO response = new CategoryResponseDTO(category);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete category by id")
    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
