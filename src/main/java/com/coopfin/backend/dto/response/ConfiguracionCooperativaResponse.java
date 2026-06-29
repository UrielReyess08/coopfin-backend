package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConfiguracionCooperativaResponse {

    private Long idConfiguracion;
    private BigDecimal tasaInteresDefault;
    private String moneda;
    private BigDecimal montoMaximoPrestamo;
    private Integer numeroMaximoCuotas;
    private Integer diasGracia;
    private Boolean estado;
    private Long idCooperativa;
    private String nombreCooperativa;
}
