package tn.esprit.devops_project.service;

import org.junit.Before;
        import org.junit.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;
        import tn.esprit.devops_project.entities.Supplier;
        import tn.esprit.devops_project.repositories.SupplierRepository;
        import tn.esprit.devops_project.services.SupplierServiceImpl;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

public class SupplierServiceTestMockito  {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Créer une liste de fournisseurs fictive pour les tests
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(1L, "Fournisseur 1"));
        suppliers.add(new Supplier(2L, "Fournisseur 2"));

        // Configurer le comportement du mock
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Appeler la méthode du service à tester
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Vérifier que le résultat correspond à ce qui était attendu
        assertEquals(suppliers, result);
    }

    @Test
    public void testAddSupplier() {
        // Créer un fournisseur fictif pour les tests
        Supplier supplier = new Supplier(1L, "Fournisseur Test");

        // Configurer le comportement du mock
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Appeler la méthode du service à tester
        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Vérifier que le fournisseur ajouté correspond à ce qui était attendu
        assertEquals(supplier, addedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        // Créer un fournisseur fictif pour les tests
        Supplier supplier = new Supplier(1L, "Fournisseur Test");

        // Configurer le comportement du mock
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Appeler la méthode du service à tester
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        // Vérifier que le fournisseur mis à jour correspond à ce qui était attendu
        assertEquals(supplier, updatedSupplier);
    }

    @Test
    public void testDeleteSupplier() {
        // ID du fournisseur à supprimer
        Long supplierId = 1L;

        // Appeler la méthode du service à tester
        supplierService.deleteSupplier(supplierId);

        // Vérifier que la méthode de suppression du repository a été appelée avec le bon ID
        verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    public void testRetrieveSupplier() {
        // Créer un fournisseur fictif pour les tests
        Supplier supplier = new Supplier(1L, "Fournisseur Test");

        // Configurer le comportement du mock
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Appeler la méthode du service à tester
        Supplier retrievedSupplier = supplierService.retrieveSupplier(1L);

        // Vérifier que le fournisseur récupéré correspond à ce qui était attendu
        assertEquals(supplier, retrievedSupplier);
    }
}


