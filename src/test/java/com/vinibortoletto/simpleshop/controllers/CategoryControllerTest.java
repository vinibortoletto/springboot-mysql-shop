package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.category.CategoryRequestDTO;
import com.vinibortoletto.simpleshop.dtos.category.CategoryResponseDTO;
import com.vinibortoletto.simpleshop.fakers.CategoryFaker;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CategoryControllerTest {
    private final CategoryFaker categoryFaker = new CategoryFaker();
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all categories")
    void findAll() {
        List<Category> categoryList = List.of(categoryFaker.createFakeCategory());
        when(categoryService.findAll()).thenReturn(categoryList);

        ResponseEntity<List<CategoryResponseDTO>> expected = ResponseEntity
            .ok()
            .body(CategoryResponseDTO.convert(categoryList));

        ResponseEntity<List<CategoryResponseDTO>> response = categoryController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return category by id")
    void findById() {
        Category category = categoryFaker.createFakeCategory();
        when(categoryService.findById(category.getId())).thenReturn(category);

        ResponseEntity<CategoryResponseDTO> expected = ResponseEntity
            .ok()
            .body(new CategoryResponseDTO(category));

        ResponseEntity<CategoryResponseDTO> response = categoryController.findById(category.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("save - should create new category")
    void save() {
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        when(categoryService.save(dto)).thenReturn(category);

        ResponseEntity<CategoryResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new CategoryResponseDTO(category));

        ResponseEntity<CategoryResponseDTO> response = categoryController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("update - should update category")
    void update() {
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        when(categoryService.update(dto, category.getId())).thenReturn(category);

        ResponseEntity<CategoryResponseDTO> expected = ResponseEntity
            .ok()
            .body(new CategoryResponseDTO(category));

        ResponseEntity<CategoryResponseDTO> response = categoryController.update(dto, category.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("delete - should delete category by id")
    void delete() {
        ResponseEntity<Void> expected = ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();

        ResponseEntity<Void> response = categoryController.delete("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(expected, response);
    }
}