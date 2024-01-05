package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.category.CategoryRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.CategoryFaker;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private final CategoryFaker categoryFaker = new CategoryFaker();

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all categories")
    void findAllCase1() {
        List<Category> categoryList = List.of(
            categoryFaker.createFakeCategory(),
            categoryFaker.createFakeCategory(),
            categoryFaker.createFakeCategory()
        );

        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> foundCategoryList = categoryService.findAll();

        assertEquals(categoryList, foundCategoryList);
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
        Category category = categoryFaker.createFakeCategory();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findById(category.getId());

        assertEquals(category, foundCategory);
        verify(categoryRepository, times(1)).findById(category.getId());
    }

    @Test
    @DisplayName("save - should throw exception if category already exists")
    void saveCase1() {
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        when(categoryRepository.findByName(dto.name())).thenReturn(Optional.of(category));

        assertThrows(ConflictException.class, () -> categoryService.save(dto));
        verify(categoryRepository, times(1)).findByName(dto.name());
        verify(categoryRepository, never()).save(category);
    }

    @Test
    @DisplayName("save - should save a category")
    void saveCase2() {
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();
        Category newCategory = new Category();
        BeanUtils.copyProperties(dto, newCategory);

        when(categoryRepository.findByName(dto.name())).thenReturn(Optional.empty());
        when(categoryRepository.save(newCategory)).thenReturn(newCategory);

        Category savedCategory = categoryService.save(dto);

        verify(categoryRepository, times(1)).findByName(dto.name());
        verify(categoryRepository, times(1)).save(newCategory);
        assertEquals(newCategory, savedCategory);
    }

    @Test
    @DisplayName("update - should throw exception if trying to duplicate category")
    void updateCase1() {
        String id = String.valueOf(UUID.randomUUID());
        Category duplicateCategory = categoryFaker.createFakeCategory();
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();

        Category newCategory = new Category();
        BeanUtils.copyProperties(dto, newCategory);

        when(categoryRepository.findByName(dto.name())).thenReturn(Optional.of(duplicateCategory));

        assertThrows(ConflictException.class, () -> categoryService.update(dto, id));
        verify(categoryRepository, times(1)).findByName(dto.name());
        verify(categoryRepository, never()).save(newCategory);
    }

    @Test
    @DisplayName("update - should update category")
    void updateCase2() {
        String id = String.valueOf(UUID.randomUUID());
        CategoryRequestDTO dto = categoryFaker.createFakeCategoryRequestDTO();

        Category newCategory = new Category();
        BeanUtils.copyProperties(dto, newCategory);

        when(categoryRepository.findByName(dto.name())).thenReturn(Optional.empty());
        when(categoryRepository.findById(id)).thenReturn(Optional.of(newCategory));
        when(categoryRepository.save(newCategory)).thenReturn(newCategory);

        Category savedCategory = categoryService.update(dto, id);

        verify(categoryRepository, times(1)).findByName(dto.name());
        verify(categoryRepository, times(1)).save(newCategory);
        assertEquals(newCategory, savedCategory);
    }

    @Test
    @DisplayName("delete - should throw exception if category was not found")
    void deleteCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryService.delete(id));
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("delete - should delete a category")
    void deleteCase2() {
        String id = String.valueOf(UUID.randomUUID());
        Category category = categoryFaker.createFakeCategory();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(id);

        categoryService.delete(id);

        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }
}