package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "socio", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private Long idSocio;

    @Column(name = "codigo_socio", nullable = false, length = 30)
    private String codigoSocio;

    @Column(nullable = false, length = 15)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 20)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cooperativa", nullable = false)
    private Cooperativa cooperativa;

    @PrePersist
    public void prePersist() {
        this.estado = true;
        this.fechaIngreso = LocalDate.now();
    }
}
