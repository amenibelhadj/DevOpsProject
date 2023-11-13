import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class TestMockito {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddStock() {
        Stock stockToAdd = new Stock(); // create a Stock object for testing
        Mockito.when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd); // Mock the save method

        Stock result = stockService.addStock(stockToAdd);

        assertEquals(stockToAdd, result);
    }

    @Test
    public void testRetrieveStock() {
        Long stockId = 1L;
        Stock stockToRetrieve = new Stock();
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stockToRetrieve)); // Mock the findById method

        Stock result = stockService.retrieveStock(stockId);

        assertEquals(stockToRetrieve, result);
    }



    @Test
    public void testRetrieveAllStock() {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        List<Stock> stockList = Arrays.asList(stock1, stock2);
        Mockito.when(stockRepository.findAll()).thenReturn(stockList); // Mock the findAll method

        List<Stock> result = stockService.retrieveAllStock();

        assertEquals(stockList, result);

    }

}
