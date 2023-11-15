package tn.esprit.devops_project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.*;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class InvoiceJUnitTEST {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private  InvoiceRepository invoiceRepository;
    @Mock
    private OperatorRepository operatorRepository;


    @InjectMocks
    InvoiceServiceImpl invoiceService;
    @BeforeEach
    public void setUp() {

    }

    Invoice invoice = new Invoice();

    List<Invoice> invoiceList =new ArrayList<Invoice>(){
        {
            // Création d'un objet Invoice avec des exemples de valeurs
            Invoice invoice1 = new Invoice();
            invoice1.setIdInvoice(1L);
            invoice1.setAmountDiscount(10.5f);
            invoice1.setAmountInvoice(100.0f);
            invoice1.setDateCreationInvoice(new Date());
            invoice1.setDateLastModificationInvoice(new Date());
            invoice1.setArchived(false);

            // Création d'objets InvoiceDetail liés à l'Invoice
            Set<InvoiceDetail> invoiceDetails = new HashSet<>();
            InvoiceDetail detail1 = new InvoiceDetail();
            detail1.setIdInvoiceDetail(1L);
            detail1.setQuantity(5);
            detail1.setPrice(20.0f);

            // Création d'un objet Product lié à l'InvoiceDetail
            Product product1 = new Product();
            product1.setIdProduct(1L);
            product1.setTitle("Product 1");
            product1.setPrice(20.0f);
            product1.setQuantity(50);
            // Vous devrez définir la catégorie et le stock en fonction de vos besoins
            product1.setCategory(ProductCategory.ELECTRONICS);

            // Création d'un objet Stock lié au Product
            Stock stock1 = new Stock();
            stock1.setIdStock(1L);
            stock1.setTitle("Stock 1");

            // Association du Stock au Product
            product1.setStock(stock1);

            // Association du produit à l'InvoiceDetail
            detail1.setProduct(product1);

            // Association de l'Invoice à l'InvoiceDetail
            detail1.setInvoice(invoice1);

            invoiceDetails.add(detail1);

            // Répétez le processus pour les autres InvoiceDetail

            invoice1.setInvoiceDetails(invoiceDetails);

        }
    };

    @Test
    public void testRetrieveInvoice() {
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(invoiceId);

        when(invoiceRepository.findById(invoiceId)).thenReturn(java.util.Optional.of(invoice));

        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        assertEquals(invoice, retrievedInvoice);

        // Log success message
        System.out.println("JUNIT1 test passed: Product retrieved successfully.");
    }
    @Test
    public void testAssignOperatorToInvoice() {
        Long idOperator = 1L;
        Long idInvoice = 1L;

        // Création d'un objet Invoice
        Invoice invoice = createInvoice(idInvoice, 10.5f, 100.0f, false);

        // Création d'un objet Operator
        Operator operator = createOperator(idOperator);

        // Définir le comportement attendu lors de l'appel à operatorRepository.findById(idOperator)
        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));

        // Définir le comportement attendu lors de l'appel à invoiceRepository.findById(idInvoice)
        when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        // Appeler la méthode assignOperatorToInvoice
        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        // Vérifier que l'Invoice a été ajouté à la liste des invoices de l'Operator
        assertTrue(operator.getInvoices().contains(invoice));

        // Vérifier que la méthode save a été appelée sur operatorRepository
        verify(operatorRepository).save(operator);
        System.out.println("JUNIT2 test passed: Product retrieved successfully.");

    }

    // Méthode utilitaire pour créer un objet Invoice
    private Invoice createInvoice(Long id, float amountDiscount, float amountInvoice, boolean archived) {
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(id);
        invoice.setAmountDiscount(amountDiscount);
        invoice.setAmountInvoice(amountInvoice);
        invoice.setDateCreationInvoice(new Date());
        invoice.setDateLastModificationInvoice(new Date());
        invoice.setArchived(archived);

        // Création d'objets InvoiceDetail liés à l'Invoice
        Set<InvoiceDetail> invoiceDetails = new HashSet<>();
        InvoiceDetail detail1 = new InvoiceDetail();
        detail1.setIdInvoiceDetail(1L);
        detail1.setQuantity(5);
        detail1.setPrice(20.0f);

        // Vous devrez définir la catégorie et le stock en fonction de vos besoins
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Product 1");
        product1.setPrice(20.0f);
        product1.setQuantity(50);

        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stock1.setTitle("Stock 1");

        product1.setStock(stock1);
        detail1.setProduct(product1);
        detail1.setInvoice(invoice);
        invoiceDetails.add(detail1);

        invoice.setInvoiceDetails(invoiceDetails);

        return invoice;
    }

    // Méthode utilitaire pour créer un objet Operator
    private Operator createOperator(Long id) {
        Operator operator = new Operator();
        operator.setIdOperateur(id);
        // Ajoutez d'autres propriétés à l'opérateur selon vos besoins
        return operator;
    }
    //
    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Définir les dates de début et de fin pour le test
        Date startDate = new Date();
        Date endDate = new Date();

        // Définir la valeur de retour attendue de la méthode du repository
        float expectedTotalAmount = 1000.0f;

        // Configurer le comportement du mock repository
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate))
                .thenReturn(expectedTotalAmount);

        // Appeler la méthode du service à tester
        float actualTotalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Vérifier que la méthode du repository a été appelée avec les bons paramètres
        // Vous pouvez ajuster cette vérification en fonction de votre logique métier
        // Mockito.verify(invoiceRepository).getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Vérifier que la valeur retournée par la méthode du service correspond à la valeur attendue
        assertEquals(expectedTotalAmount, actualTotalAmount, 0.001f);
        System.out.println("testGetTotalAmountInvoiceBetweenDates() test passed: Product retrieved successfully.");
    }
}


