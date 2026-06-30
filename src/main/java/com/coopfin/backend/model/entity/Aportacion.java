package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aportacion", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aportacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aportacion")
    private Long idAportacion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 20)
    private String periodo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 255)
    private String observacion;

    @Column(nullable = false, length = 50)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
        this.estado = "REGISTRADO";
    }
}