package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.ProductRequestDTO;
import com.vinibortoletto.simpleshop.dtos.ProductResponseDTO;
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
    private ProductService productService;

    @Operation(summary = "Return all products")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<Product> productList = productService.findAll();
        List<ProductResponseDTO> response = ProductResponseDTO.convert(productList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Return all products based on category id")
    @GetMapping(value = "/categories/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> findAllByCategoryId(@PathVariable String categoryId) {
        List<Product> productList = productService.findAllByCategoryId(categoryId);
        List<ProductResponseDTO> response = ProductResponseDTO.convert(productList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Return product by id")
    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable String productId) {
        Product product = productService.findById(productId);
        ProductResponseDTO response = new ProductResponseDTO(product);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Create new product")
    @PostMapping()
    public ResponseEntity<ProductResponseDTO> save(@RequestBody @Valid ProductRequestDTO dto) {
        Product product = productService.save(dto);
        ProductResponseDTO response = new ProductResponseDTO(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update product by id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@RequestBody @Valid ProductRequestDTO dto, @PathVariable String id) {
        Product product = productService.update(dto, id);
        ProductResponseDTO response = new ProductResponseDTO(product);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete product by id")
    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> delete(@PathVariable String productId) {
        productService.delete(productId);

        return ResponseEntity.noContent().build();
    }
}
