package com.coopfin.backend.model.entity;

import com.coopfin.backend.model.enums.EstadoCuota;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cuota_prestamo", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuotaPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota")
    private Long idCuota;

    @Column(name = "numero_cuota", nullable = false)
    private Integer numeroCuota;

    @Column(name = "capital_programado", nullable = false, precision = 12, scale = 2)
    private BigDecimal capitalProgramado;

    @Column(name = "interes_programado", nullable = false, precision = 12, scale = 2)
    private BigDecimal interesProgramado;

    @Column(name = "monto_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoCuota estado;

    @ManyToOne
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamo prestamo;

    @PrePersist
    public void prePersist() {
        this.estado = EstadoCuota.PENDIENTE;
    }
}
