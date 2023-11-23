package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.models.ProductModel;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductModel> findAll() {
        return repository.findAll();
    }
}
