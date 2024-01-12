package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.product.ProductRequestDTO;
import com.vinibortoletto.simpleshop.dtos.product.ProductResponseDTO;
import com.vinibortoletto.simpleshop.fakers.CategoryFaker;
import com.vinibortoletto.simpleshop.fakers.ProductFaker;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductControllerTest {
    private final ProductFaker productFaker = new ProductFaker();
    private final CategoryFaker categoryFaker = new CategoryFaker();

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all products")
    void findAll() {
        List<Product> productList = List.of(productFaker.createFakeProduct());
        when(productService.findAll()).thenReturn(productList);

        ResponseEntity<List<ProductResponseDTO>> expected = ResponseEntity
            .ok()
            .body(ProductResponseDTO.convert(productList));

        ResponseEntity<List<ProductResponseDTO>> response = productController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findAllByCategoryId - should return all products based on category id")
    void findAllByCategoryId() {
        Product product = productFaker.createFakeProduct();
        String categoryId = product.getCategories().get(0).getId();

        List<Product> productList = List.of(product);
        when(productService.findAllByCategoryId(categoryId)).thenReturn(productList);

        ResponseEntity<List<ProductResponseDTO>> expected = ResponseEntity
            .ok()
            .body(ProductResponseDTO.convert(productList));

        ResponseEntity<List<ProductResponseDTO>> response = productController.findAllByCategoryId(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return product by id")
    void findById() {
        Product product = productFaker.createFakeProduct();
        when(productService.findById(product.getId())).thenReturn(product);

        ResponseEntity<ProductResponseDTO> expected = ResponseEntity
            .ok()
            .body(new ProductResponseDTO(product));

        ResponseEntity<ProductResponseDTO> response = productController.findById(product.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("save - should create new product")
    void save() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        Product product = productFaker.createFakeProduct();
        BeanUtils.copyProperties(dto, product);

        when(productService.save(dto)).thenReturn(product);

        ResponseEntity<ProductResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ProductResponseDTO(product));

        ResponseEntity<ProductResponseDTO> response = productController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("update - should update product by id")
    void update() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        Product product = productFaker.createFakeProduct();
        BeanUtils.copyProperties(dto, product);

        when(productService.update(dto, product.getId())).thenReturn(product);

        ResponseEntity<ProductResponseDTO> expected = ResponseEntity
            .ok()
            .body(new ProductResponseDTO(product));

        ResponseEntity<ProductResponseDTO> response = productController.update(dto, product.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("delete - should delete product by id")
    void delete() {
        ResponseEntity<Void> expected = ResponseEntity.noContent().build();

        ResponseEntity<Void> response = productController.delete(productFaker.createFakeProduct().getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(expected, response);
    }
}