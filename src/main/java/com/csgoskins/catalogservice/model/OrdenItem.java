package com.csgoskins.catalogservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orden_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer cantidad;
    private Integer precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    private Orden orden;
}
