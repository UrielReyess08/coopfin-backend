package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AportacionRequest {

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    private String periodo;

    private String observacion;

    @NotNull(message = "El socio es obligatorio")
    private Long idSocio;
}