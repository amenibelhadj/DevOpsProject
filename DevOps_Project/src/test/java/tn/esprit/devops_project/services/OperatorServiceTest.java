package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorServiceTest {
    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Créez des données factices pour les opérateurs
        List<Operator> operators = new ArrayList<>();
        Operator operator1 = new Operator();
        Operator operator2 = new Operator();
        operators.add(operator1);
        operators.add(operator2);

        // Définissez le comportement du mock de repository
        when(operatorRepository.findAll()).thenReturn(operators);

        // Appelez la méthode du service
        List<Operator> result = operatorService.retrieveAllOperators();

        // Vérifiez si le résultat est correct
        assertEquals(2, result.size());
    }

    @Test
    public void testAddOperator() {
        // Créez un opérateur factice
        Operator operator = new Operator();

        // Définissez le comportement du mock de repository
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Appelez la méthode du service
        Operator result = operatorService.addOperator(operator);

        // Vérifiez si le résultat est correct
        assertEquals(operator, result);
    }

    @Test
    public void testDeleteOperator() {
        Long operatorId = 1L;

        // Appelez la méthode du service
        operatorService.deleteOperator(operatorId);

        // Vérifiez si la méthode de suppression a été appelée avec l'ID correct
        verify(operatorRepository).deleteById(operatorId);
    }

    @Test
    public void testRetrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);

        // Définissez le comportement du mock de repository
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        // Appelez la méthode du service
        Operator result = operatorService.retrieveOperator(operatorId);

        // Vérifiez si le résultat est correct
        assertEquals(operator, result);
    }

    @Test
    public void testRetrieveOperatorNotFound() {
        Long operatorId = 1L;

        // Définissez le comportement du mock de repository pour simuler un opérateur introuvable
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        // Appelez la méthode du service et vérifiez si elle lance une exception
        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorId));
    }

}
