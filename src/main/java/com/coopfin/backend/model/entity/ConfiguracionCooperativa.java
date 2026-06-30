package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "configuracion_cooperativa", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionCooperativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion")
    private Long idConfiguracion;

    @Column(name = "tasa_interes_default", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteresDefault;

    @Column(nullable = false, length = 10)
    private String moneda;

    @Column(name = "monto_maximo_prestamo", precision = 12, scale = 2)
    private BigDecimal montoMaximoPrestamo;

    @Column(name = "numero_maximo_cuotas")
    private Integer numeroMaximoCuotas;

    @Column(name = "dias_gracia")
    private Integer diasGracia;

    @Column(nullable = false)
    private Boolean estado;

    @OneToOne
    @JoinColumn(name = "id_cooperativa", nullable = false, unique = true)
    private Cooperativa cooperativa;

    @Column(name = "monto_minimo_aportacion", precision = 12, scale = 2)
    private BigDecimal montoMinimoAportacion;

    @Column(name = "monto_maximo_aportacion", precision = 12, scale = 2)
    private BigDecimal montoMaximoAportacion;

    @Column(name = "dia_pago_aportacion")
    private Integer diaPagoAportacion;
}
