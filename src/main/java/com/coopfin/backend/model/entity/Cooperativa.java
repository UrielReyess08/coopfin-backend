package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cooperativa", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cooperativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cooperativa")
    private Long idCooperativa;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(length = 255)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 120)
    private String email;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Column(name = "color_principal", length = 20)
    private String colorPrincipal;

    @Column(name = "color_secundario", length = 20)
    private String colorSecundario;

    @Column(nullable = false)
    private Boolean estado = true;
}
