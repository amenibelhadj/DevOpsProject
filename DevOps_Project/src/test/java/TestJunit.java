import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@ExtendWith(MockitoExtension.class)
public class TestJunit {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
        Stock stock = new Stock(); // Créez une instance de Stock selon vos besoins
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        Stock savedStock = stockService.addStock(stock);

        assertEquals(stock, savedStock);
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L; // Remplacez par l'ID de stock approprié
        Stock stock = new Stock(); // Créez une instance de Stock selon vos besoins
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertEquals(stock, retrievedStock);
    }

    @Test
    void testRetrieveAllStock() {
        Stock stock1 = new Stock(); // Créez une instance de Stock selon vos besoins
        Stock stock2 = new Stock(); // Créez une autre instance de Stock
        List<Stock> stockList = Arrays.asList(stock1, stock2);
        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        assertEquals(stockList, retrievedStockList);
    }

   /* @Test
    void testRetrieveStockNotFound() {
        Long stockId = 1L; // Remplacez par un ID qui n'existe pas
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(stockId));
    }*/

}
