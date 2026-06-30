package com.coopfin.backend.model.entity;

import com.coopfin.backend.model.enums.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long idPrestamo;

    @Column(name = "monto_solicitado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoSolicitado;

    @Column(name = "numero_cuotas", nullable = false)
    private Integer numeroCuotas;

    @Column(name = "tasa_interes_aplicada", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteresAplicada;

    @Column(name = "saldo_capital", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoCapital;

    @Column(name = "saldo_interes", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoInteres;

    @Column(name = "saldo_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoTotal;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "fecha_aprobacion")
    private LocalDate fechaAprobacion;

    @Column(length = 255)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EstadoPrestamo estado;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @PrePersist
    public void prePersist() {
        this.fechaSolicitud = LocalDate.now();
        this.estado = EstadoPrestamo.PENDIENTE;
    }
}
