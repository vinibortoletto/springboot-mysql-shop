package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.fakers.CategoryFaker;
import com.vinibortoletto.simpleshop.fakers.ProductFaker;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    private final ProductFaker productFaker = new ProductFaker();
    private final CategoryFaker categoryFaker = new CategoryFaker();

    @Mock
    private ProductRepository repository;

    @Autowired
    @InjectMocks
    private ProductService service;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }


//    @Test
//    @DisplayName("findAll - should return empty array")
//    void findAllCase1() {
//        List<_Product> expected = new ArrayList<>();
//        when(repository.findAll()).thenReturn(expected);
//
//        List<_Product> actual = service.findAll();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("findAll - should find all products")
//    void findAllCase2() {
//        List<_Product> expected = List.of(
//                productFaker.createFakeProduct(),
//                productFaker.createFakeProduct(),
//                productFaker.createFakeProduct()
//        );
//        when(repository.findAll()).thenReturn(expected);
//
//        List<_Product> actual = service.findAll();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("findAllByCategoryId - should find all products by category id")
//    void findAllByCategoryIdCase1() {
//        List<_Product> products = List.of(
//                productFaker.createFakeProduct(),
//                productFaker.createFakeProduct(),
//                productFaker.createFakeProduct()
//        );
//
//        _Category category = products.get(0).getCategories().get(0);
//
//        when(categoryService.findById(category.getId())).thenReturn(category);
//        when(repository.findAllByCategories_Id(category.getId())).thenReturn(products);
//
//        List<_Product> foundProducts = service.findAllByCategoryId(category.getId());
//        assertEquals(products, foundProducts);
//    }
//
//    @Test
//    @DisplayName("findById - should find one product by id")
//    void findByIdCase1() {
//        _Product expected = productFaker.createFakeProduct();
//        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));
//        _Product actual = service.findById(expected.getId());
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("findById - should throw exception if product is not found")
//    void findByIdCase2() {
//        _Product expected = productFaker.createFakeProduct();
//        when(repository.findById(expected.getId())).thenReturn(Optional.empty());
//        assertThrows(NotFoundException.class, () -> service.findById(expected.getId()));
//    }
//
//    @Test
//    @DisplayName("save - should throw exception if product already exists")
//    void saveCase1() {
//        String expectedMessage = "Product already exists in database";
//        ProductDto dto = productFaker.createFakeProductDto();
//        _Product product = new _Product();
//        BeanUtils.copyProperties(dto, product);
//
//        when(repository.findByName(dto.name())).thenReturn(Optional.of(product));
//        assertThrows(ConflictException.class, () -> service.save(dto));
//        verify(repository, times(1)).findByName(dto.name());
//        verify(repository, never()).save(product);
//    }
//
//    @Test
//    @DisplayName("save - should save a product")
//    void saveCase2() {
//        ProductDto dto = productFaker.createFakeProductDto();
//        _Product expected = new _Product();
//        BeanUtils.copyProperties(dto, expected);
//
//        when(repository.findByName(dto.name())).thenReturn(Optional.empty());
//
//        for (String categoryId : dto.categories()) {
//            when(categoryService.findById(categoryId)).thenReturn(new _Category());
//            expected.getCategories().add(new _Category());
//        }
//
//        when(repository.save(expected)).thenReturn(expected);
//
//        _Product actual = service.save(dto);
//        verify(repository, times(1)).findByName(dto.name());
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("update - should throw exception if product is not found")
//    void updateCase1() {
//        ProductDto dto = productFaker.createFakeProductDto();
//        String id = String.valueOf(UUID.randomUUID());
//        _Product newProduct = new _Product();
//        BeanUtils.copyProperties(dto, newProduct);
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//        assertThrows(NotFoundException.class, () -> service.update(dto, id));
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).save(newProduct);
//    }
//
//    @Test
//    @DisplayName("update - should update a product")
//    void updateCase2() {
//        ProductDto dto = productFaker.createFakeProductDto();
//        String id = String.valueOf(UUID.randomUUID());
//        _Product newProduct = new _Product();
//        BeanUtils.copyProperties(dto, newProduct);
//
//        when(repository.findById(id)).thenReturn(Optional.of(newProduct));
//        when(repository.save(newProduct)).thenReturn(newProduct);
//
//        _Product updatedProduct = service.update(dto, id);
//
//        assertEquals(newProduct, updatedProduct);
//        verify(repository, times(1)).findById(id);
//        verify(repository, times(1)).save(newProduct);
//    }
//
//    @Test
//    @DisplayName("delete - should throw exception if product is not found")
//    void deleteCase1() {
//        String id = String.valueOf(UUID.randomUUID());
//        when(repository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> service.delete(id));
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).deleteById(id);
//    }
//
//    @Test
//    @DisplayName("delete - should delete a product")
//    void deleteCase2() {
//        String id = String.valueOf(UUID.randomUUID());
//        _Product product = productFaker.createFakeProduct();
//
//        when(repository.findById(id)).thenReturn(Optional.of(product));
//        doNothing().when(repository).deleteById(id);
//
//        service.delete(id);
//
//        verify(repository, times(1)).findById(id);
//        verify(repository, times(1)).deleteById(id);
//    }
}