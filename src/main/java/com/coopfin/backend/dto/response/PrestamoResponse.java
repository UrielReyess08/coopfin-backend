package com.coopfin.backend.dto.response;

import com.coopfin.backend.model.enums.EstadoPrestamo;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PrestamoResponse {

    private Long idPrestamo;
    private BigDecimal montoSolicitado;
    private Integer numeroCuotas;
    private BigDecimal tasaInteresAplicada;
    private BigDecimal saldoCapital;
    private BigDecimal saldoInteres;
    private BigDecimal saldoTotal;
    private LocalDate fechaSolicitud;
    private LocalDate fechaAprobacion;
    private String motivo;
    private EstadoPrestamo estado;
    private Long idSocio;
    private String nombreSocio;
}
