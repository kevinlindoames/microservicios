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

    // Este método maneja las solicitudes POST a "/save"
    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody DomainEntity domainEntity) {

        try {
            // Guarda el objeto DomainEntity recibido en el repositorio
            DomainEntity savedEntity = entityRepository.save(domainEntity);
            // Verifica si la entidad se guardó exitosamente
            if (savedEntity != null && savedEntity.getId() != null) {
                // Devuelve una respuesta de éxito si la entidad se guardó
                return ResponseEntity.ok("Datos guardados correctamente.");
            } else {
                // Devuelve una respuesta de error interno del servidor si la entidad no se guardó correctamente
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar los datos.");
            }
        } catch (Exception e) {
            // Si ocurre una excepción durante el proceso, registra el error y devuelve una respuesta de error interno del servidor
            e.printStackTrace(); // Esta línea registra la traza de la excepción, lo cual ayuda en la depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar los datos.");
        }
    }
}
