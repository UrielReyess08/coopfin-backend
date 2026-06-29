package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConfiguracionCooperativaRequest {

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.00", message = "La tasa de interés no puede ser negativa")
    private BigDecimal tasaInteresDefault;

    @NotBlank(message = "La moneda es obligatoria")
    @Pattern(regexp = "PEN", message = "La moneda permitida es PEN")
    private String moneda;

    @NotNull(message = "El monto máximo de préstamo es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto máximo de préstamo debe ser mayor a 0")
    private BigDecimal montoMaximoPrestamo;

    @NotNull(message = "El número máximo de cuotas es obligatorio")
    @Min(value = 1, message = "El número máximo de cuotas debe ser como mínimo 1")
    private Integer numeroMaximoCuotas;

    @NotNull(message = "Los días de gracia son obligatorios")
    @Min(value = 0, message = "Los días de gracia no pueden ser negativos")
    private Integer diasGracia;

    @NotNull(message = "La cooperativa es obligatoria")
    private Long idCooperativa;
}
