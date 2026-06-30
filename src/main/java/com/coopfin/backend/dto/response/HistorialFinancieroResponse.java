package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class HistorialFinancieroResponse {

    private Long idSocio;
    private String codigoSocio;
    private String nombreSocio;

    private BigDecimal totalAportado;
    private BigDecimal totalPrestado;
    private BigDecimal saldoPendiente;

    private List<AportacionResponse> aportaciones;
    private List<PrestamoResponse> prestamos;
}