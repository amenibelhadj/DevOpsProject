package tn.esprit.devops_project.service;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ProductMockitoTesting {


    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public void testAddProduct() {
        Product product = new Product();
        Stock stock = new Stock();
        Long stockId = 1L;

        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        productService.addProduct(product, stockId);

        verify(stockRepository, Mockito.times(1)).findById(stockId);
        verify(productRepository, Mockito.times(1)).save(product);
    }

    public void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        productService.retrieveProduct(productId);

        verify(productRepository, Mockito.times(1)).findById(productId);
    }

    public void testRetrieveAllProduct() {
        productService.retreiveAllProduct();

        verify(productRepository, Mockito.times(1)).findAll();
    }

    public void testDeleteProduct() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, Mockito.times(1)).deleteById(productId);
    }

    public void testRetrieveProductStock() {
        Long stockId = 1L;

        productService.retreiveProductStock(stockId);

        verify(productRepository, Mockito.times(1)).findByStockIdStock(stockId);
    }
}
