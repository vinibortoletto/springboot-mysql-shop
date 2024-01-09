package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.product.ProductRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.ProductFaker;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private final ProductFaker productFaker = new ProductFaker();

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all products")
    void findAllCase1() {
        List<Product> productList = List.of(
            productFaker.createFakeProduct(),
            productFaker.createFakeProduct(),
            productFaker.createFakeProduct()
        );

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> foundProductList = productService.findAll();

        assertEquals(productList, foundProductList);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById - should find one product by id")
    void findByIdCase1() {
        Product product = productFaker.createFakeProduct();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product foundProduct = productService.findById(product.getId());
        assertEquals(product, foundProduct);
    }

    @Test
    @DisplayName("findById - should throw exception if product is not found")
    void findByIdCase2() {
        Product product = productFaker.createFakeProduct();
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productService.findById(product.getId()));
    }

    @Test
    @DisplayName("findAllByCategoryId - should find all products by category id")
    void findAllByCategoryIdCase1() {
        List<Product> productList = List.of(
            productFaker.createFakeProduct(),
            productFaker.createFakeProduct(),
            productFaker.createFakeProduct()
        );

        Category category = productList.get(0).getCategories().get(0);

        when(categoryService.findById(category.getId())).thenReturn(category);
        when(productRepository.findAllByCategories_Id(category.getId())).thenReturn(productList);

        List<Product> foundProductList = productService.findAllByCategoryId(category.getId());
        assertEquals(productList, foundProductList);
    }


    @Test
    @DisplayName("save - should throw exception if product already exists")
    void saveCase1() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        when(productRepository.findByName(dto.name())).thenReturn(Optional.of(product));
        assertThrows(ConflictException.class, () -> productService.save(dto));
        verify(productRepository, times(1)).findByName(dto.name());
        verify(productRepository, never()).save(product);
    }

    @Test
    @DisplayName("save - should save a product")
    void saveCase2() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        Product newProduct = new Product();
        BeanUtils.copyProperties(dto, newProduct);

        when(productRepository.findByName(dto.name())).thenReturn(Optional.empty());

        for (String categoryId : dto.categories()) {
            when(categoryService.findById(categoryId)).thenReturn(new Category());
            newProduct.getCategories().add(new Category());
        }

        when(productRepository.save(newProduct)).thenReturn(newProduct);

        Product savedProduct = productService.save(dto);
        verify(productRepository, times(1)).findByName(dto.name());
        assertEquals(newProduct, savedProduct);
    }

    @Test
    @DisplayName("update - should throw exception if product is not found")
    void updateCase1() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        String id = String.valueOf(UUID.randomUUID());
        Product newProduct = new Product();
        BeanUtils.copyProperties(dto, newProduct);

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productService.update(dto, id));
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, never()).save(newProduct);
    }

    @Test
    @DisplayName("update - should update a product")
    void updateCase2() {
        ProductRequestDTO dto = productFaker.createFakeProductRequestDTO();
        String id = String.valueOf(UUID.randomUUID());
        Product newProduct = new Product();
        BeanUtils.copyProperties(dto, newProduct);

        when(productRepository.findById(id)).thenReturn(Optional.of(newProduct));
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        Product updatedProduct = productService.update(dto, id);

        assertEquals(newProduct, updatedProduct);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    @DisplayName("delete - should throw exception if product is not found")
    void deleteCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.delete(id));
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("delete - should delete a product")
    void deleteCase2() {
        String id = String.valueOf(UUID.randomUUID());
        Product product = productFaker.createFakeProduct();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(id);

        productService.delete(id);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).deleteById(id);
    }
}