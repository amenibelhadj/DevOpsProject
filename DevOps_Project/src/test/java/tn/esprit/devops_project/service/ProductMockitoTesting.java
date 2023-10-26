package tn.esprit.devops_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductMockitoTesting {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, stockRepository);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        Stock stock = new Stock();
        Long stockId = 1L;

        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product, stockId);

        assertEquals(stock, savedProduct.getStock());

        // Log success message
        System.out.println("testAddProduct() test passed: Product saved successfully.");
    }

    @Test
    public void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(productId);

        assertEquals(product, retrievedProduct);

        // Log success message
        System.out.println("testRetrieveProduct() test passed: Product retrieved successfully.");
    }
}
