package com.microservicios.dominio.repository;

import com.microservicios.dominio.model.DomainEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<DomainEntity, Long> {

}
