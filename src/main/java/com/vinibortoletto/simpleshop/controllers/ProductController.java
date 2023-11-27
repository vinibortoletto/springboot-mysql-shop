package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.ProductDto;
import com.vinibortoletto.simpleshop.models.ProductModel;
import com.vinibortoletto.simpleshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductModel>> findAll() {
        List<ProductModel> productList = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable String id) {
        System.out.println(id);
        ProductModel product = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping()
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductDto dto) {
        ProductModel product = new ProductModel();
        BeanUtils.copyProperties(dto, product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(product));
    }
}
