package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AportacionResponse {

    private Long idAportacion;
    private BigDecimal monto;
    private String tipo;
    private String periodo;
    private LocalDate fecha;
    private String observacion;
    private String estado;
    private Long idSocio;
    private String nombreSocio;
}