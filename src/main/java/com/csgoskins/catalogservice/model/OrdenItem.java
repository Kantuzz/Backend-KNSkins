package com.csgoskins.catalogservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;


@Data
@Builder
@Entity
@Table(name = "orden_items")
public class OrdenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Integer cantidad;
    private Integer precioUnitario;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}
