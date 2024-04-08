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
        System.out.println("Iniciando prueba...");

        // Mock de RestTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);

        // Configurar el comportamiento del mock para restTemplate.postForEntity()
        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(
                new ResponseEntity<>("Datos guardados exitosamente", HttpStatus.OK));

        // Crear instancia de RequestData
        RequestData requestData = new RequestData();

        // Crear instancia de OrchestratorController con RestTemplate mockeado
        OrchestratorController orchestratorController = new OrchestratorController(restTemplate);

        // Llamar al método a probar
        ResponseEntity<String> response = orchestratorController.processRequest(requestData);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datos guardados exitosamente", response.getBody());

        System.out.println("Prueba completada.");
    }
}
