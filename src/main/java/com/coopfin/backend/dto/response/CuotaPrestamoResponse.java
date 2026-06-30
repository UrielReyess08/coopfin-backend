package com.coopfin.backend.dto.response;

import com.coopfin.backend.model.enums.EstadoCuota;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CuotaPrestamoResponse {

    private Long idCuota;
    private Integer numeroCuota;
    private BigDecimal capitalProgramado;
    private BigDecimal interesProgramado;
    private BigDecimal montoTotal;
    private LocalDate fechaVencimiento;
    private EstadoCuota estado;
}