package com.csgoskins.catalogservice.repository;

import com.csgoskins.catalogservice.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
