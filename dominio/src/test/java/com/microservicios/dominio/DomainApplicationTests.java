package com.microservicios.dominio;

import com.microservicios.dominio.controller.DomainController;
import com.microservicios.dominio.model.DomainEntity;
import com.microservicios.dominio.repository.EntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DomainApplicationTests {

    @Autowired
    private DomainController domainController;

    @MockBean
    private EntityRepository entityRepository;

    @Test
    public void testSaveData_Success() {
        System.out.println("Iniciando prueba...");

        // Crea los datos de prueba
        DomainEntity data = new DomainEntity();
        data.setId(5L);
        data.setNombre("TestName");
        System.out.println("Datos de prueba creados: " + data);

        // Configura el comportamiento del mock entityRepository
        when(entityRepository.save(any(DomainEntity.class))).thenReturn(data);
        System.out.println("Configuración del mock entityRepository completada.");

        // Llama al método saveData de DomainController con los datos de prueba
        ResponseEntity<String> response = domainController.saveData(data);
        System.out.println("Método saveData llamado en DomainController.");

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Datos guardados correctamente.", response.getBody());

        System.out.println("Prueba completada con éxito.");
    }
}