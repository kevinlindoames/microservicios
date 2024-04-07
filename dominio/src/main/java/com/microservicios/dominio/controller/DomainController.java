package com.microservicios.dominio.controller;
import com.microservicios.dominio.model.DomainEntity;
import com.microservicios.dominio.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DomainController {
    @Autowired
    private EntityRepository entityRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody DomainEntity data) {
        try {
            DomainEntity savedEntity = entityRepository.save(data);
            if (savedEntity != null && savedEntity.getId() != null) {
                return ResponseEntity.ok("Datos guardados correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar los datos.");
            }
        } catch (Exception e) {
            // Loguea el error para depuración
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar los datos.");
        }
    }
}
