package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.ProductDto;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "products")
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @Operation(summary = "Returns all products")
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productList = service.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @Operation(summary = "Returns a product based on its id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Creates a new product")
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody @Valid ProductDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @Operation(summary = "Updates a product based on its id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@RequestBody @Valid ProductDto dto, @PathVariable String id) {
        return ResponseEntity.ok().body(service.update(dto, id));
    }

    @Operation(summary = "Deletes a product based on its id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
