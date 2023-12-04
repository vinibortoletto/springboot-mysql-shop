package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.ProductDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<Product> findAllByCategoryId(String id) {
        categoryService.findById(id);
        return repository.findAllByCategories_Id(id);
    }

    public Product save(ProductDto dto) {
        Optional<Product> product = repository.findByName(dto.name());

        if (product.isPresent()) {
            throw new ConflictException("Product already exists in database");
        }

        Product newProduct = new Product();
        BeanUtils.copyProperties(dto, newProduct);

        for (String categoryId : dto.categories()) {
            Category category = categoryService.findById(categoryId);
            newProduct.getCategories().add(category);
        }

        return repository.save(newProduct);
    }

    public Product update(ProductDto dto, String id) {
        Product product = findById(id);
        BeanUtils.copyProperties(dto, product);
        return repository.save(product);
    }

    public void delete(String id) {
        findById(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
