package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.CategoryFaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private final CategoryFaker categoryFaker = new CategoryFaker();

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    @InjectMocks
    private _CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findById - should throw exception if category is not found")
    void findByIdCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> categoryService.findById(id));
    }

    @Test
    @DisplayName("findById - should find category by id")
    void findByIdCase2() {
        _Category category = categoryFaker.createFakeCategory();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        _Category foundCategory = categoryService.findById(category.getId());
        assertEquals(category, foundCategory);
        verify(categoryRepository, times(1)).findById(category.getId());
    }
}