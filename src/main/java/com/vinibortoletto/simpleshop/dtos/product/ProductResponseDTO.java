package com.vinibortoletto.simpleshop.dtos.product;

import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.models.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDTO(
        String id,
        String name,
        BigDecimal price,
        Integer stock,
        String image,
        String description,
        List<Category> categories
) {
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getImage(),
                product.getDescription(),
                product.getCategories()
        );
    }

    public static List<ProductResponseDTO> convert(List<Product> productList) {
        return productList.stream().map(ProductResponseDTO::new).toList();
    }
}
