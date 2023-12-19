package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.category.CategoryRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public Category save(CategoryRequestDTO dto) {
        Optional<Category> category = categoryRepository.findByName(dto.name());

        if (category.isPresent()) {
            throw new ConflictException("Category already exists");
        }

        Category newCategory = new Category();
        BeanUtils.copyProperties(dto, newCategory);
        return categoryRepository.save(newCategory);
    }

    public void delete(String categoryId) {
        findById(categoryId);

        try {
            categoryRepository.deleteById(categoryId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
