package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrestamoSolicitudRequest {

    @NotNull(message = "El monto solicitado es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto solicitado debe ser mayor a 0")
    private BigDecimal montoSolicitado;

    @NotNull(message = "El número de cuotas es obligatorio")
    @Min(value = 1, message = "El número de cuotas debe ser como mínimo 1")
    private Integer numeroCuotas;

    @Size(max = 255, message = "El motivo no puede superar los 255 caracteres")
    private String motivo;

    @NotNull(message = "El socio es obligatorio")
    private Long idSocio;
}
