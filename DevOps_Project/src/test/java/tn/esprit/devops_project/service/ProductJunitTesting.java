package tn.esprit.devops_project.service;
import org.aspectj.lang.annotation.Before;
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

public class ProductJunitTesting {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Before("running")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
    }

    @Test
    public void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(productId);

        assertEquals(product, retrievedProduct);
    }
}
