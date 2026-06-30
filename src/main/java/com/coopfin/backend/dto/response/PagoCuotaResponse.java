package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PagoCuotaResponse {

    private Long idPagoCuota;
    private BigDecimal capitalPagado;
    private BigDecimal interesPagado;
    private BigDecimal moraPagada;
    private BigDecimal montoTotalPagado;
    private LocalDate fechaPago;
    private String metodoPago;
    private String observacion;
    private Long idCuota;
    private Integer numeroCuota;
    private Long idPrestamo;
}