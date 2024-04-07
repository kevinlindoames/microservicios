package com.microservicios.dominio;

import com.microservicios.dominio.controller.DomainController;
import com.microservicios.dominio.model.DomainEntity;
import com.microservicios.dominio.repository.EntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DomainApplicationTests {

    @Test
    public void testSaveData_Success() {
        // Mock EntityRepository
        EntityRepository entityRepository = mock(EntityRepository.class);

        // Create test data
        DomainEntity testData = new DomainEntity();
        testData.setId(1L);
        testData.setNombre("TestName");

        // Set up mock behavior for entityRepository.save()
        when(entityRepository.save(any(DomainEntity.class))).thenReturn(testData);

        // Create DomainController instance with mocked repository
        DomainController domainController = new DomainController();
        domainController.setEntityRepository(entityRepository);

        // Call the method to test
        ResponseEntity<String> response = domainController.saveData(testData);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datos guardados correctamente.", response.getBody());
    }

}
