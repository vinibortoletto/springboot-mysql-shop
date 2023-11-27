package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.ProductDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.ProductModel;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductModel> findAll() {
        return repository.findAll();
    }

    public ProductModel findById(String id) {
        Optional<ProductModel> product = repository.findById(id);
        return product.orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public ProductModel save(ProductDto dto) {
        Optional<ProductModel> product = repository.findByName(dto.name());

        if (product.isPresent()) {
                throw new ConflictException("Product already exists in database");
        }

        ProductModel newProduct = new ProductModel();
        BeanUtils.copyProperties(dto, newProduct);
        return repository.save(newProduct);
    }

    public ProductModel update(ProductDto dto, String id) {
        ProductModel product = findById(id);
        BeanUtils.copyProperties(dto, product);
        return repository.save(product);
    }
}
