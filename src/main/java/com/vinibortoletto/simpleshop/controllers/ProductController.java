package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.models.ProductModel;
import com.vinibortoletto.simpleshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
