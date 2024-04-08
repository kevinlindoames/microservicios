package com.microservicios.orquestador.controller;
import com.microservicios.orquestador.model.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestratorController {
    private final RestTemplate restTemplate;

    // Constructor que acepta RestTemplate como parámetro
    @Autowired
    public OrchestratorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processRequest(@RequestBody RequestData requestData) {
        try {
            // Realizar la solicitud al microservicio del Dominio
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/save", requestData, String.class);

            // Verificar si la respuesta es exitosa (código 200)
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
            } else if (response.getStatusCode().is4xxClientError()) {
                // Manejar error del cliente (código 400)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud al servicio del dominio.");
            } else if (response.getStatusCode().is5xxServerError()) {
                // Manejar error del servidor (código 500)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor del dominio.");
            } else {
                // Manejar otros códigos de respuesta
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Respuesta inesperada del servidor del dominio.");
            }
        } catch (ResourceAccessException e) {
            // Manejar error de conexión al servicio del dominio
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al conectar con el servicio del dominio.");
        } catch (Exception e) {
            // Manejar otras excepciones inesperadas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}