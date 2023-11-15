import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestJUnitSupplierService {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

   /* @Before
    public void setUp() {
        // Créez des données fictives pour les tests
    }*/

    @Test
    public void testRetrieveAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        // Ajoutez des fournisseurs fictifs à la liste
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.retrieveAllSuppliers();

        assertEquals(suppliers, result);
    }

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();
        // Configurez le comportement de supplierRepository.save()
        when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        assertEquals(supplier, addedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        // Configurez le comportement de supplierRepository.save()
        when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        assertEquals(supplier, updatedSupplier);
    }

   /*@Test
    public void testDeleteSupplier() {
        Long supplierId = 1L;
        // Configurez le comportement de supplierRepository.deleteById()
        Mockito.doNothing().when(supplierRepository).deleteById(supplierId);

        supplierService.deleteSupplier(supplierId);
    }*/

    @Test
    public void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        // Configurez le comportement de supplierRepository.findById()
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(supplier, retrievedSupplier);
    }
}



