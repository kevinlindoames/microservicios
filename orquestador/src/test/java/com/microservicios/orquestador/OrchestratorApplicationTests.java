package com.microservicios.orquestador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.microservicios.orquestador.controller.OrchestratorController;
import com.microservicios.orquestador.model.RequestData;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class OrchestratorApplicationTests {
    @Test
    public void testProcessRequest_Success() {
        // Mock RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);

        // Set up mock behavior for restTemplate.postForEntity()
        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(
                new ResponseEntity<>("Data saved successfully", HttpStatus.OK));

        // Create RequestData instance
        RequestData requestData = new RequestData();

        // Create OrchestratorController instance with mocked RestTemplate
        OrchestratorController orchestratorController = new OrchestratorController();
        orchestratorController.setRestTemplate(restTemplate);

        // Call the method to test
        ResponseEntity<String> response = orchestratorController.processRequest(requestData);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data saved successfully", response.getBody());
    }

}
