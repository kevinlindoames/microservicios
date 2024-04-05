package com.microservicios.orquestador.controller;
import com.microservicios.orquestador.model.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestratorController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/process")
    public ResponseEntity<String> processRequest(@RequestBody RequestData requestData) {
        try {
            // Validar la solicitud
            if (requestData.isValid()) {
                // Realizar la solicitud al microservicio del Dominio
                ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/save", requestData, String.class);
                // Retornar la respuesta recibida del microservicio del Dominio
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            } else {
                return ResponseEntity.badRequest().body("Solicitud no válida.");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}
