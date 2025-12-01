package com.csgoskins.catalogservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ordenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNombre;
    private String clienteEmail;
    private String direccionEnvio;

    private Integer total;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenItem> items;
}
