package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pago_cuota", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoCuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_cuota")
    private Long idPagoCuota;

    @Column(name = "capital_pagado", nullable = false, precision = 12, scale = 2)
    private BigDecimal capitalPagado;

    @Column(name = "interes_pagado", nullable = false, precision = 12, scale = 2)
    private BigDecimal interesPagado;

    @Column(name = "mora_pagada", nullable = false, precision = 12, scale = 2)
    private BigDecimal moraPagada;

    @Column(name = "monto_total_pagado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotalPagado;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(length = 255)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_cuota", nullable = false)
    private CuotaPrestamo cuota;

    @PrePersist
    public void prePersist() {
        this.fechaPago = LocalDate.now();
    }
}